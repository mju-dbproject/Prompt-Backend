package com.team4.prompt.evaluation.repository;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;

public interface EvaluationRepository {
    boolean existsByProjectAndManPower(Project project, ManPower manpower);

}