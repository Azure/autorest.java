package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the responseByte operation. */
public final class HeadersResponseByteResponse extends ResponseBase<HeadersResponseByteHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseByteResponse.
     *
     * @param request the request which resulted in this HeadersResponseByteResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseByteResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersResponseByteHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
