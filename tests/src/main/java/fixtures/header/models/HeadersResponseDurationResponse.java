package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the responseDuration operation.
 */
public final class HeadersResponseDurationResponse extends ResponseBase<HeadersResponseDurationHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseDurationResponse.
     * 
     * @param request the request which resulted in this HeadersResponseDurationResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseDurationResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HeadersResponseDurationHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
