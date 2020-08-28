package com.rmit.majorproject.BackEnd.Repositories;

import com.rmit.majorproject.BackEnd.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    @Override
    Iterable<Booking> findAllById(Iterable<Long> iterable);

    @Override
    Iterable<Booking> findAll();
}
