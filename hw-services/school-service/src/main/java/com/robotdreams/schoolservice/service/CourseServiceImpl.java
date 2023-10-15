package com.robotdreams.schoolservice.service;

import com.robotdreams.schoolservice.exception.CourseNotFoundException;
import com.robotdreams.schoolservice.exception.InstructorNotFoundException;
import com.robotdreams.schoolservice.mapper.CourseDTOToCourseEntity;
import com.robotdreams.schoolservice.mapper.CourseEntityToCourseDTO;
import com.robotdreams.schoolservice.mapper.StudentEntityToStudentDTO;
import com.robotdreams.schoolservice.model.Course;
import com.robotdreams.schoolservice.model.Student;
import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.StudentDTO;
import com.robotdreams.schoolservice.repository.CourseRepository;
import com.robotdreams.schoolservice.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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
    public void saveCourse(CourseDTO courseDTO) {
        courseRepository.save(courseDTOToCourseEntity.map(courseDTO));
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

    @Override
    public CourseDTO removeInstructor(long id) {
        Course course = courseDTOToCourseEntity.map(findCourseById(id));
        course.setInstructor(null);
        return courseEntityToCourseDTO.map(courseRepository.save(course));
    }
}
