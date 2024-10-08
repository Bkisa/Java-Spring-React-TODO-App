package org.bugrahan.todoapp.dal;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.todoapp.entity.Todo;
import org.bugrahan.todoapp.repository.ITodoRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    public Iterable<Todo> findAllCompleted()
    {
        try {
            log.info("TodoAppHelper.findAllCompleted");
            return m_todoRepository.findAllCompleted();
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findAllCompleted: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findAllCompleted:", ex);
        }
    }

    public Iterable<Todo> findAllNotCompleted()
    {
        try {
            log.info("TodoAppHelper.findAllNotCompleted");
            return m_todoRepository.findAllNotCompleted();
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findAllNotCompleted: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findAllNotCompleted:", ex);
        }
    }

    public void deleteCompletedAll()
    {
        try {
            log.info("TodoAppHelper.deleteCompletedAll");
            m_todoRepository.deleteCompletedTodos();
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.deleteCompletedAll: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.deleteCompletedAll:", ex);
        }
    }

    public void deleteAll()
    {
        try {
            log.info("TodoAppHelper.deleteAll");
            m_todoRepository.deleteAllTodos();
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.deleteAll: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.deleteAll:", ex);
        }
    }

    public Iterable<Todo> findTodosByEndDate(LocalDate endDate)
    {
        try {
            log.info("TodoAppHelper.findTodosByEndDate: endDate -> {}", endDate);
            return m_todoRepository.findByEndDate(endDate);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findTodosByEndDate: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findTodosByEndDate:", ex);
        }
    }
}
