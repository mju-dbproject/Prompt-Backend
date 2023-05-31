package com.team4.prompt.manpower.controller;

import com.team4.prompt.employee.controller.dto.EmployeeListDto;
import com.team4.prompt.manpower.service.ManpowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manpower")
@RequiredArgsConstructor
public class ManpowerController {

    private final ManpowerService manpowerService;

    @GetMapping("/admin/new/available/all")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getAllManpower(){
        return manpowerService.getAvailableEmployeeForNewProject();
    }

    @GetMapping("/admin/new/available/search")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto searchManpower(@RequestParam String type, String keyword){
        return manpowerService.searchAvailableManpowerForNewProject(type, keyword);
    }
    @GetMapping("/admin/edit/available/all/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto getAllManpower(@PathVariable Long id){
        return manpowerService.getAvailableEmployeeForProject(id);
    }

    @GetMapping("/admin/edit/available/search/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeListDto searchManpower(@PathVariable Long id ,@RequestParam String type, String keyword ){
        return manpowerService.SearchAvailableManpowerForProject(id,type, keyword);
    }
}
