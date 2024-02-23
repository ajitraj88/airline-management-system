package org.example.exception;

public class PassengerAlreadyExists extends Exception {
    public PassengerAlreadyExists(String message) {
        super(message);
    }
}
