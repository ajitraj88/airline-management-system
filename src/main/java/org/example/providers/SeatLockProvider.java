package org.example.providers;

import org.example.entities.Schedule;
import org.example.entities.Seat;
import org.example.exception.SeatTemporaryUnavailableException;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Schedule show, List<Seat> seat, String user) throws SeatTemporaryUnavailableException;
    void unlockSeats(Schedule show, List<Seat> seat, String user);
    boolean validateLock(Schedule show, Seat seat, String user);

    List<Seat> getLockedSeats(Schedule show);
}
