package com.rmit.majorproject.BackEnd.model;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime bookingDate;

    public Booking() {};

    public Booking(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getBookingDate() {
        return this.bookingDate;
    }

    @Column(name = "booking_date", nullable = false)
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
