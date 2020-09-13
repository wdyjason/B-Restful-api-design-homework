package com.thoughtworks.capability.gtb.restfulapidesign.servcie;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.excepiton.GroupNotFoundException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void shouldUpdateGroupNameSuccessfully() throws GroupNotFoundException {
        Group returnGroups = new Group(1, "test");

        when(groupRepository.findById(1)).thenReturn(Optional.of(returnGroups));
        when(groupRepository.updateGroupNameById(1, "test_u")).thenReturn(true);

        groupService.updateName(1, "test_u");

        verify(groupRepository, times(1)).updateGroupNameById(1, "test_u");
        verify(groupRepository, times(1)).findById(1);
    }

    @Test
    public void shouldUpdateGroupNameFailure() {

        when(groupRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> groupService.updateName(1, "test_u"));
    }
}