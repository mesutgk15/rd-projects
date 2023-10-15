package com.robotdreams.schoolservice.mapper;

import com.robotdreams.schoolservice.model.Student;
import com.robotdreams.schoolservice.model.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOToStudentEntity implements BaseMapper<Student, StudentDTO> {


    @Override
    public Student map(StudentDTO studentDTO, Object... params) {

        Student student = new Student();
        if (studentDTO.getId() != 0)
            student.setId(studentDTO.getId());
        if (studentDTO.getAddress() != null)
            student.setAddress(studentDTO.getAddress());
        if (studentDTO.getName() != null)
            student.setName(studentDTO.getName());
        if (studentDTO.getGender() != 0)
            student.setGender(studentDTO.getGender());
        if (studentDTO.getBirthDate() !=null)
            student.setBirthDate(studentDTO.getBirthDate());

        return student;

    }

}
