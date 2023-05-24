package com.team4.prompt.evaluation.service;

import com.team4.prompt.evaluation.repository.EvaluationRepository;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.repository.ProjectRepository;
import com.team4.prompt.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.team4.prompt.manpower.domain.Task.PM;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final ManpowerRepository manpowerRepository;
    private final ProjectRepository projectRepository;

    //사용자가 이미 평가를 완료한 프로젝트 제외, 종료 상태인 프로젝트 리스트 가져오기
    public ProjectListDto getProjectsForEvaluation(User user) {
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
        return new ProjectListDto(projectDtoList);
    }

    //관리자가 가능한 평가 종류
    public List<String> getAvailableEvaluationsForAdmin(Long projectId, User user) {
        List<String> availableEvaluations = new ArrayList<>();
        ManPower manPower = manpowerRepository.findByProjectIdAndUser(projectId, user);

        if (manPower.getTask() == PM) {
            availableEvaluations.add("PM 평가");
            availableEvaluations.add("동료 평가");
            availableEvaluations.add("발주처 평가");
        } else {
            availableEvaluations.add("발주처 평가");
       }
        return availableEvaluations;
    }

    public List<String> getAvailableEvaluationsForEmployee(User user) {
        List<String> availableEvaluations = new ArrayList<>();
        availableEvaluations.add("동료 평가");

        return availableEvaluations;
    }

    public List<String> getProjectPeer(Long projectId, User user) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            // 프로젝트가 존재하지 않는 경우
            return Collections.emptyList();
        }

        Project project = projectOptional.get();
        List<ManPower> peers = manpowerRepository.findPeersByProject(project);

        List<String> peerList = new ArrayList<>();
        for (ManPower manPower : peers) {
            if (!manPower.getUser().getId().equals(user.getId())) {  // 현재 사용자는 목록에서 제외
                String peerInfo = manPower.getUser().getEmployeeNumber() + "+" + manPower.getUser().getName();
                peerList.add(peerInfo);
            }
        }

        return peerList;
    }

    //선택된 프로젝트 정보 출력
    //public ProjectDto getProjectDetails(Long projectId) {   //전달 값?
     //   Project project = projectRepository.findById(projectId)
     //           .orElseThrow(() -> new IllegalArgumentException(""));

    //    return new ProjectDto(project);
    //}

    //public void saveEvaluation(User user, EvaluationDto evaluationDto) {
    //    Project project = projectRepository.findById(evaluationDto.getProjectId())
    //            .orElseThrow(() -> new IllegalArgumentException(""));

    //    ManPower evaluatedUser = manpowerRepository.findByProjectAndEvaluatedId(project, evaluationDto.getEvaluatedId());

    //    if (evaluationDto.getType().equals("PM 평가")) {
    //        Evaluation evaluation = new Evaluation();
    //        evaluationRepository.save(evaluation);
    //    } else if (evaluationDto.getType().equals("동료 평가")) {
    //        List<ManPower> projectPeers = manpowerRepository.findPeersByProject(project);

    //        for (ManPower peer : projectPeers) {
    //            if (!peer.getUser().getName().equals(evaluatedUser)) {
    //                Evaluation evaluation = new Evaluation(project, peer, evaluationType);
    //                evaluationRepository.save(evaluation);
    //            }
    //        }
    //    } else if (evaluationDto.getType().equals("고객 평가")) {
    //        Evaluation evaluation = new Evaluation(project, evaluatedManPower, evaluationType);
    //        evaluationRepository.save(evaluation);
    //    } else {
    //        throw new IllegalArgumentException("");
    //    }

    //}
}