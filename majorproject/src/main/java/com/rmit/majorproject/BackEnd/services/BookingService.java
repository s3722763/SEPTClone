package com.rmit.majorproject.BackEnd.services;

import com.rmit.majorproject.BackEnd.Repositories.BookingRepository;
import com.rmit.majorproject.BackEnd.exceptions.InvalidBookingException;
import com.rmit.majorproject.BackEnd.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public void add(Booking booking) /*throws InvalidBookingException*/ {
        /*if (booking.getBookingDate().isBefore(LocalDateTime.now())) {
            throw new InvalidBookingException("Invalid Booking Date");
        }*/

        bookingRepository.save(booking);
    }

    public boolean deleteBooking(long id) {
        Optional<Booking> bookingOptional = this.bookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            bookingRepository.delete(bookingOptional.get());
        }

        return bookingOptional.isPresent();
    }
}
