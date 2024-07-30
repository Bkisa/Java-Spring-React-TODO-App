package com.techcareer.todoapp.controller;

import com.techcareer.todoapp.errorhandler.CustomException;
import com.techcareer.todoapp.service.TodoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request", response = CustomException.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = CustomException.class),
        @ApiResponse(code = 403, message = "Forbidden", response = CustomException.class),
        @ApiResponse(code = 404, message = "Not Found", response = CustomException.class)
})

public class TodoController {
    private final TodoService m_todoService;

    public TodoController(TodoService todoService)
    {
        m_todoService = todoService;
    }

    //....
}
