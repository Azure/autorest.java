package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the postAsyncRelativeRetryNoPayload operation. */
public final class LrosaDsPostAsyncRelativeRetryNoPayloadResponse
        extends ResponseBase<LrosaDsPostAsyncRelativeRetryNoPayloadHeaders, Void> {
    /**
     * Creates an instance of LrosaDsPostAsyncRelativeRetryNoPayloadResponse.
     *
     * @param request the request which resulted in this LrosaDsPostAsyncRelativeRetryNoPayloadResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPostAsyncRelativeRetryNoPayloadResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsPostAsyncRelativeRetryNoPayloadHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
