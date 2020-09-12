package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderType {
    MALE("男"), FEMALE("女");

    private String gender;

    GenderType(String gender) {
        this.gender = gender;
    }

    @JsonValue
    public String getGender() {
        return this.gender;
    }
}
