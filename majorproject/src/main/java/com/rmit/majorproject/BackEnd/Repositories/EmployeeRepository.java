package com.rmit.majorproject.BackEnd.Repositories;

import com.rmit.majorproject.BackEnd.model.Booking;
import com.rmit.majorproject.BackEnd.model.Employee;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Iterable<Employee> findAllById(Iterable<Long> iterable);

    Iterable<Employee> findAllByName(String name);
    
    Iterable<Employee> findByEmail(String email);
    
    Iterable<Employee> findByGender(String gender);

	Iterable<Employee> findByPhoneNumber(String dateOfBirth);
    
}
