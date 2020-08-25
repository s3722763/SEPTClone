package com.rmit.majorproject.services;

import com.rmit.majorproject.BackEnd.model.Booking;
import com.rmit.majorproject.BackEnd.services.BookingService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ComponentScan("com.rmit.majorproject.BackEnd.*")
public class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    @Test
    public void testAddBooking() {
        Booking b = new Booking(LocalDateTime.now().plusHours(12));
        Assert.assertTrue(bookingService.add(b));
    }
}
