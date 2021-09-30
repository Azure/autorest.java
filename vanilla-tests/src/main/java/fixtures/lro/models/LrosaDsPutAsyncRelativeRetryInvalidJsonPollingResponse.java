package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the putAsyncRelativeRetryInvalidJsonPolling operation. */
public final class LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse
        extends ResponseBase<LrosaDsPutAsyncRelativeRetryInvalidJsonPollingHeaders, Product> {
    /**
     * Creates an instance of LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse.
     *
     * @param request the request which resulted in this LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LrosaDsPutAsyncRelativeRetryInvalidJsonPollingHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
