package org.example.strategy.impl;

import org.example.entities.Seat;
import org.example.entities.Flight;
import org.example.enums.SeatType;
import org.example.strategy.PriceCalculator;

import java.util.List;

public class DefaultPriceCalculator extends PriceCalculator {

    public DefaultPriceCalculator() {
        super();
    }

    @Override
    public Double caclulatePrice(List<Seat> seats, Flight flight) {
        double basePrice = super.basePrice(flight);
        double price = 0;
        for(Seat seat : seats) {
            if (seat.getSeatType().equals(SeatType.ECONOMY)) price += basePrice*1.0;
            else if (seat.getSeatType().equals(SeatType.BUSINESS)) price += basePrice*1.0;
        }
        return price;
    }
}
