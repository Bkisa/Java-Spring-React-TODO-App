package com.techcareer.todoapp.authentication.controller;

import com.techcareer.todoapp.errorhandler.CustomException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request", response = CustomException.class), @ApiResponse(code = 401, message = "Unauthorized", response = CustomException.class), @ApiResponse(code = 403, message = "Forbidden", response = CustomException.class), @ApiResponse(code = 404, message = "Not Found", response = CustomException.class)})

public class UserController {
    AuthenticationManager m_authenticationManager;

    PasswordEncoder m_passwordEncoder;
}
