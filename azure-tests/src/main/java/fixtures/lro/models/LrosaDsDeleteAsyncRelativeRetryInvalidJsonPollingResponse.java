package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteAsyncRelativeRetryInvalidJsonPolling operation. */
public final class LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse
    extends ResponseBase<LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders, Void> {
    /**
     * Creates an instance of LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse.
     *
     * @param request the request which resulted in this LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        Void value,
        LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
