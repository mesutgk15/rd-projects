package com.robotdreams.service;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;

import java.util.List;
import java.util.Set;

public interface CourseService {

    public List<CourseDTO> findAllCourses();
    public CourseDTO findCourseById(long id);
    public void createCourse(CourseDTO courseDTO);
    public void updateCourse(long id, CourseDTO courseDTODTO);
    public void setInstructor(long id, long instructorid);
    public void deleteCourse(long id);
    public Set<StudentDTO> getStudents(long id);
}
