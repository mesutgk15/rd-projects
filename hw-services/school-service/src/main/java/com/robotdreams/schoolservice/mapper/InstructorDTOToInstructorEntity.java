package com.robotdreams.schoolservice.mapper;

import com.robotdreams.schoolservice.exception.InstructorNotFoundException;
import com.robotdreams.schoolservice.model.Instructor;
import com.robotdreams.schoolservice.model.PermanentInstructor;
import com.robotdreams.schoolservice.model.VisitingResearcher;
import com.robotdreams.schoolservice.model.dto.InstructorDTO;
import com.robotdreams.schoolservice.model.dto.VisitingResearcherDTO;
import com.robotdreams.schoolservice.repository.InstructorRepository;
import org.springframework.stereotype.Component;

@Component
public class InstructorDTOToInstructorEntity implements BaseMapper<Instructor, InstructorDTO> {

    private final InstructorRepository instructorRepository;

    public InstructorDTOToInstructorEntity(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor map(InstructorDTO instructorDTO, Object... params) {

        Instructor instructor;
        InstructorDTO.InstructorType instructorType = null;
        if (instructorDTO.getId() != 0) {
            instructor = instructorRepository.findById(instructorDTO.getId()).orElseThrow(() -> new InstructorNotFoundException("Incorrect Instructor ID"));
            instructorType = instructorDTO.getInstructorType();
        } else {
            switch (instructorDTO.getInstructorType().toString()) {
                case  "VISITING":
                    instructor = new VisitingResearcher();
                    break;
                default:
                    instructor = new PermanentInstructor();
                    break;
            }
        }

        if (instructorDTO.getName() != null)
            instructor.setName(instructorDTO.getName());
        if (instructorDTO.getAddress() != null)
            instructor.setAddress(instructorDTO.getAddress());
        if (instructorDTO.getPhoneNumber() != null)
            instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        if (instructorType == InstructorDTO.InstructorType.VISITING)
            if (((VisitingResearcherDTO) instructorDTO).getWorkingHours() != 0)
                ((VisitingResearcher) instructor).setWorkingHours(((VisitingResearcherDTO) instructorDTO).getWorkingHours());

        return instructor;
    }

}
