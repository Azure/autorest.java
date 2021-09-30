package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteAsyncRelativeRetryInvalidHeader operation. */
public final class LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse
        extends ResponseBase<LrosaDsDeleteAsyncRelativeRetryInvalidHeaderHeaders, Void> {
    /**
     * Creates an instance of LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse.
     *
     * @param request the request which resulted in this LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsDeleteAsyncRelativeRetryInvalidHeaderHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
