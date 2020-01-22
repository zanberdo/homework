package com.zanfardino.homework.exceptions;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;

/**
 * Created on 1/9/18.
 */
public class HttpClientServerErrorReturnedException extends HttpClientBadRequestReturnedException {

    public HttpClientServerErrorReturnedException(final Integer responseCode, final String responseMessage) {
        super(responseCode, responseMessage);
    }

    public HttpClientServerErrorReturnedException(final Integer responseCode, final String responseMessage, final String message) {
        super(responseCode, responseMessage, message);
    }

    public HttpClientServerErrorReturnedException(final Integer responseCode, final String responseMessage, final String message, final Throwable cause) {
        super(responseCode, responseMessage, message, cause);
    }

    public HttpClientServerErrorReturnedException(final Integer responseCode, final String responseMessage, final Map<String, String> responseHeaders) {
        super(responseCode, responseMessage, responseHeaders);
    }

}
