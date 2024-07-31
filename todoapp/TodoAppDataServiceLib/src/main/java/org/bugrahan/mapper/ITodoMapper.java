package org.bugrahan.mapper;

import org.bugrahan.dto.TodoDTO;
import org.bugrahan.dto.TodoSaveDTO;
import org.bugrahan.dto.TodoUpdateDTO;
import org.bugrahan.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(implementationName = "TodoMapperImpl", componentModel = "spring")
public interface ITodoMapper {
    Todo toTodo(TodoSaveDTO saveDTO);

    TodoDTO toTodoDTO(Todo todo);

    Todo toTodo(TodoUpdateDTO updateDTO);

    TodoUpdateDTO toTodoUpdateDTO(Todo todo);
}
