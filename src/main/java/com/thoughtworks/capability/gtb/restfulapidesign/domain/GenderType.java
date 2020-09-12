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

    public static GenderType getGenderType(String gender) {
        if (gender.equals("男")) return GenderType.MALE;
        return GenderType.FEMALE;
    }

}
