package com.robotdreams.client;

import com.robotdreams.model.*;
import com.robotdreams.utils.*;
import jakarta.persistence.EntityManager;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchoolTestClient {

    public static void main(String[] args) {

        //Create student objects
        Student student1 = new Student("Ali", LocalDate.of(2000, Month.JANUARY, 10), "Istanbul, Levent", 'M');
        Student student2 = new Student("Ayşe", LocalDate.of(2000, Month.MAY, 22), "Istanbul, Besiktas", 'F');
        Student student3 = new Student("Zeynep", LocalDate.of(2000, Month.SEPTEMBER, 12), "Istanbul, Mecidiyekoy", 'F');

        //Add student objects into a HashSet
        Set<Student> studentsToSave = new HashSet<>();
        studentsToSave.add(student1);
        studentsToSave.add(student2);
        studentsToSave.add(student3);

        //Create instructor objects
        Instructor instructor1 = new PermanentInstructor("Mehmet", "Istanbul, Ortakoy", "123456789");
        Instructor instructor2 = new VisitingResearcher("Hasan", "Istanbul, Ortakoy", "987654321");

        //Add instructor objects into a HashSet
        Set<Instructor> instructorsToSave = new HashSet<>();
        instructorsToSave.add(instructor1);
        instructorsToSave.add(instructor2);

        //Create course objects
        Course course1 = new Course("Algebra-1", "ALG101", 3);
        Course course2 = new Course("Physics-1", "PHYS101", 3);

        //Add course objects into a HashSet
        Set<Course> coursesToSave = new HashSet<>();
        coursesToSave.add(course1);
        coursesToSave.add(course2);

        //Add instructor to courses
        course1.setInstructor(instructor1);
        course2.setInstructor(instructor2);

        //Add courses to students
        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);
        student3.addCourse(course2);

        //Add all HashSet of objects to a List
        List<Set> objectsToSave = new ArrayList<>();
        objectsToSave.add(instructorsToSave);
        objectsToSave.add(coursesToSave);
        objectsToSave.add(studentsToSave);

        //Save objects to DB
        saveToDb(objectsToSave);

    }

    //Iterate through all HashSets and save the members to DataBase
    public static void saveToDb(List<Set> objectSet) {

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");


        try {
            entityManager.getTransaction().begin();

            for (Set hashSetObject : objectSet) {
                for (Object o : hashSetObject) {
                    entityManager.persist(o);
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtils.close(entityManager);
        }

    }
}
