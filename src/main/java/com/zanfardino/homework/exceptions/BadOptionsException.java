package com.zanfardino.homework.exceptions;

/**
 * Created on 1/12/17.
 */
public class BadOptionsException extends RuntimeException {
    public BadOptionsException() {
    }

    public BadOptionsException(String message) {
        super(message);
    }

    public BadOptionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadOptionsException(Throwable cause) {
        super(cause);
    }

    public BadOptionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
