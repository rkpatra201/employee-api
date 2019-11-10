package com.spring.controller;

import com.spring.entity.Employee;
import com.spring.model.SortingOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.status(HttpStatus.OK).body("running");
    }

    @PostMapping("/")
    public ResponseEntity<Long> saveEmployee(@RequestBody Employee employee) {
        return null;
    }

    @GetMapping("/{sortingOrder")
    public ResponseEntity<List<Employee>> getEmployeeList(@PathVariable("sortingOrder") SortingOrder sortingOrder) {
        return null;
    }
}
