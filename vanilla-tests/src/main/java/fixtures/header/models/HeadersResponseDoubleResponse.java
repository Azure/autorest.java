package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the responseDouble operation. */
public final class HeadersResponseDoubleResponse extends ResponseBase<HeadersResponseDoubleHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseDoubleResponse.
     *
     * @param request the request which resulted in this HeadersResponseDoubleResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseDoubleResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersResponseDoubleHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
