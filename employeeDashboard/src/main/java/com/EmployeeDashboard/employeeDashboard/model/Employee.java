package com.EmployeeDashboard.employeeDashboard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "employees")
@Data
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String role;
    private String department;
    private Date joinDate;
    private String managerId;
    private String avatarUrl;
    private List<PerformanceMetric> performance;
    private Date lastPromotionDate;
    private String location;
    private boolean isActive;
}