package com.robotdreams.schoolservice.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "instructorType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PermanentInstructorDTO.class, name = "PERMANENT"),
        @JsonSubTypes.Type(value = VisitingResearcherDTO.class, name = "VISITING")
})
@Getter
@AllArgsConstructor
@Setter
@RequiredArgsConstructor
public abstract class InstructorDTO {

    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private final InstructorType instructorType;

    public enum InstructorType {VISITING, PERMANENT}



}
