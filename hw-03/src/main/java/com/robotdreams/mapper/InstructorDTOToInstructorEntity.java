package com.robotdreams.mapper;

import com.robotdreams.model.Instructor;
import com.robotdreams.model.PermanentInstructor;
import com.robotdreams.model.VisitingResearcher;
import com.robotdreams.model.dto.InstructorDTO;
import com.robotdreams.model.dto.VisitingResearcherDTO;
import org.springframework.stereotype.Component;

@Component
public class InstructorDTOToInstructorEntity implements BaseMapper<Instructor, InstructorDTO>{


    @Override
    public Instructor map(InstructorDTO instructorDTO, Object... params) {

        Instructor instructor = null;
        if (instructorDTO == null) {
            return instructor;
        }
        switch (instructorDTO.getClass().getSimpleName()) {
            case "VisitingResearcherDTO":
                instructor = new VisitingResearcher();
                ((VisitingResearcher) instructor).setWorkingHours(((VisitingResearcherDTO) instructorDTO).getWorkingHours());
                break;
            default:
                instructor = new PermanentInstructor();
                break;
        }

        instructor.setName(instructorDTO.getName());
        instructor.setAddress(instructorDTO.getAddress());
        instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        return instructor;
    }

}
