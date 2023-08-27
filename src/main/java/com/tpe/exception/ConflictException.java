package com.tpe.exception;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//11
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
