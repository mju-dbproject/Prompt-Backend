package com.team4.prompt.user.controller.dto;

import com.team4.prompt.user.model.User;
import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class UserCreateRequest {
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
    @NotBlank
    private String position;
    @NotBlank
    private String rank;

    public User newUser(PasswordEncoder passwordEncoder) {
        return User.builder()
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
                .rank(Rank.of(rank))
                .role(Role.WAITING)
                .build();
    }

}
