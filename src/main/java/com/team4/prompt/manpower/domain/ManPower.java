package com.team4.prompt.manpower.domain;

import com.team4.prompt.project.domain.Project;
import com.team4.prompt.user.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManPower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public ManPower(Task task, User user, Project project, LocalDateTime startDate, LocalDateTime endDate) {
        this.task = task;
        this.user = user;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void finish() {
        if(endDate!=null){
            return;
        }
        this.endDate = LocalDateTime.now();
    }
}
