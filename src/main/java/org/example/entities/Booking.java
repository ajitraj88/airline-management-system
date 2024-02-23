package org.example.entities;

import org.example.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {

    private String id;
    private List<Passenger> passengers;
    private Flight flight;
    private Schedule schedule;
    private Double price;
    List<Seat> bookedSeats;
    private LocalDateTime bookingDate;

    private BookingStatus status;

    private Booking(BookingBuilder bookingBuilder) {
        this.id = bookingBuilder.id;
        this.passengers = bookingBuilder.passengers;
        this.flight = bookingBuilder.flight;
        this.schedule = bookingBuilder.schedule;
        this.price = bookingBuilder.price;
        this.bookedSeats = bookingBuilder.bookedSeats;
        this.bookingDate = bookingBuilder.bookingDate;
        this.status = bookingBuilder.status;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Double getPrice() {
        return price;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }


    public LocalDateTime getBookingDate() {
        return bookingDate;
    }


    public BookingStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", passengers=" + passengers +
                ", flight=" + flight +
                ", schedule=" + schedule +
                ", price=" + price +
                ", bookedSeats=" + bookedSeats +
                ", bookingDate=" + bookingDate +
                ", status=" + status +
                '}';
    }

    public static class BookingBuilder {
        private String id;
        private List<Passenger> passengers;
        private Flight flight;
        private Schedule schedule;
        private Double price;
        List<Seat> bookedSeats;
        private LocalDateTime bookingDate;

        private BookingStatus status;

        public BookingBuilder() {

        }

        public BookingBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public BookingBuilder setPassengers(List<Passenger> passengers) {
            this.passengers = passengers;
            return this;
        }

        public BookingBuilder setFlight(Flight flight) {
            this.flight = flight;
            return this;
        }

        public BookingBuilder setSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public BookingBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public BookingBuilder setBookedSeats(List<Seat> bookedSeats) {
            this.bookedSeats = bookedSeats;
            return this;
        }

        public BookingBuilder setBookingDate(LocalDateTime bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public BookingBuilder setStatus(BookingStatus status) {
            this.status = status;
            return this;
        }

        public Booking build() {
            Booking booking = new Booking(this);
            return booking;
        }
    }
}
