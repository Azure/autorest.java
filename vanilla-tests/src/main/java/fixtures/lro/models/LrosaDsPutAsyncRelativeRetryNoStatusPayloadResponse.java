package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the putAsyncRelativeRetryNoStatusPayload operation. */
public final class LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse
        extends ResponseBase<LrosaDsPutAsyncRelativeRetryNoStatusPayloadHeaders, Product> {
    /**
     * Creates an instance of LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse.
     *
     * @param request the request which resulted in this LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LrosaDsPutAsyncRelativeRetryNoStatusPayloadHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
