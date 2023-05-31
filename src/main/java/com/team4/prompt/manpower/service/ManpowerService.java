package com.team4.prompt.manpower.service;

import com.team4.prompt.employee.controller.dto.EmployeeDto;
import com.team4.prompt.employee.controller.dto.EmployeeListDto;
import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.Role;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManpowerService {
    private final ManpowerRepository manpowerRepository;
    private final UserRepository userRepository;

    public ManPower findManpowerById(Long id) {
        return manpowerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
    }

    public boolean isEmployeeAvailable(User employee){
        List<ManPower> manPowers = manpowerRepository.findByUser(employee).stream().filter(manPower -> manPower.getEndDate() == null).toList();
        return manPowers.size() < 3;
    }

    public void checkAlreadyManpower(User user, Project project) {
        if(manpowerRepository.countByUserAndProject(user, project)!=0){
            throw new IllegalArgumentException("");
        }
    }

    public EmployeeListDto getAvailableEmployeeForProject(Long projectId){
        List<User> availableEmployees = new ArrayList<>();
        List<ManPower> assignedEmployees = manpowerRepository.findByProjectId(projectId);
        List<Task> PMPLEmployees = Arrays.asList(Task.PM,Task.PL);
        List<User> allEmployees = userRepository.findByRoleNot(Role.ADMIN);

        for (User employee : allEmployees){
            boolean isAssignedToProject = assignedEmployees.stream()
                    .anyMatch(manPower -> manPower.getUser().equals(employee));
            boolean isAssignedToPMPLTasks = manpowerRepository.findByUserAndTaskIn(employee,PMPLEmployees)
                    .stream()
                    .anyMatch(manPower -> !manPower.getProject().getId().equals(projectId) && manPower.getProject().getStatus() != ProjectStatus.FINISH);
            if (!isAssignedToProject && !isAssignedToPMPLTasks & isEmployeeAvailable(employee)){
                availableEmployees.add(employee);
            }
        }
        List<EmployeeDto> employeeDtoList = availableEmployees.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }
    public EmployeeListDto SearchAvailableManpowerForProject(Long projectId, String type, String keyword) {
        List<User> availableEmployees = new ArrayList<>();
        List<ManPower> assignedEmployees = manpowerRepository.findByProjectId(projectId);
        List<Task> PMPLEmployees = Arrays.asList(Task.PM,Task.PL);
        List<User> allEmployees = new ArrayList<>();
        switch (type) {
            case "name" -> allEmployees = userRepository.findByRoleNotAndNameContaining(Role.ADMIN, keyword);
            case "employeeNumber" -> allEmployees = userRepository.findByRoleNotAndEmployeeNumberContaining(Role.ADMIN, keyword);
            case "rank" -> allEmployees = userRepository.findByRoleNotAndRank(Role.ADMIN, Rank.of(keyword));
            case "position" -> allEmployees = userRepository.findByRoleNotAndPosition(Role.ADMIN, Position.of(keyword));
            case "skill" -> allEmployees = userRepository.findByRoleNotAndSkillContainingIgnoreCase(Role.ADMIN, keyword);
        }
            for (User employee : allEmployees){
                boolean isAssignedToProject = assignedEmployees.stream()
                        .anyMatch(manPower -> manPower.getUser().equals(employee));
                boolean isAssignedToPMPLTasks = manpowerRepository.findByUserAndTaskIn(employee,PMPLEmployees)
                        .stream()
                        .anyMatch(manPower -> !manPower.getProject().getId().equals(projectId) && manPower.getProject().getStatus() != ProjectStatus.FINISH);
                if (!isAssignedToProject && !isAssignedToPMPLTasks & isEmployeeAvailable(employee)){
                    availableEmployees.add(employee);
                }
            }
        List<EmployeeDto> employeeDtoList = availableEmployees.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }

    public EmployeeListDto getAvailableEmployeeForNewProject() {
        List<User> availableEmployees = new ArrayList<>();
        List<Task> PMPLEmployees = Arrays.asList(Task.PM, Task.PL);
        List<User> allEmployees = userRepository.findByRoleNot(Role.ADMIN);

        for (User employee : allEmployees) {
            boolean isAssignedToPMPLTasks = manpowerRepository.findByUserAndTaskIn(employee, PMPLEmployees)
                    .stream()
                    .anyMatch(manPower -> manPower.getProject() != null && manPower.getProject().getStatus() != ProjectStatus.FINISH);
            if (!isAssignedToPMPLTasks && isEmployeeAvailable(employee)) {
                availableEmployees.add(employee);
            }
        }

        List<EmployeeDto> employeeDtoList = availableEmployees.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }

    public EmployeeListDto searchAvailableManpowerForNewProject(String type, String keyword) {
        List<User> availableEmployees = new ArrayList<>();
        List<Task> PMPLEmployees = Arrays.asList(Task.PM, Task.PL);
        List<User> allEmployees = new ArrayList<>();

        switch (type) {
            case "name" -> allEmployees = userRepository.findByRoleNotAndNameContaining(Role.ADMIN, keyword);
            case "employeeNumber" -> allEmployees = userRepository.findByRoleNotAndEmployeeNumberContaining(Role.ADMIN, keyword);
            case "rank" -> allEmployees = userRepository.findByRoleNotAndRank(Role.ADMIN, Rank.of(keyword));
            case "position" -> allEmployees = userRepository.findByRoleNotAndPosition(Role.ADMIN, Position.of(keyword));
            case "skill" -> allEmployees = userRepository.findByRoleNotAndSkillContainingIgnoreCase(Role.ADMIN, keyword);
        }
        for (User employee : allEmployees) {
            boolean isAssignedToPMPLTasks = manpowerRepository.findByUserAndTaskIn(employee, PMPLEmployees)
                    .stream()
                    .anyMatch(manPower -> manPower.getProject() != null && manPower.getProject().getStatus() != ProjectStatus.FINISH);
            if (!isAssignedToPMPLTasks && isEmployeeAvailable(employee)) {
                availableEmployees.add(employee);
            }
        }

        List<EmployeeDto> employeeDtoList = availableEmployees.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }
}
