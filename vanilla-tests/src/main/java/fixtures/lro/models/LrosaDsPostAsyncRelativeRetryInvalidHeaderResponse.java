package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the postAsyncRelativeRetryInvalidHeader operation. */
public final class LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse
        extends ResponseBase<LrosaDsPostAsyncRelativeRetryInvalidHeaderHeaders, Void> {
    /**
     * Creates an instance of LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse.
     *
     * @param request the request which resulted in this LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsPostAsyncRelativeRetryInvalidHeaderHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
