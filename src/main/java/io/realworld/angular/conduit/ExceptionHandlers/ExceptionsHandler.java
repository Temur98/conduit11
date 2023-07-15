package io.realworld.angular.conduit.ExceptionHandlers;

import io.jsonwebtoken.ExpiredJwtException;
import io.realworld.angular.conduit.config.JWTSecurityCheckFilter;
import io.realworld.angular.conduit.dto.ErrorDTO;
import io.realworld.angular.conduit.dto.response.ErrorResponse;
import io.realworld.angular.conduit.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundHandler(NotFoundException e){
        ErrorDTO error = ErrorDTO.builder().error(List.of(e.getMessage())).build();
        return new ErrorResponse(error);
    }

    @ExceptionHandler(UsernameOrPasswordInvalid.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse notFoundHandler(UsernameOrPasswordInvalid e){
        ErrorDTO error = ErrorDTO.builder().error(List.of(e.getMessage())).build();
        return new ErrorResponse(error);
    }

    @ExceptionHandler(WrongSlugException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse notFoundHandler(WrongSlugException e){
        ErrorDTO error = ErrorDTO.builder().error(List.of(e.getMessage())).build();
        return new ErrorResponse(error);
    }

    @ExceptionHandler(NotRegisteredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse notFoundHandler(NotRegisteredException e){
        ErrorDTO error = ErrorDTO.builder().error(List.of(e.getMessage())).build();
        return new ErrorResponse(error);
    }


    @ExceptionHandler(JwtTokeNotValidExceptions.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse notFoundHandler(JwtTokeNotValidExceptions e){
        ErrorDTO error = ErrorDTO.builder().error(List.of(e.getMessage())).build();
        return new ErrorResponse(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }





}
