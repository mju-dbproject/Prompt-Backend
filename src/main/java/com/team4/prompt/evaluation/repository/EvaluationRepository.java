package com.team4.prompt.evaluation.repository;

import com.team4.prompt.evaluation.domain.Evaluation;
import com.team4.prompt.evaluation.domain.EvaluationType;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("select count(*) from Evaluation e where e.project = :project and e.evaluating = :evaluating")
    int checkAlreadyEvaluate(ManPower evaluating, Project project);

    boolean existsByProjectAndEvaluatingAndEvaluatedAndType(Project project, ManPower evaluating, ManPower evaluated, EvaluationType type);

}
