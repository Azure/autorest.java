package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteAsyncRelativeRetrySucceeded operation. */
public final class LRORetrysDeleteAsyncRelativeRetrySucceededResponse
        extends ResponseBase<LRORetrysDeleteAsyncRelativeRetrySucceededHeaders, Void> {
    /**
     * Creates an instance of LRORetrysDeleteAsyncRelativeRetrySucceededResponse.
     *
     * @param request the request which resulted in this LRORetrysDeleteAsyncRelativeRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LRORetrysDeleteAsyncRelativeRetrySucceededResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LRORetrysDeleteAsyncRelativeRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
