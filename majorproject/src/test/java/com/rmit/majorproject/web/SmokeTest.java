package com.rmit.majorproject.web;

import com.rmit.majorproject.BackEnd.web.BookingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private BookingController bookingController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(bookingController).isNotNull();
    }
}
