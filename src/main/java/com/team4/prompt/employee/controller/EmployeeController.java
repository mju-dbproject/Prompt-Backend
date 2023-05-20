package com.team4.prompt.employee.controller;

import com.team4.prompt.employee.controller.dto.EmployeeListDto;
import com.team4.prompt.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getAllEmployee(){ return employeeService.getAllEmployee();}

    @GetMapping("/admin/developer")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getDeveloperEmployee(){ return employeeService.getDeveloperEmployee();}

    @GetMapping("/admin/else")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getElseEmployee(){ return employeeService.getElseEmployee();}

    @PostMapping("/admin/promotion")
    @PreAuthorize("hasRole('ADMIN')")
    public void promoteEmployee(@RequestParam String userId) throws Exception {
        employeeService.promoteEmployee(userId);
    }
}