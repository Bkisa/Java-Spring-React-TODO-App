package com.techcareer.todoapp.repository;

import com.techcareer.todoapp.entity.Todo;
import org.csystem.data.exception.repository.ICrudRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ITodoPagingRepository extends ICrudRepository<Todo, Long> {
    //////////////////////////////////////////////////////////////
    List<Todo> findAllByUsername(String username, java.awt.print.Pageable pageable);

    List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, java.awt.print.Pageable pageable);

    List<Todo> findAllByUsername(String username, Pageable pageable);

    List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, Pageable pageable);
}

