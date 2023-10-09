package com.robotdreams.model.dto;

public class CourseDTO {

    private String name;
    private String code;
    private double creditScore;
    private InstructorDTO instructorDTO;


    public CourseDTO(String name, String code, double creditScore, InstructorDTO instructorDTO) {
        this.name = name;
        this.code = code;
        this.creditScore = creditScore;
        this.instructorDTO = instructorDTO;
    }

    public CourseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public InstructorDTO getInstructorDTO() {
        return instructorDTO;
    }

    public void setInstructorDTO(InstructorDTO instructorDTO) {
        this.instructorDTO = instructorDTO;
    }
}
