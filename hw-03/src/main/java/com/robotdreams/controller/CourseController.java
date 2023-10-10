package com.robotdreams.controller;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.StudentDTO;
import com.robotdreams.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public List<CourseDTO> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping
    public CourseDTO findCourseById(@RequestParam long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping ("/new")
    public void createCourse(@RequestBody CourseDTO courseDTO) {
        courseService.createCourse(courseDTO);
    }

    @PutMapping("/update")
    public void updateCourse(@RequestParam long id, @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
    }

    @PutMapping("/instructor")
    public void setInstructor(@RequestParam long id, @RequestParam long instructorid) {
        courseService.setInstructor(id, instructorid);
    }

    @DeleteMapping
    public void deleteCourse(@RequestParam long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/students")
    public Set<StudentDTO> getStudents(@RequestParam long id) {
        return courseService.getStudents(id);
    }
}
