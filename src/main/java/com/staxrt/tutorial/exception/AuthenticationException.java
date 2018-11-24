package com.staxrt.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Givantha Kalansuriya
 * @Project spring-boot-rest-api-auth-jwt-tutorial
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends Exception {

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     */
    public AuthenticationException(String message) {
        super(message);
    }
}
