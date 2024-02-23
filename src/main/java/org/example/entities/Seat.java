package org.example.entities;

import org.example.enums.SeatStatus;
import org.example.enums.SeatType;

public class Seat {

    private String id;
    private Integer row;
    private String seatNumber;
    private SeatType seatType;

    private SeatStatus seatStatus;

    public Seat(String id, Integer row, String seatNumber, SeatType seatType, SeatStatus seatStatus) {
        this.id = id;
        this.row = row;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
    }

    public String getId() {
        return id;
    }

    public Seat setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getRow() {
        return row;
    }

    public Seat setRow(Integer row) {
        this.row = row;
        return this;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Seat setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public Seat setSeatType(SeatType seatType) {
        this.seatType = seatType;
        return this;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public Seat setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
        return this;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id='" + id + '\'' +
                ", row=" + row +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType=" + seatType +
                ", seatStatus=" + seatStatus +
                '}';
    }
}
