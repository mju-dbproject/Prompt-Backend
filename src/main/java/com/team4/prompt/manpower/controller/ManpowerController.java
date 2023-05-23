package com.team4.prompt.manpower.controller;

import com.team4.prompt.manpower.controller.dto.ManpowerListDto;
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
    public ManpowerListDto getAllManpower(){
        return manpowerService.getAvailableEmployeeForNewProject();
    }

    @GetMapping("/admin/new/available/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ManpowerListDto searchManpower(@RequestParam String type, String keyword){
        return manpowerService.searchAvailableManpowerForNewProject(type, keyword);
    }
    @PostMapping("/admin/edit/available/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ManpowerListDto getAllManpower(@RequestBody String projectNumber){
        return manpowerService.getAvailableEmployeeForProject(projectNumber);
    }

    @PostMapping("/admin/edit/available/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ManpowerListDto searchManpower(@RequestParam String type, String keyword, @RequestBody String projectNumber ){
        return manpowerService.SearchAvailableManpowerForProject(projectNumber,type, keyword);
    }
}
