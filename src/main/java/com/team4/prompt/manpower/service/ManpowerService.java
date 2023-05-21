package com.team4.prompt.manpower.service;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.manpower.repository.ManpowerRepository;
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

    public List<User> getAvailableEmployeeForProject(String projectNumber){
        List<User> availableEmployees = new ArrayList<>();

        List<ManPower> assignedEmployees = manpowerRepository.findByProject_ProjectNumber(projectNumber);

        List<Task> PMPLEmployees = Arrays.asList(Task.PM,Task.PL);

        List<User> allEmployees = userRepository.findAllByRoleNot(Role.ADMIN);

        for (User employee : allEmployees){
            boolean isAssignedToProject = assignedEmployees.stream()
                    .anyMatch(manPower -> manPower.getUser().equals(employee));
            boolean isAssignedToPMPLTasks = manpowerRepository.findByUserAndTaskIn(employee,PMPLEmployees)
                    .stream()
                    .anyMatch(manPower -> !manPower.getProject().getProjectNumber().equals(projectNumber));
            if (!isAssignedToProject && !isAssignedToPMPLTasks & isEmployeeAvailable(employee)){
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }
    public boolean isEmployeeAvailable(User employee){
        List<ManPower> assignedProjects = manpowerRepository.findByUser(employee);
        return assignedProjects.size() < 3;
    }
}
