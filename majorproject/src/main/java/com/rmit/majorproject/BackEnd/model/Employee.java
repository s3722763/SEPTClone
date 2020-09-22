package com.rmit.majorproject.BackEnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmit.majorproject.BackEnd.validators.DateConstraint;
import org.springframework.core.annotation.AliasFor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
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
    @NotNull(message = "Date of birth cannot be blank")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date dateOfBirth;
    @NotBlank(message = "Gender must be specified")
    private String gender;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Must include a phone number")
    @Pattern(regexp = "(04)?[0-9]{8}")
    private String phoneNumber;
    @NotBlank(message = "Must include a TFN number")
    private String TFN;
    @NotBlank(message = "Must include super number")
    private String superNumber;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTFN() {
        return TFN;
    }

    public void setTFN(String TFN) {
        this.TFN = TFN;
    }

    public String getSuperNumber() {
        return superNumber;
    }

    public void setSuperNumber(String superNumber) {
        this.superNumber = superNumber;
    }
}