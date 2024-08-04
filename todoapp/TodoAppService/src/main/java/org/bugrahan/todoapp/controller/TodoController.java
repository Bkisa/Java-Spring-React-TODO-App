package org.bugrahan.todoapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.bugrahan.todoapp.TodoAppDataService;
import org.bugrahan.todoapp.dto.TodoSaveDTO;
import org.bugrahan.todoapp.dto.TodoUpdateDTO;
import org.bugrahan.todoapp.error.BaseTodoError;
import org.bugrahan.todoapp.error.TodoErrorWithTodoId;
import org.bugrahan.todoapp.error.TodoErrorWithTodoName;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/techcareer/todo")
@Slf4j
@ComponentScan(basePackages = "org.bugrahan")
public class TodoController {
    private final TodoAppDataService m_todoAppDataService;

    public TodoController(TodoAppDataService todoAppDataService)
    {
        m_todoAppDataService = todoAppDataService;
    }

    @PostMapping("add")
    public ResponseEntity<Object> addTodo(@RequestBody TodoSaveDTO todoSaveDTO)
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.addTodo -> Todo: {}", todoSaveDTO.toString());
            returnValue = ResponseEntity.ok(m_todoAppDataService.saveTodo(todoSaveDTO));
        }
        catch (DataServiceException ex) {
            var todoError = new TodoErrorWithTodoName(todoSaveDTO.getName(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    , HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.addTodo -> Todo: {}, Exception: {}, Response {}",
                    todoSaveDTO, ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id)
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.deleteById -> Id: {}", id);
            m_todoAppDataService.deleteById(id);
            returnValue = ResponseEntity.ok("Delete todo successfully");
        }
        catch (DataServiceException ex) {
            var todoError = new TodoErrorWithTodoId((int) id, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.deleteById -> Id: {}, Exception: {}, Response {}", id, ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @GetMapping("find")
    public ResponseEntity<Object> findByName(@RequestParam String name)
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.findByName -> Name: {}", name);

            returnValue = ResponseEntity.ok(m_todoAppDataService.findTodoByName(name));
        }
        catch (DataServiceException ex) {
            var todoError = new TodoErrorWithTodoName(name, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.findByName -> Name: {}, Exception: {}, Response {}", name, ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") long id)
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.findById -> Id: {}", id);

            returnValue = ResponseEntity.ok(m_todoAppDataService.findTodoById(id));
        }
        catch (DataServiceException ex) {
            var todoError = new TodoErrorWithTodoId((int) id, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.findById -> Id: {}, Exception: {}, Response {}", id, ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @GetMapping("find/all")
    public ResponseEntity<Object> findAll()
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.findAll");

            returnValue = ResponseEntity.ok(m_todoAppDataService.findAllTodos());
        }
        catch (DataServiceException ex) {
            var todoError = new BaseTodoError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.findAll -> Exception: {}, Response {}", ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @GetMapping("find/completed")
    public ResponseEntity<Object> findAllCompleted()
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.findAllCompleted");

            returnValue = ResponseEntity.ok(m_todoAppDataService.findAllCompleted());
        }
        catch (DataServiceException ex) {
            var todoError = new BaseTodoError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.findAllCompleted -> Exception: {}, Response {}", ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @GetMapping("find/notcompleted")
    public ResponseEntity<Object> findAllNotCompleted()
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.findAllNotCompleted");

            returnValue = ResponseEntity.ok(m_todoAppDataService.findAllNotCompleted());
        }
        catch (DataServiceException ex) {
            var todoError = new BaseTodoError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.findAllNotCompleted -> Exception: {}, Response {}", ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody TodoUpdateDTO todoUpdateDTO)
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.update -> Todo: {}", todoUpdateDTO.toString());

            returnValue = ResponseEntity.ok(m_todoAppDataService.updateTodo(todoUpdateDTO));
        }
        catch (DataServiceException ex) {
            var todoError = new BaseTodoError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.update -> Exception: {}, Response {}", ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<Object> deleteAll()
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.deleteAll");
            m_todoAppDataService.deleteAll();
            returnValue = ResponseEntity.ok("Delete All Todos successfully");
        }
        catch (DataServiceException ex) {
            var todoError = new BaseTodoError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.deleteAll -> Exception: {}, Response {}",ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }

    @DeleteMapping("deleteCompletedAll")
    public ResponseEntity<Object> deleteCompletedAll()
    {
        ResponseEntity<Object> returnValue;

        try {
            log.info("TodoController.deleteCompletedAll");
            m_todoAppDataService.deleteCompletedAll();
            returnValue = ResponseEntity.ok("Delete All Todos successfully");
        }
        catch (DataServiceException ex) {
            var todoError = new BaseTodoError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("TodoController.deleteCompletedAll -> Exception: {}, Response {}", ex.getMessage(), todoError);

            returnValue = ResponseEntity.internalServerError().body(todoError);
        }
        return returnValue;
    }
}
