package ru.sfedu.diplomabackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import static ru.sfedu.diplomabackend.Constants.*;

import java.util.stream.Stream;

public enum Priority {

    PRIORITY_1(HIGH_CONST),
    PRIORITY_2(MEDIUM_CONST),
    PRIORITY_3(LOW_CONST);

    private String pr;

    private Priority(String pr) {
        this.pr=pr;
    }

    @JsonCreator
    public static Priority decode(final String pr) {
        return Stream.of(Priority.values()).filter(targetEnum -> targetEnum.pr.equals(pr)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return pr;
    }


}
