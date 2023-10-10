package com.robotdreams.hw05.repository;


import com.robotdreams.hw05.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    public List<Todo> findAllByCompleted(boolean isCompleted);
}
