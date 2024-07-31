package org.bugrahan.repository;

import org.csystem.data.exception.repository.ICrudRepository;
import org.bugrahan.entity.Todo;

public interface ITodoRepository extends ICrudRepository<Todo, Long> {
    Iterable<Todo> findByName(String name);
    Todo updateTodo(Todo todo);
}
