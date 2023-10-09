package com.robotdreams.service;

import com.robotdreams.exception.InstructorNotFoundException;
import com.robotdreams.mapper.CourseEntityToCourseDTO;
import com.robotdreams.mapper.InstructorDTOToInstructorEntity;
import com.robotdreams.mapper.InstructorEntityToInstructorDTO;
import com.robotdreams.model.Course;
import com.robotdreams.model.Instructor;
import com.robotdreams.model.VisitingResearcher;
import com.robotdreams.model.dto.CourseDTO;
import com.robotdreams.model.dto.InstructorDTO;
import com.robotdreams.model.dto.VisitingResearcherDTO;
import com.robotdreams.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final InstructorEntityToInstructorDTO instructorEntityToInstructorDTO;
    private final InstructorDTOToInstructorEntity instructorDTOToInstructorEntity;
    private final CourseServiceImpl courseServiceImpl;
    private final CourseEntityToCourseDTO courseEntityToCourseDTO;

    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorEntityToInstructorDTO instructorEntityToInstructorDTO, InstructorDTOToInstructorEntity instructorDTOToInstructorEntity, CourseServiceImpl courseServiceImpl, CourseEntityToCourseDTO courseEntityToCourseDTO) {
        this.instructorRepository = instructorRepository;
        this.instructorEntityToInstructorDTO = instructorEntityToInstructorDTO;
        this.instructorDTOToInstructorEntity = instructorDTOToInstructorEntity;
        this.courseServiceImpl = courseServiceImpl;
        this.courseEntityToCourseDTO = courseEntityToCourseDTO;
    }

    @Override
    public List<InstructorDTO> findAllInstructors() {
        List<Instructor> instructorList = instructorRepository.findAll();
        List<InstructorDTO> instructorDTOList = new ArrayList<>();
        instructorList.forEach(i -> instructorDTOList.add(instructorEntityToInstructorDTO.map(i)));
        return instructorDTOList;
    }

    @Override
    public InstructorDTO findInstructorById(long id) {
        return instructorEntityToInstructorDTO.map(instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException("Given ID does not exist in Instructor Database")));
    }

    @Override
    public void createInstructor(InstructorDTO instructorDTO) {
        instructorRepository.save(instructorDTOToInstructorEntity.map(instructorDTO));
    }

    @Override
    public void updateInstructor(long id, InstructorDTO instructorDTO) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException("Instructor Not Found"));
        instructor.setName(instructorDTO.getName());
        instructor.setAddress(instructorDTO.getAddress());
        instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        if (instructor instanceof VisitingResearcher) {
            ((VisitingResearcher) instructor).setWorkingHours(((VisitingResearcherDTO) instructorDTO).getWorkingHours());
        }
        instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(long id) {
        List<Course> courses = courseServiceImpl.getCourseRepository().findCourseByInstructor_Id(id);
        for (Course c : courses) {
            c.setInstructor(null);
        }
        instructorRepository.deleteById(id);
    }

    @Override
    public List<CourseDTO> getCourses(long id) {
        List<Course> courses = instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException("Instructor Not Found")).getCourses();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courses) {
            courseDTOList.add(courseEntityToCourseDTO.map(course));
        }
        return courseDTOList;
    }
}
