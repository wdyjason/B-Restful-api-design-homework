package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.capability.gtb.restfulapidesign.constants.GroupConstants.GROUP_COUNT;
import static com.thoughtworks.capability.gtb.restfulapidesign.constants.GroupConstants.GROUP_NAMES;

@Repository
public class GroupRepository {
    private final List<Group> groupsDataSource = new ArrayList<>();
    public List<Group> getGroups() {
        if (groupsDataSource.isEmpty()) {
            initGroupDataSource();
        }
        return this.groupsDataSource;
    }

    public void initGroupDataSource() {
        for (int i = 0; i < GROUP_COUNT; i ++) {
            groupsDataSource.add(new Group(i, GROUP_NAMES.get(i)));
        }
    }

    public List<Group> getAllGroups() {
        return this.groupsDataSource;
    }

    public Optional<Group> findById(int id) {
        for (Group group : this.groupsDataSource) {
            if (group.getId().equals(id)) {
                return Optional.of(group);
            }
        }
        return Optional.empty();
    }

    public boolean updateGroupNameById(int id, String newName) {
        for (Group group : this.groupsDataSource) {
            if (group.getId().equals(id)) {
                group.setName(newName);
                return true;
            }
        }
        return false;
    }
}
