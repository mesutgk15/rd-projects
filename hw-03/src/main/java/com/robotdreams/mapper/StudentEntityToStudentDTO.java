package com.robotdreams.mapper;

import com.robotdreams.model.Student;
import com.robotdreams.model.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentEntityToStudentDTO implements BaseMapper<StudentDTO, Student>{


    @Override
    public StudentDTO map(Student student, Object... params) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAddress(student.getAddress());
        studentDTO.setName(student.getName());
        studentDTO.setGender(student.getGender());
        studentDTO.setBirthDate(student.getBirthDate());

        return studentDTO;

    }

}
