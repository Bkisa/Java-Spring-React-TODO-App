package org.bugrahan.todoapp;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.todoapp.dal.TodoAppHelper;
import org.bugrahan.todoapp.dto.TodoDTO;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;
import org.bugrahan.todoapp.mapper.MapperInject;
import org.csystem.data.exception.repository.RepositoryException;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class TodoAppDataService {
    private final TodoAppHelper m_todoAppHelper;
    private final MapperInject m_mapperInject;

    public TodoAppDataService(TodoAppHelper todoAppHelper, MapperInject mapperInject)
    {
        m_todoAppHelper = todoAppHelper;
        m_mapperInject = mapperInject;
    }

    public void deleteById(long id)
    {
        try {
            log.info("TodoAppDataService.deleteById: id -> {}, ", id);
            m_todoAppHelper.deleteById(id);
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.deleteById: id -> {}, Exception -> RepositoryException, Message -> {}", id, ex.getMessage());
            throw new DataServiceException("TodoAppDataService.deleteById: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.deleteById: id -> {}, Exception -> {}, Message -> {}", id, ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.deleteById: Exception", ex);
        }
    }

    public Iterable<TodoDTO> findAllTodos()
    {
        try {
            log.info("TodoAppDataService.findAllTodos:");
            var todos = m_todoAppHelper.findAllTodos();

            return StreamSupport.stream(todos.spliterator(), false)
                    .map(m_mapperInject.getTodoMapper()::toTodoDTO)
                    .toList();
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findAllTodos: Exception -> RepositoryException, Message -> {}", ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllTodos: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findAllTodos: Exception -> {}, Message -> {}", ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllTodos: Exception", ex);
        }
    }

    public Optional<TodoDTO> findTodoById(long id)
    {
        try {
            log.info("TodoAppDataService.findTodoById: id -> {}", id);
            var todoOpt = m_todoAppHelper.findTodoById(id);

            return todoOpt.map(m_mapperInject.getTodoMapper()::toTodoDTO);
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findTodoById: id -> {}, Exception -> RepositoryException, Message -> {}", id, ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodoById: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findTodoById: id -> {}, Exception -> {}, Message -> {}", id, ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodoById: Exception", ex);
        }
    }

    public Optional<TodoDTO> findTodoByName(String name)
    {
        try {
            log.info("TodoAppDataService.findTodoByName: name -> {}", name);
            var todoOpt = m_todoAppHelper.findTodoByName(name);

            return todoOpt.map(m_mapperInject.getTodoMapper()::toTodoDTO);
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findTodoByName: name -> {}, Exception -> RepositoryException, Message -> {}", name, ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodoByName: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findTodoByName: name -> {}, Exception -> {}, Message -> {}", name, ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodoByName: Exception", ex);
        }
    }

    public TodoSaveDTO saveTodo(TodoSaveDTO todoSaveDTO)
    {
        try {
            log.info("TodoAppDataService.saveTodo: Todo -> {}", todoSaveDTO.toString());

            var todo = m_mapperInject.getTodoMapper().toTodo(todoSaveDTO);

            return m_mapperInject.getTodoMapper().todoSaveDTO(m_todoAppHelper.saveTodo(todo));
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.saveTodo: name -> {}, Exception -> RepositoryException, Message -> {}", todoSaveDTO.getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.saveTodo: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.saveTodo: name -> {}, Exception -> {}, Message -> {}", todoSaveDTO.getName(), ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.saveTodo: Exception", ex);
        }
    }

    public TodoUpdateDTO updateTodo(TodoUpdateDTO todoUpdateDTO)
    {
        try {
            log.info("TodoAppDataService.updateTodo: Todo -> {}", todoUpdateDTO.toString());

            var todo = m_todoAppHelper.updateTodo(m_mapperInject.getTodoMapper().toTodo(todoUpdateDTO));

            return m_mapperInject.getTodoMapper().toTodoUpdateDTO(todo);
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.updateTodo: name -> {}, Exception -> RepositoryException, Message -> {}", todoUpdateDTO.getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.updateTodo: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.updateTodo: name -> {}, Exception -> {}, Message -> {}", todoUpdateDTO.getName(), ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.updateTodo: Exception", ex);
        }
    }

    public Iterable<TodoDTO> findAllNotCompleted()
    {
        try {
            log.info("TodoAppDataService.findAllNotCompleted:");
            var todos = m_todoAppHelper.findAllNotCompleted();

            return StreamSupport.stream(todos.spliterator(), false)
                    .map(m_mapperInject.getTodoMapper()::toTodoDTO)
                    .toList();
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findAllNotCompleted: Exception -> RepositoryException, Message -> {}", ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllNotCompleted: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findAllNotCompleted: Exception -> {}, Message -> {}", ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllNotCompleted: Exception", ex);
        }
    }

    public Iterable<TodoDTO> findAllCompleted()
    {
        try {
            log.info("TodoAppDataService.findAllCompleted:");
            var todos = m_todoAppHelper.findAllCompleted();

            return StreamSupport.stream(todos.spliterator(), false)
                    .map(m_mapperInject.getTodoMapper()::toTodoDTO)
                    .toList();
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findAllCompleted: Exception -> RepositoryException, Message -> {}", ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllCompleted: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findAllCompleted: Exception -> {}, Message -> {}", ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllCompleted: Exception", ex);
        }
    }

    public void deleteCompletedAll()
    {
        try {
            log.info("TodoAppDataService.deleteCompletedAll:");

            m_todoAppHelper.deleteCompletedAll();
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.deleteCompletedAll: Exception -> RepositoryException, Message -> {}", ex.getMessage());
            throw new DataServiceException("TodoAppDataService.deleteCompletedAll: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.deleteCompletedAll: Exception -> {}, Message -> {}", ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.deleteCompletedAll: Exception", ex);
        }
    }

    public void deleteAll()
    {
        try {
            log.info("TodoAppDataService.deleteAll:");
            m_todoAppHelper.deleteAll();
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.deleteAll: Exception -> RepositoryException, Message -> {}", ex.getMessage());
            throw new DataServiceException("TodoAppDataService.deleteAll: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.deleteAll: Exception -> {}, Message -> {}", ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.deleteAll: Exception", ex);
        }
    }

    public Iterable<TodoDTO> findTodosByEndDate(LocalDate endDate)
    {
        try {
            log.info("TodoAppDataService.findTodosByEndDate: endDate -> {}", endDate);
            var todos = m_todoAppHelper.findTodosByEndDate(endDate);

            return StreamSupport.stream(todos.spliterator(), false)
                    .map(m_mapperInject.getTodoMapper()::toTodoDTO)
                    .toList();
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findTodosByEndDate: endDate -> {}, Exception -> RepositoryException, Message -> {}", endDate, ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodosByEndDate: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findTodosByEndDate: endDate -> {}, Exception -> {}, Message -> {}", endDate, ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodosByEndDate: Exception", ex);
        }
    }
}
