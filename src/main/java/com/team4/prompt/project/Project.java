package com.team4.prompt.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
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

    @Column(nullable = false)
    private String client;

    private int budget;

    @Column(nullable = false)
    private ProjectStatus status;

    private String description;

    @Column(nullable = false)
    private boolean deleted;
}
