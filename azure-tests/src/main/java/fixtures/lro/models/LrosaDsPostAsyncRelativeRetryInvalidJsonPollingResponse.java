package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the postAsyncRelativeRetryInvalidJsonPolling operation.
 */
public final class LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse extends ResponseBase<LrosaDsPostAsyncRelativeRetryInvalidJsonPollingHeaders, Void> {
    /**
     * Creates an instance of LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse.
     * 
     * @param request the request which resulted in this LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, LrosaDsPostAsyncRelativeRetryInvalidJsonPollingHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
