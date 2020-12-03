package com.azure.androidtest.fixtures.bodyarray.models;

import com.azure.android.core.http.exception.HttpResponseException;
import okhttp3.Response;

/**
 * Exception thrown for an invalid response with Error information.
 */
public final class ErrorException extends HttpResponseException {
    /**
     * Initializes a new instance of the ErrorException class.
     * 
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     */
    public ErrorException(String message, Response response) {
        super(message, response);
    }

    /**
     * Initializes a new instance of the ErrorException class.
     * 
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     * @param value the deserialized response value.
     */
    public ErrorException(String message, Response response, Error value) {
        super(message, response, value);
    }

    @Override
    public Error getValue() {
        return (Error) super.getValue();
    }
}
