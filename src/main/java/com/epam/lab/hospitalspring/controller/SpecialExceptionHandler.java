package com.epam.lab.hospitalspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;

/**
 * Controller that catches all uncaught exceptions.
 */
@ControllerAdvice
public class SpecialExceptionHandler {
    @Autowired
    Logger log;
    @ExceptionHandler(value = Exception.class)
    public void exception(Exception exception) {
        log.error("Uncaught exception: " + exception.getMessage());
    }
}
