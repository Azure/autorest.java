package fixtures.deferredheaderdeserialization.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the responseDatetimeRfc1123 operation. */
public final class HeadersResponseDatetimeRfc1123Response
        extends ResponseBase<HeadersResponseDatetimeRfc1123Headers, Void> {
    /**
     * Creates an instance of HeadersResponseDatetimeRfc1123Response.
     *
     * @param request the request which resulted in this HeadersResponseDatetimeRfc1123Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersResponseDatetimeRfc1123Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersResponseDatetimeRfc1123Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
