package com.games.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(PlayerCannotEnterToGameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePlayerCannotEnterToGameException(PlayerCannotEnterToGameException ex) {
        return new ErrorResponse(ex.getMessage());
    }}
