package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.http.exception.HttpResponseException;
import okhttp3.Response;

/**
 * Exception thrown for an invalid response with NotFoundErrorBase information.
 */
public final class NotFoundErrorBaseException extends HttpResponseException {
    /**
     * Initializes a new instance of the NotFoundErrorBaseException class.
     * 
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     */
    public NotFoundErrorBaseException(String message, Response response) {
        super(message, response);
    }

    /**
     * Initializes a new instance of the NotFoundErrorBaseException class.
     * 
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     * @param value the deserialized response value.
     */
    public NotFoundErrorBaseException(String message, Response response, NotFoundErrorBase value) {
        super(message, response, value);
    }

    @Override
    public NotFoundErrorBase getValue() {
        return (NotFoundErrorBase) super.getValue();
    }
}
