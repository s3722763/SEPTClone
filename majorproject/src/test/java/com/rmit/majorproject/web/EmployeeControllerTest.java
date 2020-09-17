package com.rmit.majorproject.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rmit.majorproject.BackEnd.Repositories.EmployeeRepository;
import com.rmit.majorproject.BackEnd.model.Employee;
import com.rmit.majorproject.BackEnd.web.EmployeeController;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void contextTest() {
        assertThat(mockMvc).isNotNull();
        assertThat(employeeRepository).isNotNull();
    }

    @Test
    public void shouldAddNewEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Bob test");

        String employee_json = OBJECT_MAPPER.writeValueAsString(employee);
        this.mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON).content(employee_json)).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.potentialRosterDays").doesNotExist());
    }

    @Test
    public void shouldRemoveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Bob test");
        employee.setId(1);

        when(this.employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(delete("/api/employee").queryParam("id", String.valueOf(employee.getId())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotRemoveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Bob test");
        employee.setId(1);

        when(this.employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(delete("/api/employee").queryParam("id", String.valueOf(employee.getId() + 1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());

        Optional<Employee> contained_employee_opt = this.employeeRepository.findById(employee.getId());
        assertThat(contained_employee_opt).isPresent();
        Employee contained_employee = contained_employee_opt.get();
        assertThat(contained_employee.getName()).isEqualTo(employee.getName());
        assertThat(contained_employee.getId()).isEqualTo(employee.getId());
    }
}
