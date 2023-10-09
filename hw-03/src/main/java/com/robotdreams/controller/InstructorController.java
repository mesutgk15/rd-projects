package com.robotdreams.controller;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.InstructorDTO;
import com.robotdreams.model.dto.PermanentInstructorDTO;
import com.robotdreams.model.dto.VisitingResearcherDTO;
import com.robotdreams.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/all")
    public List<InstructorDTO> findAllInstructors() {
        return instructorService.findAllInstructors();
    }

    @GetMapping
    public InstructorDTO findInstructorById(@RequestParam long id) {
        return instructorService.findInstructorById(id);
    }

    @PostMapping("/new")
    public void createInstructor(@RequestBody PermanentInstructorDTO instructorDTO) {
        instructorService.createInstructor(instructorDTO);
    }

    @PostMapping("/new/visiting")
    public void createInstructor(@RequestBody VisitingResearcherDTO instructorDTO) {
        instructorService.createInstructor(instructorDTO);
    }

    @PutMapping("/update/visiting")
    public void updateInstructor(@RequestParam long id, @RequestBody VisitingResearcherDTO instructorDTO) {
        instructorService.updateInstructor(id, instructorDTO);
    }

    @PutMapping("/update")
    public void updateInstructor(@RequestParam long id, @RequestBody PermanentInstructorDTO instructorDTO) {
        instructorService.updateInstructor(id, instructorDTO);
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
