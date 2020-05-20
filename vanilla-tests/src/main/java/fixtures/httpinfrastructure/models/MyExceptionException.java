package fixtures.httpinfrastructure.models;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpResponse;

/** Exception thrown for an invalid response with MyException information. */
public final class MyExceptionException extends HttpResponseException {
    /**
     * Initializes a new instance of the MyExceptionException class.
     *
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     */
    public MyExceptionException(String message, HttpResponse response) {
        super(message, response);
    }

    /**
     * Initializes a new instance of the MyExceptionException class.
     *
     * @param message the exception message or the response content if a message is not available.
     * @param response the HTTP response.
     * @param value the deserialized response value.
     */
    public MyExceptionException(String message, HttpResponse response, MyException value) {
        super(message, response, value);
    }

    @Override
    public MyException getValue() {
        return (MyException) super.getValue();
    }
}
