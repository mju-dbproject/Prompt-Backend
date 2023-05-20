package com.team4.prompt.user.model;

import com.team4.prompt.manpower.ManPower;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String employeeNumber;

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

    @Column(name = "c_rank", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ManPower> manPowerList = new ArrayList<>();

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    private User(String userId, String email, String password, String name, String registerNumber, String phoneNumber,
                 String education, int experienceYear, LocalDateTime enteringDate, String skill, Position position, Rank rank, Role role) {
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
        this.rank = rank;
        this.role = role;
    }

    public void giveEmployeeNumber(int enteringOrder) {
        String number = String.valueOf(enteringDate.getYear() % 100)
                + rank.getRankNumber()
                + position.getPositionNumber()
                + convertEnteringOrder(enteringOrder);
        if(number.length()!=6) {
            throw new IllegalArgumentException("");
        }
        this.employeeNumber = number;
    }

    private String convertEnteringOrder(int enteringOrder) {
        enteringOrder++;
        if(enteringOrder<10){
            return "0"+enteringOrder;
        }
        return String.valueOf(enteringOrder);
    }
}
