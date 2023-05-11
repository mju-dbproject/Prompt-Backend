package com.team4.prompt.employee.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum Position {
    DIRECTOR("임원"),
    SENIOR_MANAGER("수석"),
    MANAGER("책임"),
    SENIOR_ASSISTANCE("선임"),
    ASSISTANCE("사원");

    private final String positionName;
    Position(String positionName) {
        this.positionName = positionName;
    }

    @JsonCreator
    public static Position of(String positionName) {
        return Arrays.stream(Position.values())
                .filter(position -> Objects.nonNull(positionName))
                .filter(position -> Objects.equals(position.getPositionName(), positionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
