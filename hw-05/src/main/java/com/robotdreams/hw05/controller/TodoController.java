package com.robotdreams.hw05.controller;

import com.robotdreams.hw05.model.dto.TodoDTO;
import com.robotdreams.hw05.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class TodoController {


    @Qualifier("TodoServiceImpl")
    private final TodoService todoService;


    @GetMapping("/todos")
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodo();
    }

    @GetMapping("/todos/completed")
    public List<TodoDTO> getAll(@RequestParam boolean iscompleted) {
        return todoService.getAll(iscompleted);
    }

    @PostMapping("/todos/new")
    public TodoDTO saveTodo(@RequestBody TodoDTO todoDTO) {
        return todoService.saveTodo(todoDTO);
    }

    @PostMapping("/todos")
    public List<TodoDTO> saveTodo() {
        return todoService.saveTodo();
    }

}
