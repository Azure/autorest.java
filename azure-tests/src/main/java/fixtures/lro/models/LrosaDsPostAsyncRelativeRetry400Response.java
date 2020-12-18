package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the postAsyncRelativeRetry400 operation.
 */
public final class LrosaDsPostAsyncRelativeRetry400Response extends ResponseBase<LrosaDsPostAsyncRelativeRetry400Headers, Void> {
    /**
     * Creates an instance of LrosaDsPostAsyncRelativeRetry400Response.
     * 
     * @param request the request which resulted in this LrosaDsPostAsyncRelativeRetry400Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPostAsyncRelativeRetry400Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, LrosaDsPostAsyncRelativeRetry400Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
