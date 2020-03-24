package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the responseExistingKey operation.
 */
public final class HeadersResponseExistingKeyResponse extends ResponseBase<HeadersResponseExistingKeyHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseExistingKeyResponse.
     * 
     * @param request the request which resulted in this HeadersResponseExistingKeyResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseExistingKeyResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HeadersResponseExistingKeyHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
