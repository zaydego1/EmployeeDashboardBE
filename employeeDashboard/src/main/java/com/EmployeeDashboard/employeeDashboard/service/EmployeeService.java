package com.EmployeeDashboard.employeeDashboard.service;

import com.EmployeeDashboard.employeeDashboard.data.DataGenerator;
import com.EmployeeDashboard.employeeDashboard.model.Employee;
import com.EmployeeDashboard.employeeDashboard.model.PerformanceMetric;
import com.EmployeeDashboard.employeeDashboard.repo.EmployeeRepository;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DataGenerator dataGenerator;

    public EmployeeService() {}

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<PerformanceMetric> getPerformanceByDepartment() {
        return employeeRepository.getPerformanceByDepartment();
    }

    public Employee updateEmployee(String id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }


    public void generateMockData() {
        try {
            dataGenerator.run();
        } catch (Exception e) {
            log.error("Error generating dummy data: ", e);
        }
    }
}
