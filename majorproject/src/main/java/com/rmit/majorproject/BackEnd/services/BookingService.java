package com.rmit.majorproject.BackEnd.services;

import com.rmit.majorproject.BackEnd.Repositories.BookingRepository;
import com.rmit.majorproject.BackEnd.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public boolean add(Booking booking) {
        bookingRepository.save(booking);

        return true;
    }
}
