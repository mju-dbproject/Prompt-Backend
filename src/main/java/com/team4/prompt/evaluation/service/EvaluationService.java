package com.team4.prompt.evaluation.service;

import com.team4.prompt.evaluation.controller.dto.CanEvaluateProject;
import com.team4.prompt.evaluation.controller.dto.CanEvaluatedProjectList;
import com.team4.prompt.evaluation.controller.dto.EvaluatedDto;
import com.team4.prompt.evaluation.controller.dto.EvaluationDto;
import com.team4.prompt.evaluation.domain.Evaluation;
import com.team4.prompt.evaluation.repository.EvaluationRepository;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.repository.ProjectRepository;
import com.team4.prompt.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.team4.prompt.manpower.domain.Task.PM;
import static com.team4.prompt.user.model.Role.ADMIN;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final ManpowerRepository manpowerRepository;
    private final ProjectRepository projectRepository;

    //사용자가 이미 평가를 완료한 프로젝트 제외, 종료 상태인 프로젝트 리스트 가져오기
    public CanEvaluatedProjectList getProjectsForEvaluation(User user) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);

        List<CanEvaluateProject> projectList = manpowerList.stream()
                .filter(manPower -> manPower.getProject().getStatus().equals(ProjectStatus.FINISH))
                .filter(manPower -> !evaluationRepository.existsByProjectAndManPower(manPower.getProject(), manPower))
                .map(manPower -> new CanEvaluateProject(manPower.getProject().getId(), manPower.getProject().getName(),
                        getAvailableEvaluationType(manPower), getProjectPeer(manPower.getProject(), manPower)))
                .toList();
        return new CanEvaluatedProjectList(projectList);
    }

    //가능한 평가 종류 리스트
    public List<String> getAvailableEvaluationType(ManPower manPower) {
        List<String> availableTypeList = new ArrayList<>();

        if(manPower.getTask().equals(PM)){
            availableTypeList.add("PM 평가");
            availableTypeList.add("발주처 평가");
        }
        else {
            availableTypeList.add("동료 평가");
        }

        return availableTypeList;
    }

    public List<EvaluatedDto> getProjectPeer(Project project, ManPower evaluating) {

        List<ManPower> coworker = manpowerRepository.findManPowerWithEmployeeByProject(project);
        return coworker.stream()
                .filter(manPower -> !manPower.equals(evaluating))
                .map(manPower -> new EvaluatedDto(
                manPower.getId(), manPower.getUser().getEmployeeNumber(), manPower.getUser().getName()
        )).toList();
    }

    //선택된 프로젝트 정보 출력
    public ProjectDto getProjectDetails(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return new ProjectDto(project);
    }

    public void saveEvaluation(User user, EvaluationDto evaluationDto) {
        Long projectId = evaluationDto.getProjectId();
        Long evaluatedId = evaluationDto.getEvaluatedId();

        Optional<ManPower> manPowerOp = manpowerRepository.findById(evaluatedId);

        ManPower manPower = manPowerOp.orElseThrow(() -> new NoSuchElementException("" ));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        LocalDateTime endDate = project.getEndDate();
        evaluationDto.setEndDate(endDate);

        Evaluation evaluation = Evaluation.builder()
                .project(project)
                .manPower(manPower)
                .performance(evaluationDto.getPerformance())
                .communication(evaluationDto.getCommunication())
                .contents(evaluationDto.getContents())
                .type(evaluationDto.getType())
                .endDate(evaluationDto.getEndDate())
                .build();

        evaluationRepository.save(evaluation);

    }

    //프로젝트별 평가 조회
    public List<EvaluationDto> getEvaluationsByProjectId(Long projectId) {
        List<Evaluation> evaluations = evaluationRepository.findByProjectId(projectId);
        List<EvaluationDto> evaluationDtos = new ArrayList<>();

        for (Evaluation evaluation : evaluations) {
            EvaluationDto evaluationDto = new EvaluationDto(
                    evaluation.getProject().getId(),
                    evaluation.getManPower().getId(),
                    evaluation.getPerformance(),
                    evaluation.getCommunication(),
                    evaluation.getContents(),
                    evaluation.getType()
            );
            evaluationDto.setEndDate(evaluation.getProject().getEndDate());
            evaluationDtos.add(evaluationDto);
        }

        return evaluationDtos;
    }


    //직원별 평가 조회
    public List<EvaluationDto> getEvaluationsByEvaluatedId(Long evaluatedId) {
        List<Evaluation> evaluations = evaluationRepository.findByEvaluatedId(evaluatedId);
        List<EvaluationDto> evaluationDtos = new ArrayList<>();

            for (Evaluation evaluation : evaluations) {
                EvaluationDto evaluationDto = new EvaluationDto(
                        evaluation.getProject().getId(),
                        evaluation.getManPower().getId(),
                        evaluation.getPerformance(),
                        evaluation.getCommunication(),
                        evaluation.getContents(),
                        evaluation.getType()
                );
                evaluationDto.setEndDate(evaluation.getProject().getEndDate());
                evaluationDtos.add(evaluationDto);
            }
            return evaluationDtos;

    }
}
