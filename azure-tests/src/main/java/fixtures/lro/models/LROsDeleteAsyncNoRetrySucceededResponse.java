package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the deleteAsyncNoRetrySucceeded operation.
 */
public final class LROsDeleteAsyncNoRetrySucceededResponse extends ResponseBase<LROsDeleteAsyncNoRetrySucceededHeaders, Void> {
    /**
     * Creates an instance of LROsDeleteAsyncNoRetrySucceededResponse.
     * 
     * @param request the request which resulted in this LROsDeleteAsyncNoRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDeleteAsyncNoRetrySucceededResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, LROsDeleteAsyncNoRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
