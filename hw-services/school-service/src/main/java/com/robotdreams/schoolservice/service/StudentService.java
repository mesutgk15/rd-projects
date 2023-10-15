package com.robotdreams.schoolservice.service;

import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.StudentDTO;

import java.util.List;
import java.util.Set;

public interface StudentService {

    public List<StudentDTO> findAllStudents();

    public StudentDTO findByStudentById(long id);

    public void saveStudent(StudentDTO studentDTO);
    public void deleteStudent(long id);
    public Set<CourseDTO> courseDTOList(long id);

    public void addCourse(long id, long courseid);
    public void dropCourse(long id, long courseid);
}
