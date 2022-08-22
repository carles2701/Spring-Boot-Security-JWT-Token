package com.carles2701.springapp082.Spring.Boot.App82.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.rmi.UnexpectedException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends UnexpectedException {
    public NotFoundException(String message) {
        super(message);
    }

}
