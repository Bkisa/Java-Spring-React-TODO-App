package com.techcareer.todoapp.dal;

import com.techcareer.todoapp.controller.CountResponse;
import com.techcareer.todoapp.controller.TodoCreateRequest;
import com.techcareer.todoapp.controller.TodoUpdateRequest;
import com.techcareer.todoapp.entity.Todo;
import com.techcareer.todoapp.errorhandler.BadRequestException;
import com.techcareer.todoapp.errorhandler.InvalidPageException;
import com.techcareer.todoapp.errorhandler.ResourceNotFoundException;
import com.techcareer.todoapp.repository.ITodoPagingRepository;
import com.techcareer.todoapp.repository.ITodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Lazy
@Slf4j
public class TodoAppHelper {
    private final ITodoPagingRepository m_todoPagingRepository;
    private final ITodoRepository m_todoRepository;

    public TodoAppHelper(ITodoPagingRepository todoPagingRepository, ITodoRepository todoRepository)
    {
        m_todoPagingRepository = todoPagingRepository;
        m_todoRepository = todoRepository;
    }

    public Iterable<Todo> findAllTodos()
    {
        try {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.findAllTodos: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.findAllTodos", ex);
        }
    }

    /////////////////////////////////////////////////////////////////
    public Todo create(TodoCreateRequest todoCreateRequest, String username)
    {
        try {
            Todo todo = new Todo(todoCreateRequest.getTitle(), todoCreateRequest.getTargetDate(), username);
            return m_todoRepository.save(todo);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.create: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.create", ex);
        }
    }

    public Todo readById(long id, String username)
    {
        try {
            Todo todo = m_todoRepository.findByUsernameAndId(username, id);
            if (todo == null) {
                throw new ResourceNotFoundException("Todo not found");
            }
            return todo;
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.readById: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.readById", ex);
        }
    }

    public List<Todo> readAll(String username)
    {
        try {
            return m_todoRepository.findAllByUsername(username);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.readAll: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.readAll", ex);
        }
    }

    public List<Todo> readAllPageable(String username, String pageNumber, String pageSize)
    {
        try {
            int _pageNumber = pageNumberStringToInteger(pageNumber);
            int _pageSize = pageSizeStringToInteger(pageSize);

            Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
            return m_todoPagingRepository.findAllByUsername(username, pageable);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.readAllPageable: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.readAllPageable", ex);
        }
    }

    public List<Todo> readAllByIsCompleted(String username, String isCompleted)
    {
        try {
            boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
            return m_todoRepository.findAllByUsernameAndIsCompleted(username, _isCompleted);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.readAllByIsCompleted: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.readAllByIsCompleted", ex);
        }
    }

    public List<Todo> readAllByIsCompletedPageable(String username, String isCompleted, String pageNumber, String pageSize)
    {
        try {
            boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
            int _pageNumber = pageNumberStringToInteger(pageNumber);
            int _pageSize = pageSizeStringToInteger(pageSize);

            Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
            return m_todoPagingRepository.findAllByUsernameAndIsCompleted(username, _isCompleted, pageable);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.readAllByIsCompletedPageable: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.readAllByIsCompletedPageable", ex);
        }
    }


    public void deleteById(long id, String username)
    {
        try {
            Todo todo = m_todoRepository.findByUsernameAndId(username, id);
            m_todoRepository.deleteById(id);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.deleteById: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.deleteById", ex);
        }
    }

    public Todo updateById(long id, TodoUpdateRequest todoUpdateRequest, String username)
    {
        try {
            Todo todo = m_todoRepository.findByUsernameAndId(username, id);

            todo.setTitle(todoUpdateRequest.getTitle());
            todo.setTargetDate(todoUpdateRequest.getTargetDate());
            return m_todoRepository.save(todo);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.updateById: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.updateById", ex);
        }
    }


    public Todo markCompleteById(long id, String username)
    {
        try {
            Todo todo = m_todoRepository.findByUsernameAndId(username, id);

            todo.setIsCompleted(! todo.getIsCompleted());
            return m_todoRepository.save(todo);
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.markCompleteById: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.markCompleteById", ex);
        }
    }

    public CountResponse countAll(String username)
    {
        try {
            return new CountResponse(m_todoRepository.countByUsername(username));
        }
        catch (Throwable ex) {
            log.error("TodoAppHelper.countAll: Exception -> {}, Message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("TodoAppHelper.countAll", ex);
        }
    }


    private boolean isCompletedStringToBoolean(String isCompleted)
    {
        try {
            return Boolean.parseBoolean(isCompleted);
        }
        catch (Exception e) {
            throw new BadRequestException("Invalid isCompleted");
        }
    }

    private int pageNumberStringToInteger(String pageNumber)
    {
        int _pageNumber;

        try {
            _pageNumber = Integer.parseInt(pageNumber);
        }
        catch (Exception e) {
            throw new InvalidPageException("Invalid Page Number");
        }

        if (_pageNumber < 0) {
            throw new InvalidPageException("Invalid page number");
        }

        return _pageNumber;
    }

    private int pageSizeStringToInteger(String pageSize)
    {
        int _pageSize;

        try {
            _pageSize = Integer.parseInt(pageSize);
        }
        catch (Exception e) {
            throw new InvalidPageException("Invalid Page Size");
        }

        if (_pageSize < 1) {
            throw new InvalidPageException("Invalid page size");
        }

        return _pageSize;
    }
}
