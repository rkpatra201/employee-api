package com.spring.mock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.entity.Employee;
import com.spring.mock.utils.TestUtils;
import com.spring.model.Department;
import com.spring.model.Gender;
import com.spring.model.SortingOrder;
import com.spring.service.EmployeeService;
import com.spring.utils.DateConverterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @DisplayName("test save employee")
    @Test
    public void testCreateEmployee() throws Exception {
        Employee e1 = new Employee();
        e1.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e1.setFirstName("F1");
        e1.setLastName("L1");
        e1.setDepartment(Department.FINANCE);
        e1.setGender(Gender.MALE);
        e1.setId(100L);

        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(e1.getId());

        MvcResult mvcResult = mvc.perform(post("/employees/").
                 content(objectMapper.writeValueAsString(e1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(e1.getId(), Long.valueOf(response));
    }

    @DisplayName("test find employees")
    @Test
    public void testFindEmployees() throws Exception {
        List<Employee> employeeList = TestUtils.getMockEmployees();
        Mockito.when(employeeService.findEmployees(SortingOrder.ASC)).thenReturn(employeeList);

        MvcResult mvcResult = mvc.perform(get("/employees/ASC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(employeeList);
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(expectedResponse, response);

        // test for descending order
        Mockito.when(employeeService.findEmployees(SortingOrder.DESC)).thenReturn(employeeList);

        mvcResult = mvc.perform(get("/employees/DESC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        expectedResponse = objectMapper.writeValueAsString(employeeList);
        response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(expectedResponse, response);
    }

}
