package com.spring.controller;

import com.spring.entity.Employee;
import com.spring.model.SortingOrder;
import com.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.status(HttpStatus.OK).body("running");
    }

    @PostMapping("/")
    public ResponseEntity<Long> saveEmployee(@RequestBody Employee employee) {
        Long id = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("/{sortingOrder")
    public ResponseEntity<List<Employee>> getEmployeeList(@PathVariable("sortingOrder") SortingOrder sortingOrder) {
        List<Employee> list =  employeeService.findEmployees(sortingOrder);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
