package com.EmployeeDashboard.employeeDashboard.repo;

import com.EmployeeDashboard.employeeDashboard.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByDepartment(String department);
    List<Employee> findByManagerId(String managerId);
    List<Employee> findByLocation(String location);
    List<Employee> findByIsActive(boolean isActive);
    List<Employee> findByJoinDate(Date startDate);
    List<Employee> findByLastPromotionDate(Date startDate);
    List<Employee> findByPerformanceProductivity(int productivity);
    List<Employee> findByPerformanceGoalsCompleted(int goalsCompleted);
    List<Employee> findByPerformanceFeedbackScore(double feedbackScore);
    List<Employee> findByEmail(String email);
    List<Employee> findByRole(String role);
    List<Employee> findByPerformancePunctuality(String punctuality);
}