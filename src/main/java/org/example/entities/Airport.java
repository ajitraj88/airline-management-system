package org.example.entities;

import java.util.List;

public class Airport {
    private String id;
    private Location location;
    private List<String> flightsNumbes;

    public Airport(String id, Location location, List<String> flightsNumbes) {
        this.id = id;
        this.location = location;
        this.flightsNumbes = flightsNumbes;
    }

    public String getId() {
        return id;
    }

    public Airport setId(String id) {
        this.id = id;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Airport setLocation(Location location) {
        this.location = location;
        return this;
    }

    public List<String> getFlightsNumbes() {
        return flightsNumbes;
    }

    public Airport setFlightsNumbes(List<String> flightsNumbes) {
        this.flightsNumbes = flightsNumbes;
        return this;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + id + '\'' +
                ", location=" + location +
                ", flightsNumbes=" + flightsNumbes +
                '}';
    }
}
