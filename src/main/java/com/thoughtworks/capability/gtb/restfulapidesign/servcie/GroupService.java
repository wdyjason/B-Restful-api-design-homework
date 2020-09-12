package com.thoughtworks.capability.gtb.restfulapidesign.servcie;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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


}
