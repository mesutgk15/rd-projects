package com.robotdreams.schoolservice.service;

import com.robotdreams.schoolservice.exception.CourseNotFoundException;
import com.robotdreams.schoolservice.exception.StudentNotFoundException;
import com.robotdreams.schoolservice.mapper.CourseEntityToCourseDTO;
import com.robotdreams.schoolservice.mapper.StudentDTOToStudentEntity;
import com.robotdreams.schoolservice.mapper.StudentEntityToStudentDTO;
import com.robotdreams.schoolservice.model.Course;
import com.robotdreams.schoolservice.model.Student;
import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.StudentDTO;
import com.robotdreams.schoolservice.repository.CourseRepository;
import com.robotdreams.schoolservice.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentEntityToStudentDTO studentEntityToStudentDTO;
    private final StudentDTOToStudentEntity studentDTOToStudentEntity;
    private final CourseEntityToCourseDTO courseEntityToCourseDTO;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentEntityToStudentDTO studentEntityToStudentDTO, StudentDTOToStudentEntity studentDTOToStudentEntity, CourseEntityToCourseDTO courseEntityToCourseDTO, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.studentEntityToStudentDTO = studentEntityToStudentDTO;
        this.studentDTOToStudentEntity = studentDTOToStudentEntity;
        this.courseEntityToCourseDTO = courseEntityToCourseDTO;
        this.courseRepository = courseRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public StudentDTOToStudentEntity getStudentDTOToStudentEntity() {
        return studentDTOToStudentEntity;
    }

    @Override
    public List<StudentDTO> findAllStudents() {

        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentList.forEach(s -> studentDTOList.add(studentEntityToStudentDTO.map(s)));
        return studentDTOList;
    }

    @Override
    public StudentDTO findByStudentById(long id) {
        return studentEntityToStudentDTO.map(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Given ID does not exist in Student Database")));
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {
        studentRepository.save(studentDTOToStudentEntity.map(studentDTO));
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Set<CourseDTO> courseDTOList(long id) {
        Set<Course> courses = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found")).getCourses();
        Set<CourseDTO> courseResponseDTOSet = new HashSet<>();
        for (Course course : courses) {
            courseResponseDTOSet.add(courseEntityToCourseDTO.map(course));
        }
        return courseResponseDTOSet;
    }

    @Override
    public void addCourse(long id, long courseid) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Incorrect Student Id"));
        Course course = courseRepository.findById(courseid).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
        student.addCourse(course);
        studentRepository.save(student);
    }

    @Override
    public void dropCourse(long id, long courseid) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Incorrect Student Id"));
        Course course = courseRepository.findById(courseid).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
        student.dropCourse(course);
        studentRepository.save(student);
    }
}
