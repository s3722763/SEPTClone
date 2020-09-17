package com.rmit.majorproject.BackEnd.web;

import com.rmit.majorproject.BackEnd.model.Booking;
import com.rmit.majorproject.BackEnd.model.Employee;
import com.rmit.majorproject.BackEnd.services.EmployeeService;
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
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<?> getEmployees(@RequestParam(name = "name") Optional<String> name, @RequestParam(name = "id") Optional<Long> id) {
        if (name.isPresent()) {
            return new ResponseEntity<Iterable<Employee>>(employeeService.findAllByName(name.get()), HttpStatus.OK);
        } else if (id.isPresent()) {
            return new ResponseEntity<Optional<Employee>>(employeeService.getEmployeeById(id.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Iterable<Employee>>(employeeService.findAll(), HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

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
