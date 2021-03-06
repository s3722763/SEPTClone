package com.rmit.majorproject.BackEnd.web;

import com.rmit.majorproject.BackEnd.services.BookingService;
import com.rmit.majorproject.BackEnd.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        booking.setBookingCreationDate(Date.from(Instant.now()));
        bookingService.add(booking);

        return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Booking>> getBookings() {
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable long id) {
        boolean successfulDelete = bookingService.deleteBooking(id);

        if (successfulDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
