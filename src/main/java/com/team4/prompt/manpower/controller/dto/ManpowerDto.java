package com.team4.prompt.manpower.controller.dto;

import com.team4.prompt.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManpowerDto {
    private Long id;
    private String employeeNumber;
    private String name;
    private String skill;
    private String  rank;
    private String position;
    private String task;

    public ManpowerDto(User user){
        this.id = user.getId();
        this.employeeNumber = user.getEmployeeNumber();
        this.name = user.getName();
        this.skill = user.getSkill();
        this.rank = user.getRank().getRankName();
        this.position = user.getPosition().getPositionName();
    }
}
