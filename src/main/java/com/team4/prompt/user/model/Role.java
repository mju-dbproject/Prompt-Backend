package com.team4.prompt.user.model;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Role {
    ADMIN("관리자", Arrays.asList("ROLE_ADMIN", "ROLE_USER")),
    USER("사용자", Arrays.asList("ROLE_USER"));

    private final String roleName;
    private final List<String> authorities;

    Role(String roleName, List<String> authorities) {
        this.roleName = roleName;
        this.authorities = authorities;
    }

}
