package org.example.services.impl;

import org.example.entities.Airport;
import org.example.entities.Flight;
import org.example.enums.City;
import org.example.enums.ScheduleStatus;
import org.example.services.FairManagementService;
import org.example.services.FlightManagementService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManagementServiceImpl implements FlightManagementService {

    Map<City, List<String>> sourceToFlights; // srcCity vs flightsIds
    Map<City, List<String>> destinationToFlights; // destinationCity vs flightsIds
    Map<String, Flight> idToFlight; // flightsIds to flights

    FairManagementService fairManagementService;

    public FlightManagementServiceImpl(FairManagementService fairManagementService) {
        this.sourceToFlights = new HashMap<>();
        this.destinationToFlights = new HashMap<>();
        this.idToFlight = new HashMap<>();
        this.fairManagementService = fairManagementService;
    }

    @Override
    public void addFlight(Flight flight) {
        if(sourceToFlights.get(flight.getOrigin()) == null) {
            sourceToFlights.put(flight.getOrigin(), new ArrayList<>());
        }
        if(destinationToFlights.get(flight.getDestination()) == null) {
            destinationToFlights.put(flight.getDestination(), new ArrayList<>());
        }
        sourceToFlights.get(flight.getOrigin()).add(flight.getFlightNumber());
        destinationToFlights.get(flight.getDestination()).add(flight.getFlightNumber());
        idToFlight.put(flight.getFlightNumber(), flight);
    }

    @Override
    public void updateFlight(String id, Flight flight) {
        City currentSource = idToFlight.get(id).getOrigin();
        City currentDestination = idToFlight.get(id).getDestination();

        City newSource = flight.getOrigin();
        City newDestination = flight.getDestination();

        idToFlight.put(id, flight);
        if(!currentSource.equals(newSource)) {
            sourceToFlights.get(currentSource).remove(flight.getFlightNumber());
        }
        if(!currentDestination.equals(newDestination)) {
            destinationToFlights.get(currentSource).remove(flight.getFlightNumber());
        }

        idToFlight.put(flight.getFlightNumber(), flight);
        sourceToFlights.get(flight.getOrigin()).add(flight.getFlightNumber());
        destinationToFlights.get(flight.getDestination()).add(flight.getFlightNumber());
    }

    @Override
    public void cancelFight(String flightNumber, Airport sourceAirport, Airport destinationAirport, LocalDateTime startTime, LocalDateTime endTime) {
        idToFlight.get(flightNumber).getSchedule().stream().filter(schedule ->
                schedule.getSourceAirport().equals(sourceAirport) &&
                schedule.getDestinationAirport().equals(destinationAirport) &&
                schedule.getStartTime().equals(startTime) &&
                schedule.getEndTime().equals(endTime)
                ).forEach(schedule -> schedule.setScheduleStatus(ScheduleStatus.CANCEL));
    }

    @Override
    public List<Flight> searchFlights(City source, City destination) {
        List<Flight> flights = new ArrayList<>();
        for (String sourceIds: sourceToFlights.get(source)) {
            for (String destinationId : destinationToFlights.get(destination)) {
                if (sourceIds.equalsIgnoreCase(destinationId)) { // miss to handle cancelled flights
                    Flight currentFlight = idToFlight.get(sourceIds);
                    currentFlight.setBasePrice(fairManagementService.getFlightFair(currentFlight));
                    flights.add(currentFlight);
                }
            }
        }
        return flights;
    }
}
