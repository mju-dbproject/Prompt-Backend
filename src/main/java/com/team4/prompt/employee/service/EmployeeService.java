package com.team4.prompt.employee.service;

import com.team4.prompt.employee.controller.dto.EmployeeDto;
import com.team4.prompt.employee.controller.dto.EmployeeListDto;
import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.Role;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final UserRepository userRepository;


    public EmployeeListDto getAllEmployee() {
        List<User> employeeList = userRepository.findAllByOrderByApprovedAsc();
        List<EmployeeDto> employeeDtoList = employeeList.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }

    public EmployeeListDto getDeveloperEmployee() {
        List<User> employeeList = userRepository.findByPositionAndApproved(Position.DEVELOPER, true);
        List<EmployeeDto> employeeDtoList = employeeList.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }

    public EmployeeListDto getElseEmployee(){
        List<User> employeeList = userRepository.findByPositionNotAndApproved(Position.DEVELOPER, true);
        List<EmployeeDto> employeeDtoList = employeeList.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }

    @Transactional
    public void promoteEmployee(User user){
        user.promoteEmployee(Role.ADMIN);
        userRepository.save(user);
    }

    public EmployeeListDto searchEmployee(String type, String keyword) {
        List<User> employeeList;
        switch (type) {
            case "name" -> employeeList = userRepository.findByNameContaining(keyword);
            case "employeeNumber" -> employeeList = userRepository.findByEmployeeNumberContaining(keyword);
            case "rank" -> employeeList = userRepository.findByRank(Rank.of(keyword));
            case "position" -> employeeList = userRepository.findByPosition(Position.of(keyword));
            default -> {
                return new EmployeeListDto(List.of());
            }
        }

        List<EmployeeDto> employeeDtoList = employeeList.stream().map(EmployeeDto::new).toList();
        return new EmployeeListDto(employeeDtoList);
    }
}
