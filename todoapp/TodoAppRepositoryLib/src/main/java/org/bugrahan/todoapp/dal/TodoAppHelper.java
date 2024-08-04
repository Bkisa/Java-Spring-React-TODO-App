package org.bugrahan.todoapp.dal;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.todoapp.repository.ITodoRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.bugrahan.todoapp.entity.Todo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
@Lazy
@Slf4j
public class TodoAppHelper {
    private final ITodoRepository m_todoRepository;

    public TodoAppHelper(ITodoRepository todoRepository)
    {
        m_todoRepository = todoRepository;
    }

    public Iterable<Todo> findAllTodos()
    {
        try {
            log.info("TodoAppHelper.findAllTodos");
            return m_todoRepository.findAll();
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findAllTodos: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findAllTodos:", ex);
        }
    }

    public void deleteById(long id)
    {
        try {
            log.info("TodoAppHelper.deleteById: Id -> {}", id);
            m_todoRepository.deleteById(id);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.deleteById: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.deleteById:", ex);
        }
    }

    public Optional<Todo> findTodoById(long id)
    {
        try {
            log.info("TodoAppHelper.findTodoById: Id -> {}", id);
            return m_todoRepository.findById(id);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findTodoById: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findTodoById:", ex);
        }
    }

    public Optional<Todo> findTodoByName(String name)
    {
        try {
            log.info("TodoAppHelper.findTodoByName: name -> {}", name);
            return StreamSupport.stream(m_todoRepository.findByName(name).spliterator(), false).findFirst();
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findTodoByName: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findTodoByName:", ex);
        }
    }

    public Todo saveTodo(Todo todo)
    {
        try {
            log.info("TodoAppHelper.saveTodo: {}", todo.toString());
            return m_todoRepository.save(todo);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.saveTodo: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.saveTodo:", ex);
        }
    }

    public Todo updateTodo(Todo todo)
    {
        try {
            log.info("TodoAppHelper.updateTodo: {}", todo.toString());
            return m_todoRepository.updateTodo(todo);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.updateTodo: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.updateTodo:", ex);
        }
    }
}
