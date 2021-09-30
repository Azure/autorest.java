package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the putAsyncNoRetrySucceeded operation. */
public final class LROsPutAsyncNoRetrySucceededResponse
        extends ResponseBase<LROsPutAsyncNoRetrySucceededHeaders, Product> {
    /**
     * Creates an instance of LROsPutAsyncNoRetrySucceededResponse.
     *
     * @param request the request which resulted in this LROsPutAsyncNoRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPutAsyncNoRetrySucceededResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LROsPutAsyncNoRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
