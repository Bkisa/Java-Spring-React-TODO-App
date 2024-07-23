package com.techcareer.todoapp.errorhandler;

public class InvalidAuthenticationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidAuthenticationException(String message)
    {
        super(message);
    }
}
