package com.team4.prompt.evaluation.service;

import com.team4.prompt.evaluation.controller.dto.CanEvaluateProject;
import com.team4.prompt.evaluation.controller.dto.CanEvaluatedProjectList;
import com.team4.prompt.evaluation.controller.dto.DoneEvaluateRequest;
import com.team4.prompt.evaluation.controller.dto.DoneEvaluateResponse;
import com.team4.prompt.evaluation.controller.dto.EvaluatedDto;
import com.team4.prompt.evaluation.controller.dto.EvaluationDetail;
import com.team4.prompt.evaluation.controller.dto.EvaluationDto;
import com.team4.prompt.evaluation.controller.dto.EvaluationInfo;
import com.team4.prompt.evaluation.controller.dto.ProjectInfo;
import com.team4.prompt.evaluation.domain.Evaluation;
import com.team4.prompt.evaluation.domain.EvaluationType;
import com.team4.prompt.evaluation.repository.EvaluationRepository;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import com.team4.prompt.manpower.service.ManpowerService;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.repository.ProjectRepository;
import com.team4.prompt.project.service.ProjectService;
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
    private final ManpowerService manpowerService;
    private final ProjectService projectService;

    //사용자가 이미 평가를 완료한 프로젝트 제외, 종료 상태인 프로젝트 리스트 가져오기
    public CanEvaluatedProjectList getProjectsForEvaluation(User user) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);

        List<CanEvaluateProject> projectList = manpowerList.stream()
                .filter(manPower -> manPower.getProject().getStatus().equals(ProjectStatus.FINISH))
                .filter(manPower -> !checkAlreadyEvaluate(manPower, manPower.getProject()))
                .map(manPower -> new CanEvaluateProject(manPower.getProject().getId(), manPower.getProject().getName(),
                        getAvailableEvaluationType(manPower), getProjectPeer(manPower.getProject(), manPower)))
                .toList();
        return new CanEvaluatedProjectList(projectList);
    }

    public DoneEvaluateResponse checkDone(User user, DoneEvaluateRequest doneEvaluateRequest) {
        Project project = projectService.findProjectById(doneEvaluateRequest.getProjectId());
        ManPower evaluating = manpowerService.findManpowerByUserAndProject(user, project);
        ManPower evaluated = manpowerService.findManpowerById(doneEvaluateRequest.getEvaluatedId());
        EvaluationType evaluationType = EvaluationType.of(doneEvaluateRequest.getEvaluationType());
        boolean done = evaluationRepository.existsByProjectAndEvaluatingAndEvaluatedAndType(project, evaluating, evaluated, evaluationType);
        return new DoneEvaluateResponse(done);
    }

    public EvaluationDetail getEvaluationDetails(User user, DoneEvaluateRequest doneEvaluateRequest) {
        String performanceQuestion = "해당 직원이 주어진 업무를 충실히 수행했습니까?";
        String communicationQuestion = "해당 직원이 주어진 업무를 충실히 수행했습니까?";
        String contentQuestion = "해당 직원이 다른 팀원들과 적극적으로 소통했습니까?";
        Project project = projectService.findProjectById(doneEvaluateRequest.getProjectId());
        ProjectInfo projectInfo = new ProjectInfo(project.getId(), project.getName(), project.getProjectNumber(), project.getClient(), project.getBudget());
        EvaluationInfo evaluationInfo = new EvaluationInfo(performanceQuestion, communicationQuestion, contentQuestion);
        return new EvaluationDetail(projectInfo, evaluationInfo);
    }

    public boolean checkAlreadyEvaluate(ManPower evaluating, Project project) {
        int count = evaluationRepository.checkAlreadyEvaluate(evaluating, project);
        if(evaluating.getTask().equals(PM)){
            return count==2;
        }
        else{
            return count==1;
        }
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

/**
    public void saveEvaluation(User user, EvaluationDto evaluationDto) {
        Long projectId = evaluationDto.getProjectId();
        Long evaluatedId = evaluationDto.getEvaluatedId();


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
 **/

}
