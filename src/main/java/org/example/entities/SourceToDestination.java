package org.example.entities;

import org.example.enums.City;

public class SourceToDestination {
    City source;
    City destination;

    public SourceToDestination(City source, City destination) {
        this.source = source;
        this.destination = destination;
    }

    public City getSource() {
        return source;
    }

    public SourceToDestination setSource(City source) {
        this.source = source;
        return this;
    }

    public City getDestination() {
        return destination;
    }

    public SourceToDestination setDestination(City destination) {
        this.destination = destination;
        return this;
    }
}
