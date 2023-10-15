package com.robotdreams.schoolservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitingResearcherDTO extends InstructorDTO{


    private double workingHours;


    public VisitingResearcherDTO(long id, String name, String address, String phoneNumber, double workingHours) {
        super(id, name, address, phoneNumber, InstructorType.VISITING);
        this.workingHours = workingHours;
    }

    public VisitingResearcherDTO(InstructorType instructorType) {
        super(instructorType);
    }
}
