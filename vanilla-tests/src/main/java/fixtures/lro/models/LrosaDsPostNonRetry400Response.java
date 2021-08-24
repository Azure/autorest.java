package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the postNonRetry400 operation. */
public final class LrosaDsPostNonRetry400Response extends ResponseBase<LrosaDsPostNonRetry400Headers, Void> {
    /**
     * Creates an instance of LrosaDsPostNonRetry400Response.
     *
     * @param request the request which resulted in this LrosaDsPostNonRetry400Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPostNonRetry400Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsPostNonRetry400Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
