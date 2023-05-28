package com.team4.prompt.project.cotroller.dto;

import com.team4.prompt.project.domain.Project;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDto {
    private Long id;

    private String projectNumber;

    private String name;

    private String client;

    private int budget;

    private String status;

    private String description;

    private LocalDate createDate;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<ManpowerResponse> manpowerList;

    public static ProjectDetailsDto from(Project project) {
        return new ProjectDetailsDto(project.getId(), project.getProjectNumber(),
                project.getName(), project.getClient(), project.getBudget(),
                project.getStatus().getStatusName(), project.getDescription(),
                project.getCreateDate()!=null ?  project.getCreateDate().toLocalDate() : null,
                project.getStartDate() != null ? project.getStartDate().toLocalDate() : null,
                project.getEndDate() != null ? project.getEndDate().toLocalDate() : null,
                project.getManPowerList().stream().map(ManpowerResponse::from).toList());
    }
}
