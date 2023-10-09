package com.robotdreams.mapper;

import com.robotdreams.model.Student;
import com.robotdreams.model.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOToStudentEntity implements BaseMapper<Student, StudentDTO>{


    @Override
    public Student map(StudentDTO studentDTO, Object... params) {

        Student student = new Student();
        student.setAddress(studentDTO.getAddress());
        student.setName(studentDTO.getName());
        student.setGender(studentDTO.getGender());
        student.setBirthDate(studentDTO.getBirthDate());

        return student;

    }

}
