package com.robot.automation.model;

public enum Result {
    HIT("INCLUDE"), EXCLUDE("EXCLUDE"), REVIEW("REVIEW"), ERROR("ERROR");
    private final String value;

    Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
