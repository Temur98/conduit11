package uz.najottalim.javan6.exceptionshandler;

import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.najottalim.javan6.customexseptions.NoResourceFoundException;
import uz.najottalim.javan6.dto.ErrorDto;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundHandler(NoResourceFoundException ex){
        return ErrorDto.builder().error(ex.getMessage()).build();
    }
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundHandler(Throwable ex){
        return ErrorDto.builder().error(ex.getMessage()).build();
    }
}
