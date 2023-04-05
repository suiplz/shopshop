package com.example.shopshop.handler;

import com.example.shopshop.handler.ex.CustomValidationException;
import com.example.shopshop.handler.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {

        return Script.back(e.getErrorMap().toString());
    }
}
