package fixtures.deferredheaderdeserialization.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the responseString operation. */
public final class HeadersResponseStringResponse extends ResponseBase<HeadersResponseStringHeaders, Void> {
    /**
     * Creates an instance of HeadersResponseStringResponse.
     *
     * @param request the request which resulted in this HeadersResponseStringResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseStringResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersResponseStringHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
