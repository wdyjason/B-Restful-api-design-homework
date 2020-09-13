package com.thoughtworks.capability.gtb.restfulapidesign.servcie;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.excepiton.GroupNotFoundException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.capability.gtb.restfulapidesign.constants.GroupConstants.GROUP_COUNT;

@Service
@AllArgsConstructor
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentService studentService;


    public List<Group> groupingStudents() {
        List<Student> students =studentService.findAllStudentsWithGender(null);
        Collections.shuffle(students);
        List<Group> groups = groupRepository.getGroups();
        if (!groups.get(0).getStudentList().isEmpty()) {
            clearStudentGroupInfo(groups);
        }

        int groupIndex = 0;
        for (Student student : students) {
            groups.get(groupIndex).getStudentList().add(student);
            if (groupIndex == GROUP_COUNT - 1) {
                groupIndex = 0;
            } else {
                groupIndex ++;
            }
        }
        return groups;
    }


    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public void clearStudentGroupInfo(List<Group> groups) {
        for (Group group : groups) {
            group.getStudentList().clear();
        }
    }

    public void updateName(Integer id, String newName) throws GroupNotFoundException {
        Optional<Group> targetGroup = groupRepository.findById(id);
        if (!targetGroup.isPresent()) {
            throw new GroupNotFoundException("group not found!");
        }
        groupRepository.updateGroupNameById(id, newName);

    }
}
