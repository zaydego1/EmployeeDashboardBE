package com.EmployeeDashboard.employeeDashboard.repo;

import com.EmployeeDashboard.employeeDashboard.model.Employee;
import com.EmployeeDashboard.employeeDashboard.model.PerformanceMetric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    String performanceByDept = "[\n" +
            "  {\n" +
            "    $unwind: \"$performance\"\n" +
            "  },\n" +
            "  {\n" +
            "    $group: {\n" +
            "      _id: \"$department\",\n" +
            "      totalProductivity: {\n" +
            "        $avg: \"$performance.productivity\"\n" +
            "      },\n" +
            "      totalGoalsCompleted: {\n" +
            "        $avg: \"$performance.goalsCompleted\"\n" +
            "      },\n" +
            "      averageFeedbackScore: {\n" +
            "        $avg: \"$performance.feedbackScore\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "]";

    @Query(performanceByDept)
    public List<PerformanceMetric> getPerformanceByDepartment();
}