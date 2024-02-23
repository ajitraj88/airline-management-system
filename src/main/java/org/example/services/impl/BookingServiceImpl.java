package org.example.services.impl;

import org.example.entities.Booking;
import org.example.entities.Seat;
import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.enums.BookingStatus;
import org.example.enums.SeatStatus;
import org.example.exception.SeatsNotAvailableException;
import org.example.providers.SeatLockProvider;
import org.example.services.BookingService;
import org.example.strategy.PriceCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private Map<String, Booking> bookings = new HashMap<>();
    private PriceCalculator priceCalculator;

    private SeatLockProvider seatLockProvider; //

    public BookingServiceImpl(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public String createBooking(Flight flight, List<Passenger> passenger, LocalDateTime startTime, LocalDateTime endTime, List<Seat> seats) throws SeatsNotAvailableException {
        Set<String> idsToBook = new HashSet<>(seats.stream().map(seat -> seat.getId()).collect(Collectors.toSet()));

        List<Seat> bookedSeats = new ArrayList<>();
        flight.getSeats().stream().forEach(seat ->
        {
            if (idsToBook.contains(seat.getId()) && seat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                // also check if seat is already locked or not via seatLockProvider. Unable to implement completely.
                seat.setSeatStatus(SeatStatus.BOOKED);
                bookedSeats.add(seat);
            }
        });

        if (bookedSeats.size() != seats.size()) {
            bookedSeats.stream().forEach(seat -> seat.setSeatStatus(SeatStatus.AVAILABLE));
            throw new SeatsNotAvailableException("Seats you are trying to book are not available");
        }
        Booking booking = new Booking.BookingBuilder().
                setId(UUID.randomUUID().toString()).
                setFlight(flight).
                setPassengers(passenger).
                setBookedSeats(new ArrayList<>()).
                setStatus(BookingStatus.CREATED).
                setPrice(priceCalculator.caclulatePrice(bookedSeats, flight)).
                build();
        ;
        bookings.put(booking.getId(), booking);
        System.out.println("Booking Successfully Created " + booking.getId());
        System.out.println("Booking details: " + booking.toString());
        return booking.getId();
    }

    @Override
    public String cancelBooking(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            return "booking not exists";
        }
        Booking booking = bookings.get(bookingId);
        booking.getBookedSeats().stream().forEach(seat -> seat.setSeatStatus(SeatStatus.AVAILABLE));
        bookings.remove(bookingId);
        return null;
    }
}
