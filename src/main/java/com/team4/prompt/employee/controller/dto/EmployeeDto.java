package com.team4.prompt.employee.controller.dto;

import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.Role;
import com.team4.prompt.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String name;
    private String skill;
    private Rank rank;
    private Position position;
    private Role role;
    private Boolean approved;

    public EmployeeDto(User user){
        this.name = user.getName();
        this.skill = user.getSkill();
        this.rank = user.getRank();
        this.position = user.getPosition();
        this.role = user.getRole();
        this.approved = user.isApproved();
    }
}