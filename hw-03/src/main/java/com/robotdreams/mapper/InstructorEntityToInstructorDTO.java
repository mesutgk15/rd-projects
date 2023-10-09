package com.robotdreams.mapper;

import com.robotdreams.model.Instructor;
import com.robotdreams.model.VisitingResearcher;
import com.robotdreams.model.dto.InstructorDTO;
import com.robotdreams.model.dto.PermanentInstructorDTO;
import com.robotdreams.model.dto.VisitingResearcherDTO;
import org.springframework.stereotype.Component;

@Component
public class InstructorEntityToInstructorDTO implements BaseMapper<InstructorDTO, Instructor>{


    @Override
    public InstructorDTO map(Instructor instructor, Object... params) {

        InstructorDTO instructorDTO = null;
        if (instructor == null) {
            return instructorDTO;
        }
        switch (instructor.getClass().getSimpleName()) {
            case "VisitingResearcher":
                instructorDTO = new VisitingResearcherDTO();
                ((VisitingResearcherDTO) instructorDTO).setWorkingHours(((VisitingResearcher) instructor).getWorkingHours());
                break;
            default:
                instructorDTO = new PermanentInstructorDTO();
                break;
        }

        instructorDTO.setName(instructor.getName());
        instructorDTO.setAddress(instructor.getAddress());
        instructorDTO.setPhoneNumber(instructor.getPhoneNumber());

        return instructorDTO;

    }

}
