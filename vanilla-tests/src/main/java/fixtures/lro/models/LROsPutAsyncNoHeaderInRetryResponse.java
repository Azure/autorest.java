package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the putAsyncNoHeaderInRetry operation. */
public final class LROsPutAsyncNoHeaderInRetryResponse
        extends ResponseBase<LROsPutAsyncNoHeaderInRetryHeaders, Product> {
    /**
     * Creates an instance of LROsPutAsyncNoHeaderInRetryResponse.
     *
     * @param request the request which resulted in this LROsPutAsyncNoHeaderInRetryResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPutAsyncNoHeaderInRetryResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LROsPutAsyncNoHeaderInRetryHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
