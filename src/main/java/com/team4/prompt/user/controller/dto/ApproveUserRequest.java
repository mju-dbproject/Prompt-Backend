package com.team4.prompt.user.controller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ApproveUserRequest {
    List<Long> approveIdList;
}
