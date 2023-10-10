package com.robotdreams.hw05.service;

import com.robotdreams.hw05.mapper.TodoDTOToTodoEntity;
import com.robotdreams.hw05.mapper.TodoEntityToTodoDTO;
import com.robotdreams.hw05.model.dto.TodoDTO;
import com.robotdreams.hw05.model.entity.Todo;
import com.robotdreams.hw05.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service("TodoServiceImpl")
public class TodoServiceImpl implements TodoService{

    private final RestTemplate restTemplate;
    private final TodoDTOToTodoEntity todoDTOToTodoEntity;
    private final TodoEntityToTodoDTO todoEntityToTodoDTO;
    private final TodoRepository todoRepository;

    //Retrieves todos from uri
    public List<TodoDTO> getAllTodo() {
        TodoDTO[] todoDTOS = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos", TodoDTO[].class);
        List<TodoDTO> todoList = Arrays.asList(todoDTOS);
        return  todoList;

    }

    //Retrieves todos from database with respect to given completed status
    @Override
    public List<TodoDTO> getAll(boolean isCompleted) {
        List<Todo> todoList = todoRepository.findAllByCompleted(isCompleted);
        List<TodoDTO> todoDTOList = new ArrayList<>();
        todoList.forEach(t -> todoDTOList.add(todoEntityToTodoDTO.map(t)));
        return todoDTOList;

    }

    // Versions of save operations
    @Override
    public List<TodoDTO> saveTodo() {
        return saveTodo(getAllTodo());
    }

    @Override
    public TodoDTO saveTodo(TodoDTO todoDTO) {
        Todo todo = todoDTOToTodoEntity.map(todoDTO);
        todo.setTimeRetrieved(LocalDateTime.now());
        return todoEntityToTodoDTO.map(todoRepository.save(todo));
    }

    @Override
    public TodoDTO[] saveTodo(TodoDTO[] todoDTOS) {
        List<TodoDTO> todoDTOList = Arrays.asList(todoDTOS);
        todoDTOList.forEach(t -> {
            saveTodo(t);
        });
        return todoDTOS;
    }

    @Override
    public List<TodoDTO> saveTodo(List<TodoDTO> todoDTOS) {
        todoDTOS.forEach(t -> {
            saveTodo(t);
        });
        return todoDTOS;
    }
}
