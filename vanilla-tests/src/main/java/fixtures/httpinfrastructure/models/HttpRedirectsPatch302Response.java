package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the patch302 operation. */
public final class HttpRedirectsPatch302Response extends ResponseBase<HttpRedirectsPatch302Headers, Void> {
    /**
     * Creates an instance of HttpRedirectsPatch302Response.
     *
     * @param request the request which resulted in this HttpRedirectsPatch302Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsPatch302Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HttpRedirectsPatch302Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
