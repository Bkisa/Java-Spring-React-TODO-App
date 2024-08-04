package org.bugrahan.todoapp.mapper;

import org.bugrahan.todoapp.dto.TodoDTO;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;

import org.bugrahan.todoapp.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(implementationName = "TodoMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITodoMapper {
    @Mapping(target = "isCompleted", source = "completed")
    Todo toTodo(TodoSaveDTO saveDTO);

    @Mapping(target = "isCompleted", source = "completed")
    TodoSaveDTO todoSaveDTO(Todo todo);

    @Mapping(target = "isCompleted", source = "completed")
    Todo toTodo(TodoDTO todoDTO);

    @Mapping(target = "isCompleted", source = "completed")
    TodoDTO toTodoDTO(Todo todo);

    @Mapping(target = "isCompleted", source = "completed")
    Todo toTodo(TodoUpdateDTO updateDTO);

    @Mapping(target = "isCompleted", source = "completed")
    TodoUpdateDTO toTodoUpdateDTO(Todo todo);
}
