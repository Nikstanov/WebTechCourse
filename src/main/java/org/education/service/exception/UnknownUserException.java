package org.education.service.exception;

public class UnknownUserException extends Exception{
    public UnknownUserException() {}

    public UnknownUserException(String message) {
        super(message);
    }

    public UnknownUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
