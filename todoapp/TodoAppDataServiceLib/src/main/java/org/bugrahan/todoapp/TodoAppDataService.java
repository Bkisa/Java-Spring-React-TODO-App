/*-------------------------------------------------------------
	FILE		: TodoAppDataService.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 29th Oct 2023

	TodoAppDataService Class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
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

            return StreamSupport.stream(todos.spliterator(), false).map(m_mapperInject.getTodoMapper()::toTodoDTO).toList();
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
            log.info("TodoAppDataService.findAllTodos: id -> {}", id);
            var todoOpt = m_todoAppHelper.findTodoById(id);

            return todoOpt.map(m_mapperInject.getTodoMapper()::toTodoDTO);
        }
        catch (RepositoryException ex) {
            log.error("TodoAppDataService.findAllTodos: id -> {}, Exception -> RepositoryException, Message -> {}", id, ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findTodoByName: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("TodoAppDataService.findAllTodos: id -> {}, Exception -> {}, Message -> {}", id, ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("TodoAppDataService.findAllTodos: Exception", ex);
        }
    }

    public Optional<TodoDTO> findTodoByName(String name)
    {
        try {
            log.info("TodoAppDataService.findTodoByName: id -> {}", name);
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
}
