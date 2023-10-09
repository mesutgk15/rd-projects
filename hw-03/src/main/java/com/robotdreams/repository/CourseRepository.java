package com.robotdreams.repository;

import com.robotdreams.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findCourseByInstructor_Id(long instructorId);
}
