package com.techcareer.todoapp.repository;

import com.techcareer.todoapp.entity.Todo;
import org.csystem.data.exception.repository.ICrudRepository;

import java.util.List;

public interface ITodoRepository extends ICrudRepository<Todo, Long> {
    List<Todo> findAllByUsername(String username);

    List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted);

    Todo findByUsernameAndId(String username, long Id);

    Long countByUsername(String username);

    Long countByUsernameAndIsCompleted(String username, boolean isCompleted);
}
