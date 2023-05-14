package com.team4.prompt.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum Position {
    DIRECTOR("경영진", 0),
    DEVELOPER("개발자", 1),
    SALES("영업관리", 2),
    BUSINESS("사업관리", 3),
    ADMINISTRATION("경영 관리", 4),
    MARKETING("마켓팅", 5),
    RESEARCH("연구개발", 6);

    private final String positionName;
    private final int positionNumber;

    Position(String positionName, int positionNumber) {
        this.positionName = positionName;
        this.positionNumber = positionNumber;
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
