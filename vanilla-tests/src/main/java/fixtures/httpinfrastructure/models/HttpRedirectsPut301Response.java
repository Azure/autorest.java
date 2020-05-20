package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the put301 operation. */
public final class HttpRedirectsPut301Response extends ResponseBase<HttpRedirectsPut301Headers, Void> {
    /**
     * Creates an instance of HttpRedirectsPut301Response.
     *
     * @param request the request which resulted in this HttpRedirectsPut301Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsPut301Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HttpRedirectsPut301Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
