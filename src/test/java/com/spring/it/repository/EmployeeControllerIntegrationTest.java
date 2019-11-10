package com.spring.it.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.entity.Employee;
import com.spring.mock.utils.TestUtils;
import com.spring.model.Department;
import com.spring.model.Gender;
import com.spring.model.SortingOrder;
import com.spring.utils.DateConverterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Test
    public void testCreateEmployee() throws Exception {
        Employee e1 = new Employee();
        e1.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e1.setFirstName("F1");
        e1.setLastName("L1");
        e1.setDepartment(Department.FINANCE);
        e1.setGender(Gender.MALE);

        Employee e2 = new Employee();
        e2.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e2.setFirstName("F2");
        e2.setLastName("L2");
        e2.setDepartment(Department.HR);
        e2.setGender(Gender.FEMALE);

        MvcResult mvcResult = mvc.perform(post("/employees/").
                content(objectMapper.writeValueAsString(e1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(Long.valueOf(response) > 0);

        mvcResult = mvc.perform(post("/employees/").
                content(objectMapper.writeValueAsString(e2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        response = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(Long.valueOf(response) > 0);

        testListEmployee();
    }

    //@Test
    public void testListEmployee() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/employees/ASC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        TestUtils.listIsSorted(mapResultToEmployeeList(mvcResult),SortingOrder.ASC);

        // test for descending order
        mvcResult = mvc.perform(get("/employees/DESC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        TestUtils.listIsSorted(mapResultToEmployeeList(mvcResult),SortingOrder.DESC);

    }

    private List<Employee> mapResultToEmployeeList(MvcResult mvcResult) throws Exception
    {
        String response = mvcResult.getResponse().getContentAsString();
        Employee[] employees = objectMapper.readValue(response, Employee[].class);
        List<Employee> employeeList = Stream.of(employees).collect(Collectors.toList());
        return employeeList;
    }
}
