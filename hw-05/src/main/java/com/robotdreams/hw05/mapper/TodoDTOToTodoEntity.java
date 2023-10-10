package com.robotdreams.hw05.mapper;

import com.robotdreams.hw05.model.dto.TodoDTO;
import com.robotdreams.hw05.model.entity.Todo;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TodoDTOToTodoEntity implements BaseMapper <Todo, TodoDTO>{


    @Override
    public Todo map(TodoDTO todoDTO, Object... params) {

        Todo todo = Todo.builder()
                .userId(todoDTO.getUserId())
                .title(todoDTO.getTitle())
                .completed(todoDTO.isCompleted())
                .build();

        if (todoDTO.getId() != 0)
            todo.setId(todoDTO.getId());

        return  todo;

    }
}
