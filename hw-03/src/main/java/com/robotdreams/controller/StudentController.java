package com.robotdreams.controller;

import com.robotdreams.model.Student;
import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;
import com.robotdreams.service.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping("/all")
    public List<StudentDTO> findAllStudents() {
        return studentServiceImpl.findAllStudents();
    }

    @GetMapping
    public StudentDTO findStudentById(@RequestParam long id) {
        return studentServiceImpl.findByStudentById(id);
    }

    @PostMapping("/new")
    public void saveStudent(@RequestBody StudentDTO studentDTO) {
        studentServiceImpl.createNewStudent(studentDTO);
    }

    @PutMapping("/update")
    public void updateStudent(@RequestParam long id, @RequestBody StudentDTO studentDTO) {
        studentServiceImpl.updateStudent(id, studentDTO);
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam long id) {
        studentServiceImpl.deleteStudent(id);
    }

    @GetMapping("/courses")
    public Set<CourseDTO> getCourseList(@RequestParam long id) {
        return studentServiceImpl.courseDTOList(id);
    }

}
