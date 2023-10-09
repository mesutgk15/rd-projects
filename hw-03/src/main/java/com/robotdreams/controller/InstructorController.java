package com.robotdreams.controller;

import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.InstructorDTO;
import com.robotdreams.model.dto.PermanentInstructorDTO;
import com.robotdreams.model.dto.VisitingResearcherDTO;
import com.robotdreams.service.InstructorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instructor")
public class InstructorController {

    private final InstructorServiceImpl instructorServiceImpl;

    public InstructorController(InstructorServiceImpl instructorServiceImpl) {
        this.instructorServiceImpl = instructorServiceImpl;
    }

    @GetMapping("/all")
    public List<InstructorDTO> findAllInstructors() {
        return instructorServiceImpl.findAllInstructors();
    }

    @GetMapping
    public InstructorDTO findInstructorById(@RequestParam long id) {
        return instructorServiceImpl.findInstructorById(id);
    }

    @PostMapping("/new")
    public void createInstructor(@RequestBody PermanentInstructorDTO instructorDTO) {
        instructorServiceImpl.createInstructor(instructorDTO);
    }

    @PostMapping("/new/visiting")
    public void createInstructor(@RequestBody VisitingResearcherDTO instructorDTO) {
        instructorServiceImpl.createInstructor(instructorDTO);
    }

    @PutMapping("/update/visiting")
    public void updateInstructor(@RequestParam long id, @RequestBody VisitingResearcherDTO instructorDTO) {
        instructorServiceImpl.updateInstructor(id, instructorDTO);
    }

    @PutMapping("/update")
    public void updateInstructor(@RequestParam long id, @RequestBody PermanentInstructorDTO instructorDTO) {
        instructorServiceImpl.updateInstructor(id, instructorDTO);
    }

    @DeleteMapping
    public void deleteInstructor(@RequestParam long id) {
        instructorServiceImpl.deleteInstructor(id);
    }

    @GetMapping("/courses")
    public List<CourseDTO> getCourses(@RequestParam long id) {
       return instructorServiceImpl.getCourses(id);
    }
}
