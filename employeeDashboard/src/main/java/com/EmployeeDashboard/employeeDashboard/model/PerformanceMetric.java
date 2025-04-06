package com.EmployeeDashboard.employeeDashboard.model;

import lombok.Data;

import java.util.List;

@Data
public class PerformanceMetric {
    private String month;
    private int productivity;
    private int goalsCompleted;
    private double feedbackScore;
    private String punctuality;
    private List<String> keyAchievements;

}
