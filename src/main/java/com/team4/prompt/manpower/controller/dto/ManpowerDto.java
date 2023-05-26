package com.team4.prompt.manpower.controller.dto;

import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public ManpowerDto(User user){
        this.id = user.getId();
        this.employeeNumber = user.getEmployeeNumber();
        this.name = user.getName();
        this.skill = user.getSkill();
        this.rank = user.getRank().getRankName();
        this.position = user.getPosition().getPositionName();
    }
}
