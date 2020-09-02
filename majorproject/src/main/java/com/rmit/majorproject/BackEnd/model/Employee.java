package com.rmit.majorproject.BackEnd.model;

import com.rmit.majorproject.BackEnd.validators.DateConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Employee {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @OneToMany
    @JoinColumn(name = "assigned_bookings", referencedColumnName = "id")
    private List<Booking> assignedBookings;
    //TODO: Check this to see how it works
    @ElementCollection(targetClass = String.class)
    @DateConstraint
    private List<String> potentialRosterDays;

    public Employee() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getAssignedBookings() {
        return this.assignedBookings;
    }

    public void setAssignedBookings(List<Booking> assignedBookings) {
        this.assignedBookings = assignedBookings;
    }

    public List<String> getPotentialRosterDays() {
        return this.potentialRosterDays;
    }

    public void setPotentialRosterDays(List<String> potentialRosterDays) {
        this.potentialRosterDays = potentialRosterDays;
    }
}