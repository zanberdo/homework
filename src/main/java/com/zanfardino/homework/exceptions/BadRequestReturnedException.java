package com.zanfardino.homework.exceptions;

public class BadRequestReturnedException extends RuntimeException {
    public BadRequestReturnedException() {
    }

    public BadRequestReturnedException(String message) {
        super(message);
    }

    public BadRequestReturnedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestReturnedException(Throwable cause) {
        super(cause);
    }

    public BadRequestReturnedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
