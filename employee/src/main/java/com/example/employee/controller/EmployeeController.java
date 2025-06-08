package com.example.employee.controller;

import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElse(null);
        assert emp != null;
        Department dept = restTemplate.getForObject("http://department-service/api/departments/" + emp.getDepartmentId(), Department.class);

        Map<String, Object> map = new HashMap<>();
        map.put("employee", emp);
        map.put("department", dept);
        return map;
    }
}

