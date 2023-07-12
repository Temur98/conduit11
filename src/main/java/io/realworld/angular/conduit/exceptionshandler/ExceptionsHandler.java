package io.realworld.angular.conduit.exceptionshandler;

import io.realworld.angular.conduit.customexseption.EmailAlreadyRegisteredException;
import io.realworld.angular.conduit.customexseption.NoResourceFoundException;
import io.realworld.angular.conduit.customexseption.UsernameAlreadyRegisteredException;
import io.realworld.angular.conduit.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO notFoundHandler(NoResourceFoundException ex){
        ex.printStackTrace();
        return ErrorDTO.builder().errors(Map.of("error ",List.of(ex.getMessage())).toString()).build();
    }
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO emailAlreadyRegisteredHandler(EmailAlreadyRegisteredException ex){
        ex.printStackTrace();
        return ErrorDTO.builder().errors(Map.of("email ",List.of(ex.getMessage())).toString()).build();
    }

    @ExceptionHandler(UsernameAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO usernameAlreadyRegisteredHandler(UsernameAlreadyRegisteredException ex){
        ex.printStackTrace();
        return ErrorDTO.builder().errors(Map.of("username ",List.of(ex.getMessage())).toString()).build();
    }
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO notFoundHandler(Throwable ex){
        ex.printStackTrace();
        return ErrorDTO.builder().errors(Map.of("error ",List.of(ex.getMessage())).toString()).build();
    }
}
