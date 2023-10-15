package com.robotdreams.schoolservice.mapper;

import com.robotdreams.schoolservice.model.Student;
import com.robotdreams.schoolservice.model.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentEntityToStudentDTO implements BaseMapper<StudentDTO, Student> {


    @Override
    public StudentDTO map(Student student, Object... params) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAddress(student.getAddress());
        studentDTO.setName(student.getName());
        studentDTO.setGender(student.getGender());
        studentDTO.setBirthDate(student.getBirthDate());
        studentDTO.setId(student.getId());

        return studentDTO;

    }

}
