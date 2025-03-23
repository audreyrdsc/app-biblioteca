package com.unifap.biblioteca.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.unifap.biblioteca.exceptions.EntityNotFoundException;

/**
 * Controller advice exception handlers
 * @author Thiago Pinheiro do Nascimento
 * @since 13 mar 2025
 **/
@ControllerAdvice
public class AdviceExceptionHandler {

    /**
     * Redirection for catching illegal argument exception
     * @author Thiago Pinheiro do Nascimento
     * @since 13 mar 2025
     **/
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return "errors/400";
    }

    /**
     * Redirection for catching entity not found exception
     * @author Thiago Pinheiro do Nascimento
     * @since 13 mar 2025
     **/
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(EntityNotFoundException e) {
        return "errors/404";
    }

    /**
     * Redirection for catching no handle exception
     * @author Thiago Pinheiro do Nascimento
     * @since 13 mar 2025
     **/
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandleFound(NoHandlerFoundException e) {
        return "errors/404";
    }

    /**
     * Redirection for catching no resource exception
     * @author Thiago Pinheiro do Nascimento
     * @since 13 mar 2025
     **/
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFound(NoResourceFoundException e) {
        return "errors/404";
    }

    /**
     * Redirection for catching exception
     * @author Thiago Pinheiro do Nascimento
     * @since 13 mar 2025
     **/
    @ExceptionHandler(Exception.class)
    public String handleUncaught(Exception e) {
        return "errors/500";
    }
}
