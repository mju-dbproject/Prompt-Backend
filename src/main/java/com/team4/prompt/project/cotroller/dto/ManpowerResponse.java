package com.team4.prompt.project.cotroller.dto;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManpowerResponse {
    private Long id;
    private String employeeNumber;
    private String task;
    private String name;
    private String position;
    private String rank;
    private String skill;

    public static ManpowerResponse from(ManPower manPower) {
        User user = manPower.getUser();
        return new ManpowerResponse(manPower.getId(), user.getEmployeeNumber(),
                manPower.getTask().getTaskName(), user.getName(), user.getPosition().getPositionName(),
                user.getRank().getRankName(), user.getSkill());
    }
}
