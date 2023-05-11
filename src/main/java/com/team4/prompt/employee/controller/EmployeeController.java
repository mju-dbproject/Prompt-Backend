package com.team4.prompt.employee.controller;

import com.team4.prompt.employee.controller.dto.EmployeeCreateRequest;
import com.team4.prompt.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public void join(@RequestBody @Valid EmployeeCreateRequest employeeCreateRequest) {
        employeeService.join(employeeCreateRequest.newUser(passwordEncoder));
    }
}
