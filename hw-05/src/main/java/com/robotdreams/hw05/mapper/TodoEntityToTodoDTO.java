package com.robotdreams.hw05.mapper;

import com.robotdreams.hw05.model.dto.TodoDTO;
import com.robotdreams.hw05.model.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoEntityToTodoDTO implements BaseMapper <TodoDTO, Todo>{


    @Override
    public TodoDTO map(Todo todo, Object... params) {

        return TodoDTO.builder()
                .id(todo.getId())
                .userId(todo.getUserId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .build();
    }
}
