package com.spring.mock.service;

import com.spring.entity.Employee;
import com.spring.mock.utils.TestUtils;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;


    @DisplayName("test get employees by asc order")
    @Test
    public void testFindEmployeesAsc() {
        List<Employee> employeeList = TestUtils.getMockEmployees();
        Mockito.when(employeeRepository.findByOrderByFirstNameAsc()).thenReturn(employeeList);
        List<Employee> employeeListFromSvc = employeeService.findEmployees(SortingOrder.ASC);
        TestUtils.compareListOrderByName(employeeList, employeeListFromSvc);
    }

    @DisplayName("test get employees by desc order")
    @Test
    public void testFindEmployeesDesc() {
        List<Employee> employeeList = TestUtils.getMockEmployees();
        Mockito.when(employeeRepository.findByOrderByFirstNameDesc()).thenReturn(employeeList);
        List<Employee> employeeListFromSvc = employeeService.findEmployees(SortingOrder.DESC);
        TestUtils.compareListOrderByName(employeeList, employeeListFromSvc);
    }

    private List<Employee> setUpMockList() {
        List<Employee> employeeList = TestUtils.getMockEmployees();
        Mockito.when(employeeRepository.findByOrderByFirstNameAsc()).thenReturn(employeeList);
        return employeeList;
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
