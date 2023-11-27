package org.education.service.exception;

public class AlreadyExistException extends Exception{
    public AlreadyExistException() {}

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

}

