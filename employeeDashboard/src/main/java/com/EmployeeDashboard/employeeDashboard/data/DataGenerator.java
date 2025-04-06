package com.EmployeeDashboard.employeeDashboard.data;

import com.EmployeeDashboard.employeeDashboard.model.Employee;
import com.EmployeeDashboard.employeeDashboard.model.PerformanceMetric;
import com.EmployeeDashboard.employeeDashboard.repo.EmployeeRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DataGenerator implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final Faker faker = new Faker();

    public DataGenerator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        employeeRepository.deleteAll();

        for (int i = 0; i < 50; i++) {
            Employee employee = new Employee();
            employee.setId(faker.idNumber().valid());
            employee.setName(faker.name().fullName());
            employee.setEmail(faker.internet().emailAddress());
            employee.setDepartment(faker.options().option("Engineering", "HR", "Finance", "Marketing"));
            employee.setRole(faker.job().title());
            employee.setJoinDate(faker.date().past(365 * 3, TimeUnit.DAYS)); // Joined in the last 3 years
            employee.setPerformance(generatePerformanceMetrics());
            employee.setActive(faker.bool().bool());

            employeeRepository.save(employee);
        }
    }

    private List<PerformanceMetric> generatePerformanceMetrics() {
        List<PerformanceMetric> metrics = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            PerformanceMetric metric = new PerformanceMetric();
            metric.setMonth(LocalDate.now().minusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM")));
            metric.setProductivity(faker.number().numberBetween(60, 100));
            metric.setGoalsCompleted(faker.number().numberBetween(1, 5));
            metric.setFeedbackScore(faker.number().randomDouble(1, 3, 5));
            metrics.add(metric);
        }
        return metrics;
    }
}
