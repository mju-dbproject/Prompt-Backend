package com.team4.prompt.employee.repository;

import com.team4.prompt.employee.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserId(String userId);
}
