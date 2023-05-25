package com.team4.prompt.project.repository;

import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepositoryCustom {
    List<Project> findBySearchOption(Integer status, String projectNumber, String client, String projectName, LocalDateTime startDate, LocalDateTime endDate);
}
