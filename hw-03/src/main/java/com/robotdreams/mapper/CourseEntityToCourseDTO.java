package com.robotdreams.mapper;

import com.robotdreams.model.Course;
import com.robotdreams.model.dto.CourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseEntityToCourseDTO implements BaseMapper<CourseDTO, Course>{

    private final InstructorEntityToInstructorDTO instructorEntityToInstructorDTO;

    public CourseEntityToCourseDTO(InstructorEntityToInstructorDTO instructorEntityToInstructorDTO) {
        this.instructorEntityToInstructorDTO = instructorEntityToInstructorDTO;
    }

    @Override
    public CourseDTO map(Course course, Object... params) {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCode(course.getCode());
        courseDTO.setName(course.getName());
        courseDTO.setCreditScore(course.getCreditScore());
        courseDTO.setInstructorDTO(instructorEntityToInstructorDTO.map(course.getInstructor()));
        return courseDTO;

    }

}
