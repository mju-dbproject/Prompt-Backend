package com.team4.prompt.project.repository;

import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

    List<Project> findByStatus(ProjectStatus projectStatus);

    List<Project> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Project> findByStartDateBetweenAndClientContaining(LocalDateTime startDate, LocalDateTime endDate, String client);
    List<Project> findByStartDateBetweenAndProjectNumberContaining(LocalDateTime startDate, LocalDateTime endDate, String projectNumber);
    List<Project> findByStartDateBetweenAndNameContaining(LocalDateTime startDate, LocalDateTime endDate, String projectName);
    List<Project> findByClientContaining(String client);
    List<Project> findByProjectNumberContaining(String projectNumber);
    List<Project> findByNameContaining(String projectName);



    @Query("select count(*) from Project where YEAR(createDate) = YEAR(:now) AND MONTH(createDate) = MONTH(:now)")
    int findCreateCountInMonth(LocalDateTime now);
}
