package com.team4.prompt.evaluation.repository;

import com.team4.prompt.evaluation.domain.Evaluation;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    boolean existsByProjectAndManPower(Project project, ManPower manpower);
    @Query("SELECT e FROM Evaluation e JOIN e.project p WHERE p.id = :projectId")
    List<Evaluation> findByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT e FROM Evaluation e JOIN e.manPower m WHERE m.id = :evaluatedId")
    List<Evaluation> findByEvaluatedId(@Param("evaluatedId")Long evaluatedId);
}