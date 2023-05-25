package com.team4.prompt.evaluation.service;

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
    public List<ProjectDto> getProjectsForEvaluation(User user) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for (ManPower manpower : manpowerList) {
            Project project = manpower.getProject();

            if (project.getStatus() == ProjectStatus.FINISH) {
                boolean isEvaluated = evaluationRepository.existsByProjectAndManPower(project, manpower);

                if (!isEvaluated) {
                    ProjectDto projectDto = new ProjectDto(project);
                    projectDtoList.add(projectDto);
                }
            }
        }
        return projectDtoList;
    }

    //가능한 평가 종류 리스트
    public List<String> getAvailableEvaluations(Long projectId, User user) {
        List<String> availableEvaluations = new ArrayList<>();
        ManPower manPower = manpowerRepository.findByProjectIdAndUser(projectId, user);

        if(user.getRole() == ADMIN) {
            if (manPower.getTask() == PM) {
                availableEvaluations.add("PM 평가");
                availableEvaluations.add("동료 평가");
                availableEvaluations.add("발주처 평가");
            } else {
                availableEvaluations.add("발주처 평가");
            }
        } else { availableEvaluations.add("동료 평가"); }

        return availableEvaluations;
    }

    public List<String> getProjectPeer(Long projectId, User user) {
        Optional<Project> projectOp = projectRepository.findById(projectId);
        if (projectOp.isEmpty()) {
            // 프로젝트가 존재하지 않는 경우
            return Collections.emptyList();
        }

        Project project = projectOp.get();
        List<ManPower> peers = manpowerRepository.findPeersByProject(project);

        List<String> peerList = new ArrayList<>();
        for (ManPower manPower : peers) {
            if (!manPower.getUser().getId().equals(user.getId())) {  // 현재 사용자는 목록에서 제외
                String peerInfo = manPower.getUser().getEmployeeNumber() + " " + manPower.getUser().getName();
                peerList.add(peerInfo);
            }
        }

        return peerList;
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