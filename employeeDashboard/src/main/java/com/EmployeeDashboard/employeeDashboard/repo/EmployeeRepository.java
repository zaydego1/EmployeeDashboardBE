package com.EmployeeDashboard.employeeDashboard.repo;

import com.EmployeeDashboard.employeeDashboard.model.Employee;
import com.EmployeeDashboard.employeeDashboard.model.PerformanceByDept;
import com.EmployeeDashboard.employeeDashboard.model.PerformanceMetric;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Aggregation(pipeline = {
            "{ $unwind: '$performance' }",
            "{ $match: { " +
                    "'performance.productivity': { $exists: true, $type: 'number' }, " +
                    "'performance.goalsCompleted': { $exists: true, $type: 'number' }, " +
                    "'performance.feedbackScore': { $exists: true, $type: 'number' } " +
                    "}}",
            "{ $group: { " +
                    "_id: '$department', " +
                    "avgProductivity: { $avg: '$performance.productivity' }, " +
                    "avgGoalsCompleted: { $avg: '$performance.goalsCompleted' }, " +
                    "avgFeedbackScore: { $avg: '$performance.feedbackScore' } " +
                    "}}",
            "{ $project: { " +
                    "_id: 1, " +
                    "avgProductivity: { $round: ['$avgProductivity', 2] }, " +
                    "avgGoalsCompleted: { $round: ['$avgGoalsCompleted', 2] }, " +
                    "avgFeedbackScore: { $round: ['$avgFeedbackScore', 2] } " +
                    "}}"
    })
    List<PerformanceByDept> getPerformanceByDepartment();
}