package com.robotdreams.service;

import com.robotdreams.exception.CourseNotFoundException;
import com.robotdreams.exception.InstructorNotFoundException;
import com.robotdreams.mapper.CourseDTOToCourseEntity;
import com.robotdreams.mapper.CourseEntityToCourseDTO;
import com.robotdreams.mapper.StudentEntityToStudentDTO;
import com.robotdreams.model.Course;
import com.robotdreams.model.Student;
import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;
import com.robotdreams.repository.CourseRepository;
import com.robotdreams.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseEntityToCourseDTO courseEntityToCourseDTO;
    private final CourseDTOToCourseEntity courseDTOToCourseEntity;
    private final InstructorRepository instructorRepository;
    private final StudentServiceImpl studentServiceImpl;
    private final StudentEntityToStudentDTO studentEntityToStudentDTO;

    public CourseServiceImpl(CourseRepository courseRepository, CourseEntityToCourseDTO courseEntityToCourseDTO, CourseDTOToCourseEntity courseDTOToCourseEntity, InstructorRepository instructorRepository, StudentServiceImpl studentServiceImpl, StudentEntityToStudentDTO studentEntityToStudentDTO) {
        this.courseRepository = courseRepository;
        this.courseEntityToCourseDTO = courseEntityToCourseDTO;
        this.courseDTOToCourseEntity = courseDTOToCourseEntity;
        this.instructorRepository = instructorRepository;
        this.studentServiceImpl = studentServiceImpl;
        this.studentEntityToStudentDTO = studentEntityToStudentDTO;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    @Override
    public List<CourseDTO> findAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseList.forEach(c -> courseDTOList.add(courseEntityToCourseDTO.map(c)));
        return courseDTOList;
    }

    @Override
    public CourseDTO findCourseById(long id) {
        return courseEntityToCourseDTO.map(courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Given ID does not exist in Course Database")));
    }

    @Override
    public void createCourse(CourseDTO courseDTO) {
        courseRepository.save(courseDTOToCourseEntity.map(courseDTO));
    }

    @Override
    public void updateCourse(long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
        if (courseDTO.getName() != null)
            course.setName(courseDTO.getName());
        if (courseDTO.getCode() != null)
            course.setCode(courseDTO.getCode());
        course.setCreditScore(courseDTO.getCreditScore());
        courseRepository.save(course);
    }

    @Override
    public void setInstructor(long id, long instructorid) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
        course.setInstructor(instructorRepository.findById(instructorid).orElseThrow(() -> new InstructorNotFoundException("Instructor Not Found")));
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(long id) {
        if (courseRepository.existsById(id)) {
            Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
            List<Student> studentList = studentServiceImpl.getStudentRepository().findAll();
            for (Student s : studentList) {
                s.getCourses().remove(course);
            }
        }
        courseRepository.deleteById(id);
    }

    @Override
    public Set<StudentDTO> getStudents(long id) {
        Set<Student> students = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course Not Found")).getStudents();
        Set<StudentDTO> studentDTOSet = new HashSet<>();
        for (Student student : students) {
            studentDTOSet.add(studentEntityToStudentDTO.map(student));
        }
        return studentDTOSet;

    }
}
