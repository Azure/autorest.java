package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the get301 operation.
 */
public final class HttpRedirectsGet301Response extends ResponseBase<HttpRedirectsGet301Headers, Void> {
    /**
     * Creates an instance of HttpRedirectsGet301Response.
     * 
     * @param request the request which resulted in this HttpRedirectsGet301Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsGet301Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, HttpRedirectsGet301Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
