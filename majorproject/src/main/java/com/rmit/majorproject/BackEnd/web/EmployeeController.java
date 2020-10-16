package com.rmit.majorproject.BackEnd.web;

import com.rmit.majorproject.BackEnd.model.Booking;
import com.rmit.majorproject.BackEnd.model.Employee;
import com.rmit.majorproject.BackEnd.services.EmployeeService;
import com.rmit.majorproject.MajorprojectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(MajorprojectApplication.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    @CrossOrigin("*")
    public ResponseEntity<?> getEmployees(@RequestParam(name = "name") Optional<String> name, @RequestParam(name = "id") Optional<Long> id) {
        if (name.isPresent()) {
            return new ResponseEntity<Iterable<Employee>>(employeeService.findAllByName(name.get()), HttpStatus.OK);
        } else if (id.isPresent()) {
            return new ResponseEntity<Optional<Employee>>(employeeService.getEmployeeById(id.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Iterable<Employee>>(employeeService.findAll(), HttpStatus.OK);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
    	
        employeeService.edit(employee);
    	
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
    
    @PostMapping("")
    @CrossOrigin("*")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        logger.debug("Request made to make employee + " + employee);
        if (result.hasErrors()) {
            logger.info("Invalid request made for employee creation");
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        logger.info("Creating new employee: " + employee.getName());
        System.out.println("Creating employee: " + employee.getName());
        employeeService.add(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<?> removeEmployee(@RequestParam(name = "id") Long id) {
        boolean successfulDelete = employeeService.removeEmployee(id);

        if (successfulDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
