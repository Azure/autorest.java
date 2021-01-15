package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the post202NonRetry400 operation. */
public final class LrosaDsPost202NonRetry400Response extends ResponseBase<LrosaDsPost202NonRetry400Headers, Void> {
    /**
     * Creates an instance of LrosaDsPost202NonRetry400Response.
     *
     * @param request the request which resulted in this LrosaDsPost202NonRetry400Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPost202NonRetry400Response(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        Void value,
        LrosaDsPost202NonRetry400Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
