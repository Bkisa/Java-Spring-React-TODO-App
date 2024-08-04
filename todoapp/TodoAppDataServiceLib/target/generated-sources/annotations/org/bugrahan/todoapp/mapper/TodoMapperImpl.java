package org.bugrahan.todoapp.mapper;

import javax.annotation.processing.Generated;
import org.bugrahan.todoapp.dto.TodoDTO;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;
import org.bugrahan.todoapp.entity.Todo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-04T21:55:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TodoMapperImpl implements ITodoMapper {

    @Override
    public Todo toTodo(TodoSaveDTO saveDTO) {
        if ( saveDTO == null ) {
            return null;
        }

        boolean isCompleted = false;
        long id = 0L;
        String name = null;

        isCompleted = saveDTO.isCompleted();
        id = saveDTO.getId();
        name = saveDTO.getName();

        Todo todo = new Todo( id, name, isCompleted );

        todo.setCompleted( saveDTO.isCompleted() );

        return todo;
    }

    @Override
    public TodoSaveDTO todoSaveDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        boolean isCompleted = false;
        long id = 0L;
        String name = null;

        isCompleted = todo.isCompleted();
        id = todo.getId();
        name = todo.getName();

        TodoSaveDTO todoSaveDTO = new TodoSaveDTO( id, name, isCompleted );

        todoSaveDTO.setCompleted( todo.isCompleted() );

        return todoSaveDTO;
    }

    @Override
    public Todo toTodo(TodoDTO todoDTO) {
        if ( todoDTO == null ) {
            return null;
        }

        boolean isCompleted = false;
        long id = 0L;
        String name = null;

        isCompleted = todoDTO.isCompleted();
        id = todoDTO.getId();
        name = todoDTO.getName();

        Todo todo = new Todo( id, name, isCompleted );

        todo.setCompleted( todoDTO.isCompleted() );

        return todo;
    }

    @Override
    public TodoDTO toTodoDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        boolean isCompleted = false;
        long id = 0L;
        String name = null;

        isCompleted = todo.isCompleted();
        id = todo.getId();
        name = todo.getName();

        TodoDTO todoDTO = new TodoDTO( id, name, isCompleted );

        todoDTO.setCompleted( todo.isCompleted() );

        return todoDTO;
    }

    @Override
    public Todo toTodo(TodoUpdateDTO updateDTO) {
        if ( updateDTO == null ) {
            return null;
        }

        boolean isCompleted = false;
        long id = 0L;
        String name = null;

        isCompleted = updateDTO.isCompleted();
        id = updateDTO.getId();
        name = updateDTO.getName();

        Todo todo = new Todo( id, name, isCompleted );

        todo.setCompleted( updateDTO.isCompleted() );

        return todo;
    }

    @Override
    public TodoUpdateDTO toTodoUpdateDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        boolean isCompleted = false;
        long id = 0L;
        String name = null;

        isCompleted = todo.isCompleted();
        id = todo.getId();
        name = todo.getName();

        TodoUpdateDTO todoUpdateDTO = new TodoUpdateDTO( id, name, isCompleted );

        todoUpdateDTO.setCompleted( todo.isCompleted() );

        return todoUpdateDTO;
    }
}
