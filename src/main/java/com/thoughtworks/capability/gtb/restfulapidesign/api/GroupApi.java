package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.servcie.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupApi {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups/grouping")
    public void grouping() {
        groupService.groupingStudents();
    }
}
