package com.rmit.majorproject;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.rmit.majorproject.BackEnd.model.Booking;
import com.rmit.majorproject.BackEnd.services.BookingService;
import com.rmit.majorproject.BackEnd.web.BookingController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@WebMvcTest(BookingController.class)
public class BookingControllerTests {
    @MockBean
    private BookingService bookingService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void cleanDB() {
        for (Booking b : bookingService.findAll()) {
            bookingService.deleteBooking(b.getId());
        }
    }

    @Test
    public void testGETNoId() throws Exception {
        Booking bob = new Booking();
        bob.setBookersName("Bob booker");
        bob.setBookingDate(LocalDateTime.now().plusDays(1));
        bookingService.add(bob);

        mvc.perform(get("/api/booking")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(bob.getBookersName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPOST() throws Exception {
        Booking bob = new Booking();
        bob.setBookersName("Bob booker");
        bob.setBookingDate(LocalDateTime.now().plusDays(1));

        mvc.perform(post("/api/booking").content(bob.toString()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
