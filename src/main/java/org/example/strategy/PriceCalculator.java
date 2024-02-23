package org.example.strategy;

import org.example.entities.Seat;
import org.example.entities.Flight;
import org.example.enums.City;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PriceCalculator {

    Map<City, Map<City, Double>> sourceToDestToBasePrice;

    public PriceCalculator() {
        sourceToDestToBasePrice = new HashMap<>();
        Map<City, Double> prices = new HashMap<>();
        prices.put(City.LKW, 3000.0);
        sourceToDestToBasePrice.put(City.BLR, prices);
    }

    public abstract Double caclulatePrice(List<Seat> seats, Flight flight);

    public Double basePrice(Flight flight) {
        if (!sourceToDestToBasePrice.containsKey(flight.getOrigin())) {
            System.out.println("Source does not exists in Pricing system");
            return null;
        }
        else if (!sourceToDestToBasePrice.get(flight.getOrigin()).containsKey(flight.getDestination())) {
            System.out.println("Destination does not exists in Pricing system");
            return null;
        }
        double basePrice = sourceToDestToBasePrice.get(flight.getOrigin()).get(flight.getDestination());
        return basePrice;
    }
}
