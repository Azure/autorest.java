package fixtures.azurespecials.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the customNamedRequestId operation. */
public final class HeadersCustomNamedRequestIdResponse extends ResponseBase<HeadersCustomNamedRequestIdHeaders, Void> {
    /**
     * Creates an instance of HeadersCustomNamedRequestIdResponse.
     *
     * @param request the request which resulted in this HeadersCustomNamedRequestIdResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersCustomNamedRequestIdResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersCustomNamedRequestIdHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
