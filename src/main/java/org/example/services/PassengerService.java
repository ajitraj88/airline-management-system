package org.example.services;

import org.example.entities.Passenger;
import org.example.exception.PassengerAlreadyExists;

import java.util.List;

public interface PassengerService {
    public String addPassenger(String name, String email, String phoneNumber) throws PassengerAlreadyExists;

    public List<Passenger> getPassengers();
}
