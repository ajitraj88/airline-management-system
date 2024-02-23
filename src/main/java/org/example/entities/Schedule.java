package org.example.entities;

import org.example.enums.ScheduleStatus;

import java.time.LocalDateTime;

public class Schedule {
    private Airport sourceAirport;
    private Airport destinationAirport;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ScheduleStatus scheduleStatus;

    public Schedule(Airport sourceAirport, Airport destinationAirport, LocalDateTime startTime, LocalDateTime endTime, ScheduleStatus scheduleStatus) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleStatus = scheduleStatus;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public Schedule setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
        return this;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public Schedule setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
        return this;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Schedule setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Schedule setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public Schedule setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
        return this;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sourceAirport=" + sourceAirport +
                ", destinationAirport=" + destinationAirport +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", scheduleStatus=" + scheduleStatus +
                '}';
    }
}
