package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteAsyncRetrySucceeded operation. */
public final class LROsDeleteAsyncRetrySucceededResponse
    extends ResponseBase<LROsDeleteAsyncRetrySucceededHeaders, Void> {
    /**
     * Creates an instance of LROsDeleteAsyncRetrySucceededResponse.
     *
     * @param request the request which resulted in this LROsDeleteAsyncRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDeleteAsyncRetrySucceededResponse(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        Void value,
        LROsDeleteAsyncRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
