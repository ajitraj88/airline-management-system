package org.example.entities;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Schedule show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

    public SeatLock(Seat seat, Schedule show, Integer timeoutInSeconds, Date lockTime, String lockedBy) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.lockedBy = lockedBy;
    }

    public Seat getSeat() {
        return seat;
    }

    public SeatLock setSeat(Seat seat) {
        this.seat = seat;
        return this;
    }

    public Schedule getShow() {
        return show;
    }

    public SeatLock setShow(Schedule show) {
        this.show = show;
        return this;
    }

    public Integer getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public SeatLock setTimeoutInSeconds(Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
        return this;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public SeatLock setLockTime(Date lockTime) {
        this.lockTime = lockTime;
        return this;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public SeatLock setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
        return this;
    }
}
