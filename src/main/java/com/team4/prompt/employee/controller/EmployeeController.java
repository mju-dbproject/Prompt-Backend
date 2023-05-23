package com.team4.prompt.employee.controller;

import com.team4.prompt.employee.controller.dto.EmployeeListDto;
import com.team4.prompt.employee.service.EmployeeService;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private final UserService userService;

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getAllEmployee(){ return employeeService.getAllEmployee();}

    @GetMapping("/admin/developer")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getDeveloperEmployee(){ return employeeService.getDeveloperEmployee();}

    @GetMapping("/admin/else")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getElseEmployee(){ return employeeService.getElseEmployee();}

    @GetMapping("/admin/search")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto searchEmployee(@RequestParam String type, @RequestParam String keyword){ return  employeeService.searchEmployee(type, keyword);}

    @PostMapping("/admin/promotion")
    @PreAuthorize("hasRole('ADMIN')")
    public void promoteEmployee(@RequestBody User user) {
        User clickedEmployee = userService.findUserById(user.getId());
        employeeService.promoteEmployee(clickedEmployee);
    }
}
