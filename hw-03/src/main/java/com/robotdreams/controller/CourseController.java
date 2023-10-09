package com.robotdreams.controller;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;
import com.robotdreams.service.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    public CourseController(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    @GetMapping("/all")
    public List<CourseDTO> findAllCourses() {
        return courseServiceImpl.findAllCourses();
    }

    @GetMapping
    public CourseDTO findCourseById(@RequestParam long id) {
        return courseServiceImpl.findCourseById(id);
    }

    @GetMapping("/new")
    public void createCourse(@RequestBody CourseDTO courseDTO) {
        courseServiceImpl.createCourse(courseDTO);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestParam long id, @RequestBody CourseDTO courseDTO) {
        courseServiceImpl.updateCourse(id, courseDTO);
    }

    @PutMapping("/instructor")
    public void setInstructor(@RequestParam long id, @RequestParam long instructorid) {
        courseServiceImpl.setInstructor(id, instructorid);
    }

    @DeleteMapping
    public void deleteCourse(@RequestParam long id) {
        courseServiceImpl.deleteCourse(id);
    }

    @GetMapping("/students")
    public Set<StudentDTO> getStudents(@RequestParam long id) {
        return courseServiceImpl.getStudents(id);
    }
}
