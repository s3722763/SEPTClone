package com.rmit.majorproject.BackEnd.Repositories;

import com.rmit.majorproject.BackEnd.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
