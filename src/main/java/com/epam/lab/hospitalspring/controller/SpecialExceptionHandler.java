package com.epam.lab.hospitalspring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpecialExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public void exception(Exception exception) {
        System.err.println(exception.getMessage());
    }
}
