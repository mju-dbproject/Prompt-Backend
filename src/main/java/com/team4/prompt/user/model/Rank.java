package com.team4.prompt.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum Rank {
    DIRECTOR("임원", 0),
    SENIOR_MANAGER("수석", 1),
    MANAGER("책임", 2),
    SENIOR_ASSISTANCE("선임", 3),
    ASSISTANCE("사원", 4);

    private final String rankName;
    private final int rankNumber;
    Rank(String rankName, int rankNumber) {
        this.rankName = rankName;
        this.rankNumber = rankNumber;
    }

    @JsonCreator
    public static Rank of(String rankName) {
        return Arrays.stream(Rank.values())
                .filter(position -> Objects.nonNull(rankName))
                .filter(position -> Objects.equals(position.getRankName(), rankName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
