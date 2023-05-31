package com.team4.prompt.evaluation.domain;

import com.team4.prompt.project.domain.ProjectStatus;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum EvaluationType {
    PEER("동료 평가"),
    PM("PM 평가"),
    CLIENT("발주처 평가");

    private final String typeName;

    EvaluationType(String typeName) {
        this.typeName = typeName;
    }

    public static EvaluationType of(String typeName) {
        System.out.println(typeName);
        return Arrays.stream(EvaluationType.values())
                .filter(Objects::nonNull)
                .filter(type -> Objects.equals(type.getTypeName(), typeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
