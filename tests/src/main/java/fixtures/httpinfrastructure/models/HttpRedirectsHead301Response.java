package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the head301 operation.
 */
public final class HttpRedirectsHead301Response extends ResponseBase<HttpRedirectsHead301Headers, Void> {
    /**
     * Creates an instance of HttpRedirectsHead301Response.
     * 
     * @param request the request which resulted in this HttpRedirectsHead301Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsHead301Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HttpRedirectsHead301Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
