package com.robotdreams.schoolservice.model;

import jakarta.persistence.Entity;

@Entity
public class PermanentInstructor extends Instructor{

    private static final double salary = 1000;

    public PermanentInstructor() {
    }

    public PermanentInstructor(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
