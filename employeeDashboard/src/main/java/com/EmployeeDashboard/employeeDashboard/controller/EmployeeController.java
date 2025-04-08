package com.EmployeeDashboard.employeeDashboard.controller;


import com.EmployeeDashboard.employeeDashboard.model.Employee;
import com.EmployeeDashboard.employeeDashboard.model.PerformanceMetric;
import com.EmployeeDashboard.employeeDashboard.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/department/performance")
    public List<PerformanceMetric> getPerformanceByDepartment() {
        return employeeService.getPerformanceByDepartment();
    }

    @GetMapping("/generateMockData")
    public ResponseEntity<String> generateMockData() {
        employeeService.generateMockData();
        return ResponseEntity.ok("Mock data generated successfully");
    }
}
