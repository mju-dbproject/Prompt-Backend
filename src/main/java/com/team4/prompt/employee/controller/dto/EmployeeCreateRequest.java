package com.team4.prompt.employee.controller.dto;

import com.team4.prompt.employee.model.Employee;
import com.team4.prompt.employee.model.Position;
import com.team4.prompt.employee.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class EmployeeCreateRequest {
    @NotBlank
    private String userId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String registerNumber;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String education;

    private int experienceYear;

    private String skill;

    private String position;

    public Employee newUser(PasswordEncoder passwordEncoder) {
        return Employee.builder()
                .userId(userId)
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .registerNumber(registerNumber)
                .phoneNumber(phoneNumber)
                .education(education)
                .experienceYear(experienceYear)
                .enteringDate(LocalDateTime.now())
                .skill(skill)
                .position(Position.of(position))
                .role(Role.USER)
                .build();
    }

}
