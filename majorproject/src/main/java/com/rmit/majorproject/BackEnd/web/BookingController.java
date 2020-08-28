package com.rmit.majorproject.BackEnd.web;

import com.rmit.majorproject.BackEnd.services.BookingService;
import com.rmit.majorproject.BackEnd.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    public ResponseEntity<Booking> createNewBooking(@RequestBody Booking booking) {
        bookingService.add(booking);

        return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
    }
}
