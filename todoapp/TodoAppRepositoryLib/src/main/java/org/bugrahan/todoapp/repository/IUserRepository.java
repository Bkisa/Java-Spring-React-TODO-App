package org.bugrahan.todoapp.repository;

import org.bugrahan.todoapp.entity.Todo;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IUserRepository extends ICrudRepository<Todo, Long> {
    Iterable<Todo> findAllByIdAndName(long id, String name);
    Iterable<Todo> findCompletedById(long id);
    Iterable<Todo> findNotCompletedById(long id);
    Iterable<Todo> deleteNotCompletedById(long id);

}
