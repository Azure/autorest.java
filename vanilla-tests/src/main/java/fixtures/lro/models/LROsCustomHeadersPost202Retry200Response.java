package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the post202Retry200 operation. */
public final class LROsCustomHeadersPost202Retry200Response
        extends ResponseBase<LROsCustomHeadersPost202Retry200Headers, Void> {
    /**
     * Creates an instance of LROsCustomHeadersPost202Retry200Response.
     *
     * @param request the request which resulted in this LROsCustomHeadersPost202Retry200Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsCustomHeadersPost202Retry200Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LROsCustomHeadersPost202Retry200Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
