package com.robotdreams.schoolservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CourseDTO {

    private long id;
    private String name;
    private String code;
    private double creditScore;
    private InstructorDTO instructorDTO;
    private long instructorID;



}
