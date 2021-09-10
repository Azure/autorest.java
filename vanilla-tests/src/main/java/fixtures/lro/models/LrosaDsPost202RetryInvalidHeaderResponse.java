package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the post202RetryInvalidHeader operation. */
public final class LrosaDsPost202RetryInvalidHeaderResponse
        extends ResponseBase<LrosaDsPost202RetryInvalidHeaderHeaders, Void> {
    /**
     * Creates an instance of LrosaDsPost202RetryInvalidHeaderResponse.
     *
     * @param request the request which resulted in this LrosaDsPost202RetryInvalidHeaderResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPost202RetryInvalidHeaderResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsPost202RetryInvalidHeaderHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
