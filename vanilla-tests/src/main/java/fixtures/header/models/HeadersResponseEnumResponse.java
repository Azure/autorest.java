package fixtures.header.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the responseEnum operation. */
public final class HeadersResponseEnumResponse extends ResponseBase<HeadersResponseEnumHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseEnumResponse.
     *
     * @param request the request which resulted in this HeadersResponseEnumResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseEnumResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersResponseEnumHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
