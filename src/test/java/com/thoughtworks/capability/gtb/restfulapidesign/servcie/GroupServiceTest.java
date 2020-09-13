package com.thoughtworks.capability.gtb.restfulapidesign.servcie;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GroupServiceTest {
    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllGroupsSuccessfully() {
        List<Group> returnGroups = new ArrayList<Group>();
        List<Group> expectedGroups = new ArrayList<Group>();

        when(groupRepository.getAllGroups()).thenReturn(returnGroups);

        List<Group> groupsResult = groupService.getAllGroups();

        assertEquals(expectedGroups, groupsResult);
    }
}