package com.spring.service;

import com.spring.entity.Employee;
import com.spring.model.SortingOrder;
import com.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Long saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee.getId();
    }

    public List<Employee> findEmployees(SortingOrder sortingOrder) {
        if (sortingOrder == SortingOrder.ASC)
            return employeeRepository.findByOrderByFirstNameAsc();
        else
            return employeeRepository.findByOrderByFirstNameDesc();
    }
}
