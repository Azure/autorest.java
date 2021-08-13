package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the postAsyncRelativeRetrySucceeded operation.
 */
public final class LRORetrysPostAsyncRelativeRetrySucceededResponse extends ResponseBase<LRORetrysPostAsyncRelativeRetrySucceededHeaders, Void> {
    /**
     * Creates an instance of LRORetrysPostAsyncRelativeRetrySucceededResponse.
     * 
     * @param request the request which resulted in this LRORetrysPostAsyncRelativeRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LRORetrysPostAsyncRelativeRetrySucceededResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, LRORetrysPostAsyncRelativeRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
