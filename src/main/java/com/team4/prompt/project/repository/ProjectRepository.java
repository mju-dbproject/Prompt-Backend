package com.team4.prompt.project.repository;

import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

    Optional<Project> findById(Long id);
    List<Project> findByStatus(ProjectStatus projectStatus);
    @Query("select count(*) from Project where YEAR(createDate) = YEAR(:now) AND MONTH(createDate) = MONTH(:now)")
    int findCreateCountInMonth(LocalDateTime now);
}
