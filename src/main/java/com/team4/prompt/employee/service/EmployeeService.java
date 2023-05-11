package com.team4.prompt.employee.service;

import com.team4.prompt.employee.model.Employee;
import com.team4.prompt.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void join(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee findByUserId(String userId) {
        return employeeRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));
    }
}
