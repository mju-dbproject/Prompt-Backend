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
    private Long id;
    private String employeeNumber;
    private String name;
    private String skill;
    private String  rank;
    private String  position;
    private String role;
    private Boolean approved;

    public EmployeeDto(User user){
        this.id =user.getId();
        this.employeeNumber = user.getEmployeeNumber();
        this.name = user.getName();
        this.skill = user.getSkill();
        this.rank = user.getRank().getRankName();
        this.position = user.getPosition().getPositionName();
        this.role = user.getRole().getRoleName();
        this.approved = user.isApproved();
    }
}
