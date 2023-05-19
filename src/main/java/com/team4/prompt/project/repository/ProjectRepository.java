package com.team4.prompt.project.repository;

import com.team4.prompt.project.domain.Project;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select count(*) from Project where YEAR(createDate) = YEAR(:now) AND MONTH(createDate) = MONTH(:now)")
    int findCreateCountInMonth(LocalDateTime now);
}
