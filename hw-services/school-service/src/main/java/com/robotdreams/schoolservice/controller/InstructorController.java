package com.robotdreams.schoolservice.controller;

import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.InstructorDTO;
import com.robotdreams.schoolservice.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstructorDTO> findAllInstructors() {
        return instructorService.findAllInstructors();
    }

    @GetMapping("/{id}")
    public InstructorDTO findInstructorById(@PathVariable long id) {
        return instructorService.findInstructorById(id);
    }

    @PostMapping()
    public void createInstructor(@RequestBody InstructorDTO instructorDTO) {
        instructorService.createInstructor(instructorDTO);
    }

    @PutMapping()
    public void updateInstructor(@RequestBody InstructorDTO instructorDTO) {
        instructorService.updateInstructor(instructorDTO);
    }

    @DeleteMapping
    public void deleteInstructor(@RequestParam long id) {
        instructorService.deleteInstructor(id);
    }

    @GetMapping("/courses")
    public List<CourseDTO> getCourses(@RequestParam long id) {
       return instructorService.getCourses(id);
    }
}
