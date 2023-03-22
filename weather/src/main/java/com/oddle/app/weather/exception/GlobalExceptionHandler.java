package com.oddle.app.weather.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("Exception: ", ex);
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                "Internal server error");
    }

    @ExceptionHandler(CommonBusinessException.class)
    public ErrorMessage commonBusinessExceptionHandler(CommonBusinessException ex, WebRequest request) {
        log.error("Common Business Exception: ", ex);
        int code = ex.getCode() != 0 ? ex.getCode() : HttpStatus.INTERNAL_SERVER_ERROR.value();
        return new ErrorMessage(
                code,
                new Date(),
                ex.getMessage(),
                HttpStatus.valueOf(code).getReasonPhrase());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorMessage handleBindException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder("");
        e.getConstraintViolations().forEach(error -> message.append(error.getMessage()));
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                message.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
