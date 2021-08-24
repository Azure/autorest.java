package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteNoHeaderInRetry operation. */
public final class LROsDeleteNoHeaderInRetryResponse extends ResponseBase<LROsDeleteNoHeaderInRetryHeaders, Void> {
    /**
     * Creates an instance of LROsDeleteNoHeaderInRetryResponse.
     *
     * @param request the request which resulted in this LROsDeleteNoHeaderInRetryResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDeleteNoHeaderInRetryResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LROsDeleteNoHeaderInRetryHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
