package com.robotdreams.controller;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;
import com.robotdreams.service.StudentService;
import com.robotdreams.service.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<StudentDTO> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping
    public StudentDTO findStudentById(@RequestParam long id) {
        return studentService.findByStudentById(id);
    }

    @PostMapping("/new")
    public void saveStudent(@RequestBody StudentDTO studentDTO) {
        studentService.createNewStudent(studentDTO);
    }

    @PutMapping("/update")
    public void updateStudent(@RequestParam long id, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(id, studentDTO);
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
