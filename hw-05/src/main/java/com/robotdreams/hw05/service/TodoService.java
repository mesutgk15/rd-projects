package com.robotdreams.hw05.service;


import com.robotdreams.hw05.model.dto.TodoDTO;
import com.robotdreams.hw05.model.entity.Todo;

import java.util.List;

public interface TodoService {

    public List<TodoDTO> getAllTodo();
    public List<TodoDTO> getAll(boolean isCompleted);
    public List<TodoDTO> saveTodo();
    public TodoDTO saveTodo(TodoDTO todoDTO);
    public TodoDTO[] saveTodo(TodoDTO[] todoDTOS);
    public List<TodoDTO> saveTodo(List<TodoDTO> todoDTOS);
}
