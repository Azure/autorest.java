package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the postAsyncRetrySucceeded operation. */
public final class LrosCustomHeadersPostAsyncRetrySucceededResponse
    extends ResponseBase<LrosCustomHeadersPostAsyncRetrySucceededHeaders, Void> {
    /**
     * Creates an instance of LrosCustomHeadersPostAsyncRetrySucceededResponse.
     *
     * @param request the request which resulted in this LrosCustomHeadersPostAsyncRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosCustomHeadersPostAsyncRetrySucceededResponse(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        Void value,
        LrosCustomHeadersPostAsyncRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
