package org.example.providers;

import org.example.entities.Schedule;
import org.example.entities.Seat;
import org.example.entities.SeatLock;
import org.example.exception.SeatTemporaryUnavailableException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySeatLockProviderImpl implements SeatLockProvider {
    private Integer lockTimeout;
    private Map<Schedule, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProviderImpl(final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    synchronized public void lockSeats(final Schedule schedule, final List<Seat> seats,
                                       final String user) throws SeatTemporaryUnavailableException {
        // flight
        for (Seat seat : seats) {
            if (isSeatLocked(schedule, seat)) {
                throw new SeatTemporaryUnavailableException("Seat not available");
            }
        }

        for (Seat seat : seats) {
            lockSeat(schedule, seat, user, lockTimeout);
        }
    }

    @Override
    public void unlockSeats( final Schedule schedule,  final List<Seat> seats,  final String user) {
        for (Seat seat: seats) {
            if (validateLock(schedule, seat, user)) {
                unlockSeat(schedule, seat);
            }
        }
    }

    @Override
    public boolean validateLock( final Schedule schedule,  final Seat seat,  final String user) {
        return isSeatLocked(schedule, seat) && locks.get(schedule).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats( final Schedule schedule) {
        if (!locks.containsKey(schedule)) {
            return new ArrayList<>();
        }
        final List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : locks.get(schedule).keySet()) {
            if (isSeatLocked(schedule, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private void unlockSeat(final Schedule schedule, final Seat seat) {
        if (!locks.containsKey(schedule)) {
            return;
        }
        locks.get(schedule).remove(seat);
    }

    private void lockSeat(final Schedule schedule, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(schedule)) {
            locks.put(schedule, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, schedule, timeoutInSeconds, new Date(), user);
        locks.get(schedule).put(seat, lock);
    }

    private boolean isSeatLocked(final Schedule schedule, final Seat seat) {
        return locks.containsKey(schedule) && locks.get(schedule).containsKey(seat) && !locks.get(schedule).get(seat).isLockExpired();
    }
}
