package org.bugrahan.todoapp.repository;

import org.bugrahan.todoapp.entity.Todo;
import org.csystem.data.exception.repository.ICrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ITodoRepository extends ICrudRepository<Todo, Long> {
    Iterable<Todo> findByName(String name);

    Todo updateTodo(Todo todo);

    Iterable<Todo> findAllCompleted();

    Iterable<Todo> findAllNotCompleted();

    void deleteCompletedTodos();

    void deleteAllTodos();

    List<Todo> findByEndDate(LocalDate endDate);
}
