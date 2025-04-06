package com.EmployeeDashboard.employeeDashboard.service;

import com.EmployeeDashboard.employeeDashboard.data.DataGenerator;
import com.EmployeeDashboard.employeeDashboard.model.Employee;
import com.EmployeeDashboard.employeeDashboard.repo.EmployeeRepository;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
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

    public List<Employee> getEmployeesByManagerId(String managerId) {
        return employeeRepository.findByManagerId(managerId);
    }

    public List<Employee> getEmployeesByLocation(String location) {
        return employeeRepository.findByLocation(location);
    }

    public List<Employee> getEmployeesByJoinDate(Date joinDate) {
        return employeeRepository.findByJoinDate(joinDate);
    }

    public List<Employee> getEmployeesByLastPromotionDate(Date lastPromotionDate) {
        return employeeRepository.findByLastPromotionDate(lastPromotionDate);
    }

    public List<Employee> getEmployeesByActiveStatus(boolean isActive) {
        return employeeRepository.findByIsActive(isActive);
    }

    public List<Employee> getEmployeesByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public List<Employee> getEmployeesByRole(String role) {
        return employeeRepository.findByRole(role);
    }

    public List<Employee> getEmployeesByProductivity(int productivity) {
        return employeeRepository.findByPerformanceProductivity(productivity);
    }

    public List<Employee> getEmployeesByGoalsCompleted(int goalsCompleted) {
        return employeeRepository.findByPerformanceGoalsCompleted(goalsCompleted);
    }

    public List<Employee> getEmployeesByFeedbackScore(double feedbackScore) {
        return employeeRepository.findByPerformanceFeedbackScore(feedbackScore);
    }

    public List<Employee> getEmployeesByPunctuality(String punctuality) {
        return employeeRepository.findByPerformancePunctuality(punctuality);
    }

    public void generateMockData() {
        try {
            dataGenerator.run();
        } catch (Exception e) {
            log.error("Error generating dummy data: ", e);
        }
    }
}
