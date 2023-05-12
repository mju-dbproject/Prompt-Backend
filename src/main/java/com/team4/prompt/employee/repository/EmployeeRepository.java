package com.team4.prompt.employee.repository;

import com.team4.prompt.employee.model.Employee;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserId(String userId);

    @Query("select count(*) from Employee where date_format(:now, '%Y') = year(enteringDate)")
    Integer findEnteringCountInYear(LocalDateTime now);
}
