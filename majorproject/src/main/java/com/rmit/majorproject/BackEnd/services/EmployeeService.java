package com.rmit.majorproject.BackEnd.services;

import com.rmit.majorproject.BackEnd.Repositories.EmployeeRepository;
import com.rmit.majorproject.BackEnd.model.Booking;
import com.rmit.majorproject.BackEnd.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void add(Employee employee) {
        employeeRepository.save(employee);
    }

    public Iterable<Employee> findAllByName(String name) {
       return employeeRepository.findAllByName(name);
    }
    
    public Iterable<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
     }
    
    public Iterable<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
     }
    
    public Iterable<Employee> findByPhoneNumber(String phoneNumber) {
        return employeeRepository.findByPhoneNumber(phoneNumber);
     }

    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }
    
    
    public void edit(Employee employee) {
    	employeeRepository.save(employee);
    }

    public boolean removeEmployee(Long id) {
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
        }

        return employeeOptional.isPresent();
    }
}
