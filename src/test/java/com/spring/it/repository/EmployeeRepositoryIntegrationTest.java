package com.spring.it.repository;

import com.spring.entity.Employee;
import com.spring.model.Department;
import com.spring.model.Gender;
import com.spring.repository.EmployeeRepository;
import com.spring.utils.DateConverterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByOrderByFirstNameAsc() {
        Employee e2 = new Employee();
        e2.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e2.setFirstName("John");
        e2.setLastName("Doe");
        e2.setDepartment(Department.HR);
        e2.setGender(Gender.FEMALE);

        Employee e1 = new Employee();
        e1.setDateOfBirth(DateConverterUtils.toDate("24-08-1996"));
        e1.setFirstName("Alistair");
        e1.setLastName("Cook");
        e1.setDepartment(Department.FINANCE);
        e1.setGender(Gender.MALE);

        employeeRepository.save(e1);
        employeeRepository.save(e2);

        List<Employee> employeeList = employeeRepository.findByOrderByFirstNameAsc();
        Assertions.assertEquals(2, employeeList.size());
        Assertions.assertEquals(e1.getFirstName(), employeeList.get(0).getFirstName());
        Assertions.assertEquals(e2.getFirstName(), employeeList.get(1).getFirstName());

    }

}
