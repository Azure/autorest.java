package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the postAsyncRetrySucceeded operation. */
public final class LROsCustomHeadersPostAsyncRetrySucceededResponse
        extends ResponseBase<LROsCustomHeadersPostAsyncRetrySucceededHeaders, Void> {
    /**
     * Creates an instance of LROsCustomHeadersPostAsyncRetrySucceededResponse.
     *
     * @param request the request which resulted in this LROsCustomHeadersPostAsyncRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsCustomHeadersPostAsyncRetrySucceededResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LROsCustomHeadersPostAsyncRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
