package com.team4.prompt.project.domain;

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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String projectNumber;

    @Column(nullable = false)
    private String name;

    private LocalDateTime createDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ManPower> manPowerList = new ArrayList<>();

    @Column(nullable = false)
    private String client;

    private int budget;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    private String description;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    public Project(String projectNumber, String name, LocalDateTime createDate, LocalDateTime startDate,
                   LocalDateTime endDate, String client, int budget, ProjectStatus status, String description,
                   boolean deleted) {
        this.projectNumber = projectNumber;
        this.name = name;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.budget = budget;
        this.status = status;
        this.description = description;
        this.deleted = deleted;
    }

    public void giveProjectNumber(int createOrder){
        String projectNumber = createDate.getYear() % 100
                + convertMonthToString(createDate.getMonthValue())
                + convertCreatingOrder(createOrder);
        if(projectNumber.length()!=6) {
            throw new IllegalArgumentException("");
        }
        this.projectNumber = projectNumber;
    }

    private String convertMonthToString(int month){
        if(month<10){
            return "0" + month;
        }
        return String.valueOf(month);
    }

    private String convertCreatingOrder(int createOrder) {
        createOrder++;
        if(createOrder<10){
            return "0"+createOrder;
        }
        return String.valueOf(createOrder);
    }
}
