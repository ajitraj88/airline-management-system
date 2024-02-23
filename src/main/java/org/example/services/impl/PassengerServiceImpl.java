package org.example.services.impl;

import org.example.entities.Passenger;
import org.example.exception.PassengerAlreadyExists;
import org.example.services.PassengerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PassengerServiceImpl implements PassengerService {

    Map<String, Passenger> phoneToPassenger = new HashMap<>();

    @Override
    public String addPassenger(String name, String email, String phoneNumber) throws PassengerAlreadyExists {
        Passenger passenger = null;
        if (phoneToPassenger.containsKey(phoneNumber)) {
            throw new PassengerAlreadyExists("Passenger with the phone number: " + phoneNumber + " already exists");
        } else {
            passenger = new Passenger(
                    UUID.randomUUID().toString(),
                    name,
                    email,
                    phoneNumber
            );
            phoneToPassenger.put(phoneNumber, passenger);
        }
        return passenger.getId();
    }

    @Override
    public List<Passenger> getPassengers() {
        return new ArrayList(phoneToPassenger.values());
    }
}
