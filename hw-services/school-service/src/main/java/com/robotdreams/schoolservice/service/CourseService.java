package com.robotdreams.schoolservice.service;

import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.StudentDTO;

import java.util.List;
import java.util.Set;

public interface CourseService {

    List<CourseDTO> findAllCourses();
    CourseDTO findCourseById(long id);
    void saveCourse(CourseDTO courseDTO);
    void setInstructor(long id, long instructorid);
    void deleteCourse(long id);
    Set<StudentDTO> getStudents(long id);

    public CourseDTO removeInstructor(long id);
}
