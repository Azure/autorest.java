package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the deleteAsyncRelativeRetry400 operation.
 */
public final class LrosaDsDeleteAsyncRelativeRetry400Response extends ResponseBase<LrosaDsDeleteAsyncRelativeRetry400Headers, Void> {
    /**
     * Creates an instance of LrosaDsDeleteAsyncRelativeRetry400Response.
     * 
     * @param request the request which resulted in this LrosaDsDeleteAsyncRelativeRetry400Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsDeleteAsyncRelativeRetry400Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, LrosaDsDeleteAsyncRelativeRetry400Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
