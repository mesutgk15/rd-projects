package com.robotdreams.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate birthDate;
    private String address;
    private char gender;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String name, LocalDate birthDate, String address, char gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return courses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return gender == student.gender && Objects.equals(name, student.name) && Objects.equals(birthDate, student.birthDate) && Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, address, gender);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }

    public void addCourse(Course course) {
        getCourses().add(course);
    }

    public void dropCourse(Course course) {
        if (getCourses().contains(course)) {
            getCourses().remove(course);
        } else {
            System.out.println("Course not found");
        }
    }
}
