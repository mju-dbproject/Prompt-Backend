package com.team4.prompt.employee.service;

import com.team4.prompt.employee.controller.dto.EmployeeDto;
import com.team4.prompt.employee.controller.dto.EmployeeListDto;
import com.team4.prompt.user.model.Position;
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
        List<User> employeeList = userRepository.findAllByOrderByApprovedDesc();
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
}
