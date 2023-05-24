package com.team4.prompt.evaluation.repository;

import com.team4.prompt.evaluation.domain.Evaluation;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    boolean existsByProjectAndManPower(Project project, ManPower manpower);

}