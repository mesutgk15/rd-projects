package com.robotdreams.service;

import com.robotdreams.exception.StudentNotFoundException;
import com.robotdreams.model.Student;
import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;

import java.util.List;
import java.util.Set;

public interface StudentService {

    public List<StudentDTO> findAllStudents();

    public StudentDTO findByStudentById(long id);

    public void createNewStudent(StudentDTO studentDTO);
    public void updateStudent(long id, StudentDTO studentDTO);
    public void deleteStudent(long id);
    public Set<CourseDTO> courseDTOList(long id);
}
