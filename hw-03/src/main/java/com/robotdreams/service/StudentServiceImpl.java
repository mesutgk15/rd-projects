package com.robotdreams.service;

import com.robotdreams.exception.StudentNotFoundException;
import com.robotdreams.mapper.CourseEntityToCourseDTO;
import com.robotdreams.mapper.StudentDTOToStudentEntity;
import com.robotdreams.mapper.StudentEntityToStudentDTO;
import com.robotdreams.model.Course;
import com.robotdreams.model.Student;
import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;
import com.robotdreams.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentEntityToStudentDTO studentEntityToStudentDTO;
    private final StudentDTOToStudentEntity studentDTOToStudentEntity;
    private final CourseEntityToCourseDTO courseEntityToCourseDTO;

    public StudentServiceImpl(StudentRepository studentRepository, StudentEntityToStudentDTO studentEntityToStudentDTO, StudentDTOToStudentEntity studentDTOToStudentEntity, CourseEntityToCourseDTO courseEntityToCourseDTO) {
        this.studentRepository = studentRepository;
        this.studentEntityToStudentDTO = studentEntityToStudentDTO;
        this.studentDTOToStudentEntity = studentDTOToStudentEntity;
        this.courseEntityToCourseDTO = courseEntityToCourseDTO;
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
    public void createNewStudent(StudentDTO studentDTO) {
        studentRepository.save(studentDTOToStudentEntity.map(studentDTO));
    }

    @Override
    public void updateStudent(long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found"));
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setGender(studentDTO.getGender());
        student.setBirthDate(studentDTO.getBirthDate());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Set<CourseDTO> courseDTOList(long id) {
        Set<Course> courses = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found")).getCourses();
        Set<CourseDTO> courseDTOSet = new HashSet<>();
        for (Course course : courses) {
            courseDTOSet.add(courseEntityToCourseDTO.map(course));
        }
        return courseDTOSet;
    }
}
