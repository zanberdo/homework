package com.zanfardino.homework.exceptions;

import java.util.Map;

public class HttpClientBadRequestReturnedException extends Exception {
    private final Integer responseCode;
    private final String responseMessage;
    private final Map<String, String> responseHeaders;

    public HttpClientBadRequestReturnedException(final Integer responseCode, final String responseMessage) {
        super(responseMessage);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        responseHeaders = null;
    }

    public HttpClientBadRequestReturnedException(final Integer responseCode, final String responseMessage, final String message) {
        super(message);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        responseHeaders = null;
    }

    public HttpClientBadRequestReturnedException(final Integer responseCode, final String responseMessage, final String message, final Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        responseHeaders = null;
    }

    public HttpClientBadRequestReturnedException(final Integer responseCode, final String responseMessage, final Map<String, String> responseHeaders) {
        super(responseMessage);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseHeaders = responseHeaders;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }
}
