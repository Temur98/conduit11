package io.realworld.angular.conduit.ExceptionHandlers;

import io.realworld.angular.conduit.dto.ErrorDTO;
import io.realworld.angular.conduit.dto.response.ErrorResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.exception.UsernameOrPasswordInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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


}
