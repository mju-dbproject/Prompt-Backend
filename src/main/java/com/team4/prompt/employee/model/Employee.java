package com.team4.prompt.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String registerNumber;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private int experienceYear;

    @Column(nullable = false)
    private LocalDateTime enteringDate;

    private String skill;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    private Employee(String userId, String email, String password, String name, String registerNumber, String phoneNumber,
                     String education, int experienceYear, LocalDateTime enteringDate, String skill, Position position, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerNumber = registerNumber;
        this.phoneNumber = phoneNumber;
        this.education = education;
        this.experienceYear = experienceYear;
        this.enteringDate = enteringDate;
        this.skill = skill;
        this.position = position;
        this.role = role;
    }
}
