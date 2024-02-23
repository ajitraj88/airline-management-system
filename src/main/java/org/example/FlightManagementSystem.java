package org.example;

import org.example.entities.Airport;
import org.example.entities.Location;
import org.example.entities.Schedule;
import org.example.entities.Seat;
import org.example.entities.Flight;
import org.example.entities.Passenger;
import org.example.enums.Airlines;
import org.example.enums.City;
import org.example.enums.SeatStatus;
import org.example.enums.SeatType;
import org.example.exception.PassengerAlreadyExists;
import org.example.exception.SeatsNotAvailableException;
import org.example.services.BookingService;
import org.example.services.FairManagementService;
import org.example.services.FlightManagementService;
import org.example.services.PassengerService;
import org.example.services.impl.BookingServiceImpl;
import org.example.services.impl.FairManagementServiceImpl;
import org.example.services.impl.FlightManagementServiceImpl;
import org.example.services.impl.PassengerServiceImpl;
import org.example.strategy.PriceCalculator;
import org.example.strategy.impl.DefaultPriceCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.example.enums.ScheduleStatus.ON_TIME;

/**
 * Hello world!
 */
public class FlightManagementSystem {
    private static PassengerService passengerService;
    private static FlightManagementService flightManagementService;
    private static BookingService bookingService;
    private static BufferedReader bufferedReader;

    private static FairManagementService fairManagementService;

    public static void main(String[] args) throws IOException {
        bind();
        createPassenger();
        createFlights();
    }

    private static void createPassenger() {
        try {
            passengerService.addPassenger("name1", "name1@gmail.com", "12345668");
            passengerService.addPassenger("name2", "name2@gmail.com", "12345678");
            passengerService.addPassenger("name3", "name3@gmail.com", "12345688");
            passengerService.addPassenger("name4", "name4@gmail.com", "12345698");
        } catch (PassengerAlreadyExists e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createFlights() {
        Airport blrAirport = new Airport(UUID.randomUUID().toString(), new Location(City.BLR, "560034"), Arrays.asList("123", "134", "145"));
        Airport lucknowAirport = new Airport(UUID.randomUUID().toString(), new Location(City.LKW, "226001"), Arrays.asList("123", "134", "145"));
        Schedule schedule1 = new Schedule(blrAirport, lucknowAirport,
                LocalDateTime.of(2023, 11, 30, 04, 30),
                LocalDateTime.of(2023, 11, 30, 07, 30),
                ON_TIME
        );
        Schedule schedule2 = new Schedule(blrAirport, lucknowAirport,
                LocalDateTime.of(2023, 12, 30, 04, 30),
                LocalDateTime.of(2023, 12, 30, 07, 30),
                ON_TIME
        );

        // create seats

        Seat seat1 = new Seat("1", 1, "A", SeatType.ECONOMY, SeatStatus.AVAILABLE);
        Seat seat2 = new Seat("2", 1, "B", SeatType.ECONOMY, SeatStatus.AVAILABLE);
        Seat seat3 = new Seat("3", 1, "C", SeatType.ECONOMY, SeatStatus.AVAILABLE);
        Seat seat4 = new Seat("4", 1, "D", SeatType.ECONOMY, SeatStatus.AVAILABLE);
        Seat seat5 = new Seat("5", 1, "E", SeatType.ECONOMY, SeatStatus.AVAILABLE);
        Seat seat6 = new Seat("6", 1, "F", SeatType.ECONOMY, SeatStatus.AVAILABLE);

        Flight flight1 = new Flight("123", City.BLR, City.LKW, Airlines.INDIGO, Arrays.asList(schedule1, schedule2), Arrays.asList(seat2, seat1, seat3, seat4));
        Flight flight2 = new Flight("134", City.BLR, City.LKW, Airlines.INDIGO, Arrays.asList(schedule1, schedule2), Arrays.asList(seat5, seat6));

        flightManagementService.addFlight(flight1);
        flightManagementService.addFlight(flight2);

        // search flights
        List<Flight> flights = flightManagementService.searchFlights(City.BLR, City.LKW);
        System.out.println(flights.size());
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }

        // book flight
        List<Passenger> passengers = passengerService.getPassengers();
        try {
            bookingService.createBooking(flights.get(0),
                    Arrays.asList(passengers.get(0)),
                    LocalDateTime.of(2023, 11, 30, 04, 30),
                    LocalDateTime.of(2023, 11, 30, 07, 30),
                    Arrays.asList(seat2, seat1));
        } catch (SeatsNotAvailableException e) {
        }
    }

    private static void bind() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PriceCalculator priceCalculator = new DefaultPriceCalculator();
        fairManagementService = new FairManagementServiceImpl(priceCalculator);
        passengerService = new PassengerServiceImpl();
        flightManagementService = new FlightManagementServiceImpl(fairManagementService);
        bookingService = new BookingServiceImpl(priceCalculator);
    }
}
