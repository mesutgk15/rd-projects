package com.robotdreams.initializer;

import com.robotdreams.model.*;
import com.robotdreams.repository.CourseRepository;
import com.robotdreams.repository.InstructorRepository;
import com.robotdreams.repository.StudentRepository;
import com.robotdreams.service.InstructorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TestDataInitializer implements ApplicationRunner {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TestDataInitializer(CourseRepository courseRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        if (studentRepository.findAll().size() == 0) {


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
        ((VisitingResearcher) instructor2).setWorkingHours(10);

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


    }

    //Iterate through all HashSets and save the members to DataBase
    public void saveToDb(List<Set> objectSet) {

        for (Set hashSetObject : objectSet) {
            for (Object o : hashSetObject) {
                String type = o.getClass().getSimpleName();
                switch (type) {
                    case "Instructor" :
                        instructorRepository.save((Instructor) o);
                        break;
                    case "Student" :
                        studentRepository.save((Student) o);
                        break;
                    case "Course" :
                        courseRepository.save((Course) o);
                }
            }
        }
    }
}
