package com.robotdreams.schoolservice.service;

import com.robotdreams.schoolservice.exception.InstructorNotFoundException;
import com.robotdreams.schoolservice.mapper.CourseEntityToCourseDTO;
import com.robotdreams.schoolservice.mapper.InstructorDTOToInstructorEntity;
import com.robotdreams.schoolservice.mapper.InstructorEntityToInstructorDTO;
import com.robotdreams.schoolservice.model.Course;
import com.robotdreams.schoolservice.model.Instructor;
import com.robotdreams.schoolservice.model.dto.CourseDTO;
import com.robotdreams.schoolservice.model.dto.InstructorDTO;
import com.robotdreams.schoolservice.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

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
    public void updateInstructor(InstructorDTO instructorDTO) {
         instructorRepository.save(instructorDTOToInstructorEntity.map(instructorDTO));
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
