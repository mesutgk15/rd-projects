package com.robotdreams.schoolservice.mapper;

import com.robotdreams.schoolservice.model.Instructor;
import com.robotdreams.schoolservice.model.VisitingResearcher;
import com.robotdreams.schoolservice.model.dto.InstructorDTO;
import com.robotdreams.schoolservice.model.dto.PermanentInstructorDTO;
import com.robotdreams.schoolservice.model.dto.VisitingResearcherDTO;
import org.springframework.stereotype.Component;

@Component
public class InstructorEntityToInstructorDTO implements BaseMapper<InstructorDTO, Instructor> {


    @Override
    public InstructorDTO map(Instructor instructor, Object... params) {

        InstructorDTO instructorDTO = null;
        if (instructor == null) {
            return instructorDTO;
        }
        switch (instructor.getClass().getSimpleName()) {
            case "VisitingResearcher":
                instructorDTO = new VisitingResearcherDTO(InstructorDTO.InstructorType.VISITING);
                ((VisitingResearcherDTO) instructorDTO).setWorkingHours(((VisitingResearcher) instructor).getWorkingHours());
                break;
            default:
                instructorDTO = new PermanentInstructorDTO(InstructorDTO.InstructorType.PERMANENT);
                break;
        }

        instructorDTO.setName(instructor.getName());
        instructorDTO.setAddress(instructor.getAddress());
        instructorDTO.setPhoneNumber(instructor.getPhoneNumber());
        instructorDTO.setId(instructor.getId());

        return instructorDTO;

    }

}
