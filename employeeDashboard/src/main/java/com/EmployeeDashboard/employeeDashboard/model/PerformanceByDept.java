package com.EmployeeDashboard.employeeDashboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PerformanceByDept {

    @Id
    private String department;
    private double avgProductivity;
    private double avgGoalsCompleted;
    private double avgFeedbackScore;
}
