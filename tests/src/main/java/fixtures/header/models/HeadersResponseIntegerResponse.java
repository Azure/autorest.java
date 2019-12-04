package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the responseInteger operation.
 */
public final class HeadersResponseIntegerResponse extends ResponseBase<HeadersResponseIntegerHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseIntegerResponse.
     * 
     * @param request the request which resulted in this HeadersResponseIntegerResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseIntegerResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HeadersResponseIntegerHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
