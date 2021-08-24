package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteAsyncRelativeRetryNoStatus operation. */
public final class LrosaDsDeleteAsyncRelativeRetryNoStatusResponse
        extends ResponseBase<LrosaDsDeleteAsyncRelativeRetryNoStatusHeaders, Void> {
    /**
     * Creates an instance of LrosaDsDeleteAsyncRelativeRetryNoStatusResponse.
     *
     * @param request the request which resulted in this LrosaDsDeleteAsyncRelativeRetryNoStatusResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsDeleteAsyncRelativeRetryNoStatusResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsDeleteAsyncRelativeRetryNoStatusHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
