package com.robotdreams.schoolservice.mapper;

import com.robotdreams.schoolservice.exception.CourseNotFoundException;
import com.robotdreams.schoolservice.exception.InstructorNotFoundException;
import com.robotdreams.schoolservice.model.Course;
import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.repository.CourseRepository;
import com.robotdreams.schoolservice.repository.InstructorRepository;
import org.springframework.stereotype.Component;

@Component
public class CourseDTOToCourseEntity implements BaseMapper<Course, CourseDTO> {

    private final InstructorDTOToInstructorEntity instructorDTOToInstructorEntity;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    public CourseDTOToCourseEntity(InstructorDTOToInstructorEntity instructorDTOToInstructorEntity, InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorDTOToInstructorEntity = instructorDTOToInstructorEntity;
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Course map(CourseDTO courseDTO, Object... params) {


        Course course = new Course();

        if (courseDTO.getId() != 0)
            course = courseRepository.findById(courseDTO.getId()).orElseThrow(() -> new CourseNotFoundException("Incorrect Course ID"));

        if (courseDTO.getCode() != null)
            course.setCode(courseDTO.getCode());
        if (courseDTO.getName() != null)
            course.setName(courseDTO.getName());
        if (courseDTO.getCreditScore() != 0)
            course.setCreditScore(courseDTO.getCreditScore());
        if (courseDTO.getInstructorID() != 0)
            course.setInstructor(instructorRepository.findById(courseDTO.getInstructorID()).orElseThrow(() -> new InstructorNotFoundException("Instructor Not Set")));


        return course;

    }

}
