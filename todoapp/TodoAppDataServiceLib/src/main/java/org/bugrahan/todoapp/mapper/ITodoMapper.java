package org.bugrahan.todoapp.mapper;

import org.bugrahan.todoapp.dto.TodoDTO;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;

import org.bugrahan.todoapp.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(implementationName = "TodoMapperImpl", componentModel = "spring")
public interface ITodoMapper {
    Todo toTodo(TodoSaveDTO saveDTO);

    TodoSaveDTO todoSaveDTO(Todo todo);

    TodoDTO toTodoDTO(Todo todo);

    Todo toTodo(TodoUpdateDTO updateDTO);

    TodoUpdateDTO toTodoUpdateDTO(Todo todo);
}
