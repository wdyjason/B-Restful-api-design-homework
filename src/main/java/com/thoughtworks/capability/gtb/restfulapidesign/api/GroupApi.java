package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.servcie.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupApi {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups/grouping")
    public void grouping() {
        groupService.groupingStudents();
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
}
