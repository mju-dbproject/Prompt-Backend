package com.team4.prompt.evaluation.domain;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluated_id")
    private ManPower manPower;

    @Column(nullable = false)
    private String performance;

    @Column(nullable = false)
    private String communication;

    private String contents;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDateTime end_date;


    @Builder
    public Evaluation(Project project, ManPower manPower, String performance, String communication,
                      String contents, String type, LocalDateTime end_date) {
        this.project = project;
        this.manPower = manPower;
        this.performance = performance;
        this.communication = communication;
        this.contents = contents;
        this.type = type;
        this.end_date = end_date;
    }
}
