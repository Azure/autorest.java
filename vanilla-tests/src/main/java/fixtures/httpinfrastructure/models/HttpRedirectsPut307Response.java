package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the put307 operation. */
public final class HttpRedirectsPut307Response extends ResponseBase<HttpRedirectsPut307Headers, Void> {
    /**
     * Creates an instance of HttpRedirectsPut307Response.
     *
     * @param request the request which resulted in this HttpRedirectsPut307Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsPut307Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HttpRedirectsPut307Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
