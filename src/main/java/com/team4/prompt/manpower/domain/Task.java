package com.team4.prompt.manpower.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum Task {
    PM("PM"),
    PL("PL"),
    ANALYSIS("분석가"),
    DESIGNER("디자이너"),
    PROGRAMMER("프로그래머"),
    TESTER("테스터");

    private final String taskName;

    Task(String taskName) {
        this.taskName = taskName;
    }

    @JsonCreator
    public static Task of(String taskName) {
        return Arrays.stream(Task.values())
                .filter(position -> Objects.nonNull(taskName))
                .filter(position -> Objects.equals(position.getTaskName(), taskName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
