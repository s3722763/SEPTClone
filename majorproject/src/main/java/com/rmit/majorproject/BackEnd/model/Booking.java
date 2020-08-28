package com.rmit.majorproject.BackEnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Bookers name cannot be empty")
    private String bookersName;
    @NotBlank(message = "Workers name cannot be empty")
    private String workerName;
    @JsonFormat(pattern = "yyyy-mm-dd-HH-MM")
    private LocalDateTime bookingDate;
    @JsonFormat(pattern = "yyyy-mm-dd-HH-MM-ss")
    private LocalDateTime bookingCreationDate;

    public Booking() {};

    public Booking(String workerName, String bookersName, LocalDateTime bookingCreationDate, LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
        this.bookingCreationDate = bookingCreationDate;
        this.bookersName = bookersName;
        this.workerName = workerName;
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

    public LocalDateTime getBookingCreationDate () {
        return this.bookingCreationDate;
    }

    @Column(name = "booking_creation_date", nullable = false)
    public void setBookingCreationDate(LocalDateTime bookingCreationDate) {
        this.bookingCreationDate = bookingCreationDate;
    }

    public String getBookersName() {
        return this.bookersName;
    }

    @Column(name = "bookers_name", nullable = false)
    public void setBookersName(String name) {
        this.bookersName = name;
    }

    public String getWorkerName() {
        return this.workerName;
    }

    @Column(name = "worker_name", nullable = false)
    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
}
