package com.team4.prompt.employee.service;

import com.team4.prompt.employee.controller.dto.EmployeeCreateRequest;
import com.team4.prompt.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(EmployeeCreateRequest employeeCreateRequest) {
        employeeRepository.save(employeeCreateRequest.newUser(passwordEncoder));
    }
}
