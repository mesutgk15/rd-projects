package com.robotdreams.schoolservice.service;

import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {

    List<InstructorDTO> findAllInstructors();

    InstructorDTO findInstructorById(long id);
    void createInstructor(InstructorDTO instructorDTO);
    void updateInstructor(InstructorDTO instructorDTO);
    void deleteInstructor(long id);

    List<CourseDTO> getCourses(long id);
}
