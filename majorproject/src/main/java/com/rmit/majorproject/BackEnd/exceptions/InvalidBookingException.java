package com.rmit.majorproject.BackEnd.exceptions;

public class InvalidBookingException extends Exception {
    private final String reason;

    public InvalidBookingException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }
}
