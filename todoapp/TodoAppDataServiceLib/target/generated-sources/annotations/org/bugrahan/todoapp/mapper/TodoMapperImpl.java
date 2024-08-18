package org.bugrahan.todoapp.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.bugrahan.todoapp.dto.TodoDTO;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;
import org.bugrahan.todoapp.entity.Todo;
import org.bugrahan.todoapp.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-19T00:45:15+0300",
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
        LocalDate endDate = null;
        long id = 0L;
        String name = null;

        isCompleted = saveDTO.isCompleted();
        endDate = saveDTO.getEndDate();
        id = saveDTO.getId();
        name = saveDTO.getName();

        User user = null;

        Todo todo = new Todo( id, name, isCompleted, endDate, user );

        todo.setCompleted( saveDTO.isCompleted() );

        return todo;
    }

    @Override
    public TodoSaveDTO todoSaveDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        boolean isCompleted = false;
        LocalDate endDate = null;
        long id = 0L;
        String name = null;

        isCompleted = todo.isCompleted();
        endDate = todo.getEndDate();
        id = todo.getId();
        name = todo.getName();

        TodoSaveDTO todoSaveDTO = new TodoSaveDTO( id, name, isCompleted, endDate );

        todoSaveDTO.setCompleted( todo.isCompleted() );

        return todoSaveDTO;
    }

    @Override
    public Todo toTodo(TodoDTO todoDTO) {
        if ( todoDTO == null ) {
            return null;
        }

        boolean isCompleted = false;
        LocalDate endDate = null;
        long id = 0L;
        String name = null;

        isCompleted = todoDTO.isCompleted();
        endDate = todoDTO.getEndDate();
        id = todoDTO.getId();
        name = todoDTO.getName();

        User user = null;

        Todo todo = new Todo( id, name, isCompleted, endDate, user );

        todo.setCompleted( todoDTO.isCompleted() );

        return todo;
    }

    @Override
    public TodoDTO toTodoDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        boolean isCompleted = false;
        LocalDate endDate = null;
        long id = 0L;
        String name = null;

        isCompleted = todo.isCompleted();
        endDate = todo.getEndDate();
        id = todo.getId();
        name = todo.getName();

        TodoDTO todoDTO = new TodoDTO( id, name, isCompleted, endDate );

        todoDTO.setCompleted( todo.isCompleted() );

        return todoDTO;
    }

    @Override
    public Todo toTodo(TodoUpdateDTO updateDTO) {
        if ( updateDTO == null ) {
            return null;
        }

        boolean isCompleted = false;
        LocalDate endDate = null;
        long id = 0L;
        String name = null;

        isCompleted = updateDTO.isCompleted();
        endDate = updateDTO.getEndDate();
        id = updateDTO.getId();
        name = updateDTO.getName();

        User user = null;

        Todo todo = new Todo( id, name, isCompleted, endDate, user );

        todo.setCompleted( updateDTO.isCompleted() );

        return todo;
    }

    @Override
    public TodoUpdateDTO toTodoUpdateDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        boolean isCompleted = false;
        LocalDate endDate = null;
        long id = 0L;
        String name = null;

        isCompleted = todo.isCompleted();
        endDate = todo.getEndDate();
        id = todo.getId();
        name = todo.getName();

        TodoUpdateDTO todoUpdateDTO = new TodoUpdateDTO( id, name, isCompleted, endDate );

        todoUpdateDTO.setCompleted( todo.isCompleted() );

        return todoUpdateDTO;
    }
}
