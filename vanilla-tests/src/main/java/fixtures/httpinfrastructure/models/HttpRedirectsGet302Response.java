package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the get302 operation. */
public final class HttpRedirectsGet302Response extends ResponseBase<HttpRedirectsGet302Headers, Void> {
    /**
     * Creates an instance of HttpRedirectsGet302Response.
     *
     * @param request the request which resulted in this HttpRedirectsGet302Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsGet302Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HttpRedirectsGet302Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
