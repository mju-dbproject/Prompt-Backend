package com.team4.prompt.user.controller.dto;

import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserInfoDto {

    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String skill;
    private final Rank rank;
    private final Position position;

    @Builder
    public UserInfoDto(User user){
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.skill = user.getSkill();
        this.rank = user.getRank();
        this.position = user.getPosition();
    }
}
