package com.robotdreams.mapper;

import com.robotdreams.model.Course;
import com.robotdreams.model.dto.CourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseDTOToCourseEntity implements BaseMapper<Course, CourseDTO>{

    private final InstructorDTOToInstructorEntity instructorDTOToInstructorEntity;

    public CourseDTOToCourseEntity(InstructorDTOToInstructorEntity instructorDTOToInstructorEntity) {
        this.instructorDTOToInstructorEntity = instructorDTOToInstructorEntity;
    }

    @Override
    public Course map(CourseDTO courseDTO, Object... params) {

        Course course = new Course();
        course.setCode(courseDTO.getCode());
        course.setName(courseDTO.getName());
        course.setCreditScore(courseDTO.getCreditScore());
        course.setInstructor(instructorDTOToInstructorEntity.map(courseDTO.getInstructorDTO()));
        return course;

    }

}
