package com.team4.prompt.evaluation.domain;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;
import jakarta.persistence.*;
import lombok.*;

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
    private ManPower evaluated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluating_id")
    private ManPower evaluating;

    @Column(nullable = false)
    private int performance;

    @Column(nullable = false)
    private int communication;

    private String contents;

    @Column(nullable = false)
    private EvaluationType type;

    @Column(nullable = false)
    private LocalDateTime endDate;


    @Builder
    public Evaluation(Project project, ManPower evaluated, ManPower evaluating, int performance, int communication,
                      String contents, EvaluationType type, LocalDateTime endDate) {
        this.project = project;
        this.evaluated = evaluated;
        this.evaluating = evaluating;
        this.performance = performance;
        this.communication = communication;
        this.contents = contents;
        this.type = type;
        this.endDate = endDate;
    }
}
