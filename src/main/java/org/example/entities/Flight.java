package org.example.entities;

import org.example.enums.Airlines;
import org.example.enums.City;

import java.util.List;

public class Flight {
    private String flightNumber;
    private City origin;
    private City destination;
    private Airlines airline;
    private List<Schedule> schedule;
    private List<Seat> seats;

    private Double basePrice;


    public Flight(String flightNumber, City origin, City destination, Airlines airline, List<Schedule> schedule, List<Seat> seats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.schedule = schedule;
        this.seats = seats;

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Flight setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public City getOrigin() {
        return origin;
    }

    public Flight setOrigin(City origin) {
        this.origin = origin;
        return this;
    }

    public City getDestination() {
        return destination;
    }

    public Flight setDestination(City destination) {
        this.destination = destination;
        return this;
    }

    public Airlines getAirline() {
        return airline;
    }

    public Flight setAirline(Airlines airline) {
        this.airline = airline;
        return this;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public Flight setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
        return this;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Flight setSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public Flight setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", airline=" + airline +
                ", schedule=" + schedule +
                ", seats=" + seats +
                ", basePrice=" + basePrice +
                '}';
    }
}
