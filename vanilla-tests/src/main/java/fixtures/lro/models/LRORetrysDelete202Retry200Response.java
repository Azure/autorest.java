package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the delete202Retry200 operation.
 */
public final class LRORetrysDelete202Retry200Response extends ResponseBase<LRORetrysDelete202Retry200Headers, Void> {
    /**
     * Creates an instance of LRORetrysDelete202Retry200Response.
     * 
     * @param request the request which resulted in this LRORetrysDelete202Retry200Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LRORetrysDelete202Retry200Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, LRORetrysDelete202Retry200Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
