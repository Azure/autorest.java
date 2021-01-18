package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteNonRetry400 operation. */
public final class LrosaDsDeleteNonRetry400Response extends ResponseBase<LrosaDsDeleteNonRetry400Headers, Void> {
    /**
     * Creates an instance of LrosaDsDeleteNonRetry400Response.
     *
     * @param request the request which resulted in this LrosaDsDeleteNonRetry400Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsDeleteNonRetry400Response(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        Void value,
        LrosaDsDeleteNonRetry400Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
