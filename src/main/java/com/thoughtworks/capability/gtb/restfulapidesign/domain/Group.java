package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    private Integer id;

    private String name;

    private String note;

    private List<Student> studentList;

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
