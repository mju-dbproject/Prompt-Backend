package com.team4.prompt.project.cotroller.dto;

import com.team4.prompt.project.domain.Project;
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

    private LocalDateTime createDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<ManpowerResponse> manpowerList;

    public static ProjectDetailsDto from(Project project) {
        return new ProjectDetailsDto(project.getId(), project.getProjectNumber(),
                project.getName(), project.getClient(), project.getBudget(),
                project.getStatus().getStatusName(), project.getDescription(),
                project.getCreateDate(), project.getStartDate(), project.getEndDate(),
                project.getManPowerList().stream().map(ManpowerResponse::from).toList());
    }
}
