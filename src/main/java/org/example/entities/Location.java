package org.example.entities;

import org.example.enums.City;

public class Location {
    private City city;
    private String pincode;

    public Location(City city, String pincode) {
        this.city = city;
        this.pincode = pincode;
    }

    public City getCity() {
        return city;
    }

    public Location setCity(City city) {
        this.city = city;
        return this;
    }

    public String getPincode() {
        return pincode;
    }

    public Location setPincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city=" + city +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
