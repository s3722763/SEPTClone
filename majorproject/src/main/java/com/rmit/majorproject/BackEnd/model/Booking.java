package com.rmit.majorproject.BackEnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Bookers name cannot be empty")
    private String bookersName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_employee", referencedColumnName = "id")
    private Employee assignedEmployee;
    @JsonFormat(pattern = "yyyy-mm-dd-HH-MM")
    private LocalDateTime bookingDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bookingCreationDate;
    @NotBlank
    private String service;

    public Booking() { };

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

    public Date getBookingCreationDate () {
        return this.bookingCreationDate;
    }

    @Column(name = "booking_creation_date", nullable = false)
    public void setBookingCreationDate(Date bookingCreationDate) {
        this.bookingCreationDate = bookingCreationDate;
    }

    public String getBookersName() {
        return this.bookersName;
    }

    @Column(name = "bookers_name", nullable = false)
    public void setBookersName(String name) {
        this.bookersName = name;
    }

    public Employee getAssignedEmployeeName() {
        return this.assignedEmployee;
    }

    @Column(name = "worker_name", nullable = false)
    public void setAssignedEmployeeName(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public String getService() {
        return this.service;
    }

    @Column(name = "service", nullable = false)
    public void setService(String service) {
        this.service = service;
    }
}
