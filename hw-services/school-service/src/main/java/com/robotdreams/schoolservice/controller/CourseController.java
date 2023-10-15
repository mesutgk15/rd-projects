package com.robotdreams.schoolservice.controller;

import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.StudentDTO;
import com.robotdreams.schoolservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO findCourseById(@PathVariable long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping
    public void saveCourse(@RequestBody CourseDTO courseDTO) {
        courseService.saveCourse(courseDTO);
    }

    @PutMapping
    public void updateCourse(@RequestBody CourseDTO courseDTO) {
        courseService.saveCourse(courseDTO);
    }

    @PutMapping("/instructors")
    public void setInstructor(@RequestParam long id, @RequestParam long instructorid) {
        courseService.setInstructor(id, instructorid);
    }

    @DeleteMapping("/instructors")
    public CourseDTO removeInstructor(@RequestParam long id) {
        return courseService.removeInstructor(id);
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
