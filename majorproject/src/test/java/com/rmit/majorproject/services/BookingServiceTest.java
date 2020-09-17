package com.rmit.majorproject.services;

import com.rmit.majorproject.BackEnd.services.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ComponentScan("com.rmit.majorproject.BackEnd.*")
public class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    @Test
    public void testAddBooking()  {

    }
}
