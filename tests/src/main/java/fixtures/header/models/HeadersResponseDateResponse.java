package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the responseDate operation.
 */
public final class HeadersResponseDateResponse extends ResponseBase<HeadersResponseDateHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseDateResponse.
     * 
     * @param request the request which resulted in this HeadersResponseDateResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseDateResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HeadersResponseDateHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
