// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.headexceptions.models;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpResponse;

/**
 * CustomizedException
 */
public class CustomizedException extends HttpResponseException {

    /**
     * Initializes a new instance of the CustomizedException class.
     *
     * @param message the exception message or the response content if a message is not available
     * @param response the HTTP response
     */
    public CustomizedException(final String message, final HttpResponse response) {
        super(message, response);
    }

    /**
     * Initializes a new instance of the CustomizedException class.
     *
     * @param message the exception message or the response content if a message is not available
     * @param response the HTTP response
     * @param value the deserialized response value
     */
    public CustomizedException(final String message, final HttpResponse response, final Object value) {
        super(message, response, value);
    }

    /**
     * Initializes a new instance of the CustomizedException class.
     *
     * @param message the exception message or the response content if a message is not available
     * @param response the HTTP response
     * @param cause the Throwable which caused the creation of this ResourceExistsException
     */
    public CustomizedException(final String message, final HttpResponse response, final Throwable cause) {
        super(message, response, cause);
    }
}
