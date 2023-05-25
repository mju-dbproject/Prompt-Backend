package com.team4.prompt.project.domain;

import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum ProjectStatus {
    READY("준비중", 1),
    PROGRESS("진행중", 2),
    FINISH("완료", 3),
    CANCEL("취소", 4);

    private final String statusName;
    private final int statusNumber;

    ProjectStatus(String statusName, int statusNumber) {
        this.statusName = statusName;
        this.statusNumber = statusNumber;
    }

    public static ProjectStatus of(int statusNumber) {
        return Arrays.stream(ProjectStatus.values())
                .filter(Objects::nonNull)
                .filter(status -> Objects.equals(status.statusNumber, statusNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }


}
