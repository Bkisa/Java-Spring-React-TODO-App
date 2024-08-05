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
    @Mapping(target = "endDate", source = "endDate")
    Todo toTodo(TodoSaveDTO saveDTO);

    @Mapping(target = "isCompleted", source = "completed")
    @Mapping(target = "endDate", source = "endDate")
    TodoSaveDTO todoSaveDTO(Todo todo);

    @Mapping(target = "isCompleted", source = "completed")
    @Mapping(target = "endDate", source = "endDate")
    Todo toTodo(TodoDTO todoDTO);

    @Mapping(target = "isCompleted", source = "completed")
    @Mapping(target = "endDate", source = "endDate")
    TodoDTO toTodoDTO(Todo todo);

    @Mapping(target = "isCompleted", source = "completed")
    @Mapping(target = "endDate", source = "endDate")
    Todo toTodo(TodoUpdateDTO updateDTO);

    @Mapping(target = "isCompleted", source = "completed")
    @Mapping(target = "endDate", source = "endDate")
    TodoUpdateDTO toTodoUpdateDTO(Todo todo);
}
