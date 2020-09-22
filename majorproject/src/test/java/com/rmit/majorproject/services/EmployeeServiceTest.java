package com.rmit.majorproject.services;

import com.rmit.majorproject.BackEnd.Repositories.BookingRepository;
import com.rmit.majorproject.BackEnd.model.Employee;
import com.rmit.majorproject.BackEnd.services.BookingService;
import com.rmit.majorproject.BackEnd.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan("com.rmit.majorproject.BackEnd.services")
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void contextTest() {
        assertThat(employeeService).isNotNull();
        assertThat(entityManager).isNotNull();
    }

    @Test
    public void testGettingById() {
        Employee bob = new Employee();
        bob.setName("Bob Test");
        bob.setDateOfBirth(new Date(1985, Calendar.OCTOBER, 22));
        bob.setTFN("123456789");
        bob.setEmail("bob@test.com");
        bob.setGender("Male");
        bob.setPhoneNumber("0499988844");
        bob.setSuperNumber("044815002000020");
        long id = (long)entityManager.persistAndGetId(bob);
        entityManager.flush();

        Optional<Employee> bobFromDb_opt = employeeService.getEmployeeById(id);
        assertThat(bobFromDb_opt).isPresent();
        Employee bobFromDb = bobFromDb_opt.get();

        assertThat(bobFromDb.getName()).isEqualTo(bob.getName());
    }

    @Test
    public void testGettingByName() {
        Employee bob = new Employee();
        bob.setName("Bob Test");
        bob.setDateOfBirth(new Date(1985, Calendar.OCTOBER, 22));
        bob.setTFN("123456789");
        bob.setEmail("bob@test.com");
        bob.setGender("Male");
        bob.setPhoneNumber("0499988844");
        bob.setSuperNumber("044815002000020");
        entityManager.persistAndFlush(bob);

        Iterable<Employee> resultItr = employeeService.findAllByName(bob.getName());
        assertThat(resultItr.spliterator().getExactSizeIfKnown()).isOne();
        //Get the first as this should be Bob Test
        Employee bobFromDb = resultItr.iterator().next();

        assertThat(bobFromDb.getName()).isEqualTo(bob.getName());

        resultItr = employeeService.findAllByName("Steve Test");
        assertThat(resultItr.spliterator().getExactSizeIfKnown()).isZero();
    }

    @Test
    public void testRemoveByIdExists() {
        Employee bob = new Employee();
        bob.setName("Bob Test");
        bob.setDateOfBirth(new Date(1985, Calendar.OCTOBER, 22));
        bob.setTFN("123456789");
        bob.setEmail("bob@test.com");
        bob.setGender("Male");
        bob.setPhoneNumber("0499988844");
        bob.setSuperNumber("044815002000020");

        long bob_id = (long)entityManager.persistAndGetId(bob);
        //Make sure that the employee was added to the database
        assertThat(entityManager.find(Employee.class, bob_id)).isNotNull();
        assertThat(employeeService.findAllByName(bob.getName())).isNotNull();
        employeeService.removeEmployee(bob_id);
        assertThat(entityManager.find(Employee.class, bob_id)).isNull();
        assertThat(employeeService.findAllByName(bob.getName()).spliterator().getExactSizeIfKnown()).isZero();
    }

    @Test
    public void testRemoveByIdMultipleExist() {
        //Re add bob to the db
        Employee bob = new Employee();
        bob.setName("Bob Test");
        bob.setDateOfBirth(new Date(1985, Calendar.OCTOBER, 22));
        bob.setTFN("123456789");
        bob.setEmail("bob@test.com");
        bob.setGender("Male");
        bob.setPhoneNumber("0499988844");
        bob.setSuperNumber("044815002000020");

        long bob_id = (long)entityManager.persistAndGetId(bob);

        Employee steve = new Employee();
        steve.setName("Steve Test");
        steve.setDateOfBirth(new Date(1985, Calendar.OCTOBER, 22));
        steve.setTFN("123456789");
        steve.setEmail("bob@test.com");
        steve.setGender("Male");
        steve.setPhoneNumber("0499988844");
        steve.setSuperNumber("044815002000020");
        long steve_id = (long)entityManager.persistAndGetId(steve);

        assertThat(entityManager.find(Employee.class, steve_id)).isNotNull();
        assertThat(employeeService.findAllByName(bob.getName())).isNotNull();
        assertThat(employeeService.findAllByName(steve.getName())).isNotNull();
        assertThat(employeeService.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(2);

        employeeService.removeEmployee(steve_id);

        assertThat(entityManager.find(Employee.class, steve_id)).isNull();
        assertThat(entityManager.find(Employee.class, bob_id)).isNotNull();
        assertThat(employeeService.findAllByName(steve.getName())).isNotNull();
        assertThat(entityManager.find(Employee.class, bob_id).getName()).isEqualTo(bob.getName());
    }

    @Test
    public void testRemoveByIdDoesNotExist() {
        Employee bob = new Employee();
        bob.setName("Bob Test");
        bob.setDateOfBirth(new Date(1985, Calendar.OCTOBER, 22));
        bob.setTFN("123456789");
        bob.setEmail("bob@test.com");
        bob.setGender("Male");
        bob.setPhoneNumber("0499988844");
        bob.setSuperNumber("044815002000020");

        entityManager.persistAndFlush(bob);

        long id = (long)entityManager.persistAndGetId(bob);
        //Make sure that the employee was added to the database
        assertThat(employeeService.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(1);
        assertThat(entityManager.find(Employee.class, id)).isNotNull();
        assertThat(employeeService.findAllByName(bob.getName())).isNotNull();
        employeeService.removeEmployee(id + 1) ;
        assertThat(entityManager.find(Employee.class, id)).isNotNull();
        assertThat(employeeService.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(1);
        assertThat(entityManager.find(Employee.class, id).getName()).isEqualTo(bob.getName());
    }
}
