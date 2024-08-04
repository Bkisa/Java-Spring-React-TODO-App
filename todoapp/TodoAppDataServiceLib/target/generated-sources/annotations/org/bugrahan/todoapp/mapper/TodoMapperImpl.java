package org.bugrahan.todoapp.mapper;

import javax.annotation.processing.Generated;
import org.bugrahan.todoapp.dto.TodoDTO;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;
import org.bugrahan.todoapp.entity.Todo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-04T14:57:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TodoMapperImpl implements ITodoMapper {

    @Override
    public Todo toTodo(TodoSaveDTO saveDTO) {
        if ( saveDTO == null ) {
            return null;
        }

        long id = 0L;
        String name = null;

        id = saveDTO.getId();
        name = saveDTO.getName();

        Todo todo = new Todo( id, name );

        return todo;
    }

    @Override
    public TodoSaveDTO todoSaveDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoSaveDTO todoSaveDTO = new TodoSaveDTO();

        todoSaveDTO.setId( todo.getId() );
        todoSaveDTO.setName( todo.getName() );

        return todoSaveDTO;
    }

    @Override
    public TodoDTO toTodoDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        long id = 0L;
        String name = null;

        id = todo.getId();
        name = todo.getName();

        TodoDTO todoDTO = new TodoDTO( id, name );

        return todoDTO;
    }

    @Override
    public Todo toTodo(TodoUpdateDTO updateDTO) {
        if ( updateDTO == null ) {
            return null;
        }

        long id = 0L;
        String name = null;

        id = updateDTO.getId();
        name = updateDTO.getName();

        Todo todo = new Todo( id, name );

        return todo;
    }

    @Override
    public TodoUpdateDTO toTodoUpdateDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoUpdateDTO todoUpdateDTO = new TodoUpdateDTO();

        todoUpdateDTO.setId( todo.getId() );
        todoUpdateDTO.setName( todo.getName() );

        return todoUpdateDTO;
    }
}
