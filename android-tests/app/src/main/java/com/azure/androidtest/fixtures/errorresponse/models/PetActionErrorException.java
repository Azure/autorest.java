package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.http.exception.HttpResponseException;
import okhttp3.Response;

/**
 * Exception thrown for an invalid response with PetActionError information.
 */
public final class PetActionErrorException extends HttpResponseException {
    /**
     * Initializes a new instance of the PetActionErrorException class.
     * 
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     */
    public PetActionErrorException(String message, Response response) {
        super(message, response);
    }

    /**
     * Initializes a new instance of the PetActionErrorException class.
     * 
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     * @param value the deserialized response value.
     */
    public PetActionErrorException(String message, Response response, PetActionError value) {
        super(message, response, value);
    }

    @Override
    public PetActionError getValue() {
        return (PetActionError) super.getValue();
    }
}
