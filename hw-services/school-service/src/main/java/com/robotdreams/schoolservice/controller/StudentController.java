package com.robotdreams.schoolservice.controller;

import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.StudentDTO;
import com.robotdreams.schoolservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO findStudentById(@PathVariable long id) {
        return studentService.findByStudentById(id);
    }

    @PostMapping
    public void saveStudent(@RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(studentDTO);
    }

    @PutMapping
    public void updateStudent(@RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(studentDTO);
    }

    @PostMapping("courses")
    public void addCourse(@RequestParam long id, @RequestParam long courseid) {
        studentService.addCourse(id, courseid);
    }

    @PutMapping("courses")
    public void dropCourse(@RequestParam long id, @RequestParam long courseid) {
        studentService.dropCourse(id, courseid);
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/courses")
    public Set<CourseDTO> getCourseList(@RequestParam long id) {
        return studentService.courseDTOList(id);
    }

}
