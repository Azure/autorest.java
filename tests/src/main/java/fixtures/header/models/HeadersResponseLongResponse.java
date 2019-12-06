package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the responseLong operation.
 */
public final class HeadersResponseLongResponse extends ResponseBase<HeadersResponseLongHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseLongResponse.
     * 
     * @param request the request which resulted in this HeadersResponseLongResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseLongResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HeadersResponseLongHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
