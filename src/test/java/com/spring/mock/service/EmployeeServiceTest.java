package com.spring.mock.service;

import com.spring.entity.Employee;
import com.spring.model.Department;
import com.spring.model.Gender;
import com.spring.model.SortingOrder;
import com.spring.repository.EmployeeRepository;
import com.spring.service.EmployeeService;
import com.spring.utils.DateConverterUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    List<Employee> employeeList = null;

    @DisplayName("test get employees by sorting order")
    @Test
    public void testFindEmployees() {
        setUpMockList();
        List<Employee> employeeListFromSvc = employeeService.findEmployees(SortingOrder.ASC);
        assertEquals(employeeList, employeeListFromSvc);
    }

    private void setUpMockList() {
        employeeList = new ArrayList<>();
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

        employeeList.add(e1);
        employeeList.add(e2);
        Mockito.when(employeeRepository.findByOrderByFirstNameAsc()).thenReturn(employeeList);
    }

    @DisplayName("test save employee")
    @Test
    public void saveEmployee() {
        Employee e1 = new Employee();
        e1.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e1.setFirstName("F1");
        e1.setLastName("L1");
        e1.setDepartment(Department.FINANCE);
        e1.setGender(Gender.MALE);
        e1.setId(100L);
        Mockito.when(employeeRepository.save(e1)).thenReturn(e1);
        Long id = employeeService.saveEmployee(e1);
        assertEquals(e1.getId(), id);
    }
}
