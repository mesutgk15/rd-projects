package com.robotdreams.service;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {

    public List<InstructorDTO> findAllInstructors();

    public InstructorDTO findInstructorById(long id);
    public void createInstructor(InstructorDTO instructorDTO);
    public void updateInstructor(long id, InstructorDTO instructorDTO);
    public void deleteInstructor(long id);

    public List<CourseDTO> getCourses(long id);
}
