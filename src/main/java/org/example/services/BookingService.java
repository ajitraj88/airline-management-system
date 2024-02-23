package org.example.services;

import org.example.entities.Seat;
import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.exception.SeatsNotAvailableException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    public String createBooking(Flight flight, List<Passenger> passenger, LocalDateTime startTime, LocalDateTime endTime, List<Seat> seats) throws SeatsNotAvailableException;
    public String cancelBooking(String bookingId);
}
