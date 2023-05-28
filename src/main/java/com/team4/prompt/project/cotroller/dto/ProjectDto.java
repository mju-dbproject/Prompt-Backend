package com.team4.prompt.project.cotroller.dto;

import com.team4.prompt.project.domain.Project;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class ProjectDto {
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

    public ProjectDto(Project project){
        copyProperties(project, this);
        this.status = project.getStatus().getStatusName();
        this.createDate = project.getCreateDate()!=null ?  project.getCreateDate().toLocalDate() : null;
        this.startDate = project.getStartDate() != null ? project.getStartDate().toLocalDate() : null;
        this.endDate = project.getEndDate() != null ? project.getEndDate().toLocalDate() : null;
    }

}
