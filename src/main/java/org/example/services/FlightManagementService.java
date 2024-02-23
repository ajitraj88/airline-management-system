package org.example.services;

import org.example.entities.Airport;
import org.example.entities.Flight;
import org.example.enums.City;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightManagementService {
    public void addFlight(Flight flight);
    public void updateFlight(String id, Flight flight);
    public void cancelFight(String flightNumber, Airport sourceAirport, Airport destinationAirport, LocalDateTime startTime, LocalDateTime endTime);

    public List<Flight> searchFlights(City source, City destination);
}
