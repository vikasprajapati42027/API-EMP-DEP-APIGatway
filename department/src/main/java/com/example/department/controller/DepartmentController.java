package com.example.department.controller;

import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository repo;

    @PostMapping
    public Department save(@RequestBody Department department) {
        return repo.save(department);
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
