package com.team4.prompt.manpower.controller;

import com.team4.prompt.manpower.controller.dto.ManpowerDto;
import com.team4.prompt.manpower.service.ManpowerService;
import com.team4.prompt.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manpower")
@RequiredArgsConstructor
public class ManpowerController {

    private final ManpowerService manpowerService;

    @PostMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ManpowerDto> getAllManpower(@RequestBody String projectNumber){
        List<User> manpowerEmployees =  manpowerService.getAvailableEmployeeForProject(projectNumber);
        return manpowerEmployees.stream()
                .map(this::convertToManpowerDto)
                .collect(Collectors.toList());
    }

    private ManpowerDto convertToManpowerDto(User user){
        ManpowerDto dto = new ManpowerDto();
        dto.setEmployeeNumber(user.getEmployeeNumber());
        dto.setName(user.getName());
        dto.setSkill(user.getSkill());
        dto.setRank(user.getRank().getRankName());
        dto.setPosition(user.getPosition().name());

        return dto;
    }
}
