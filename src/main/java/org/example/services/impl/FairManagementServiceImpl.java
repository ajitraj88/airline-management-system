package org.example.services.impl;

import org.example.entities.Flight;
import org.example.services.FairManagementService;
import org.example.strategy.PriceCalculator;

public class FairManagementServiceImpl implements FairManagementService {

    private PriceCalculator priceCalculator;


    public FairManagementServiceImpl(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public double getFlightFair(Flight flight) {
        return priceCalculator.basePrice(flight);
    }
}
