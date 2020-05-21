package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the responseProtectedKey operation. */
public final class HeadersResponseProtectedKeyResponse extends ResponseBase<HeadersResponseProtectedKeyHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseProtectedKeyResponse.
     *
     * @param request the request which resulted in this HeadersResponseProtectedKeyResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseProtectedKeyResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersResponseProtectedKeyHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
