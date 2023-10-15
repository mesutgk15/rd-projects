package com.robotdreams.schoolservice.repository;

import com.robotdreams.schoolservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCourseByInstructor_Id(long instructorId);
}
