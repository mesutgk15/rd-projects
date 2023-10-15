package com.robotdreams.schoolservice.model.dto;

public class PermanentInstructorDTO extends InstructorDTO{

    public PermanentInstructorDTO(long id, String name, String address, String phoneNumber) {
        super(id, name, address, phoneNumber, InstructorType.PERMANENT);
    }

    public PermanentInstructorDTO(InstructorType instructorType) {
        super(instructorType);
    }
}
