package com.zanfardino.homework.exceptions;

/**
 * Created on 9/21/17.
 */
public class JsonProcessingException extends RuntimeException {

    public JsonProcessingException() {
    }

    public JsonProcessingException(final String message) {
        super(message);
    }

    public JsonProcessingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JsonProcessingException(final Throwable cause) {
        super(cause);
    }

    public JsonProcessingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
