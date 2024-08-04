package org.bugrahan.todoapp.repository;

import org.bugrahan.todoapp.entity.Todo;
import org.csystem.data.exception.repository.ICrudRepository;

public interface ITodoRepository extends ICrudRepository<Todo, Long> {
    Iterable<Todo> findByName(String name);

    Todo updateTodo(Todo todo);
}
