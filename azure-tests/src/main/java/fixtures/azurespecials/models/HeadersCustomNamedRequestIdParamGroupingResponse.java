package fixtures.azurespecials.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the customNamedRequestIdParamGrouping operation. */
public final class HeadersCustomNamedRequestIdParamGroupingResponse
        extends ResponseBase<HeadersCustomNamedRequestIdParamGroupingHeaders, Void> {
    /**
     * Creates an instance of HeadersCustomNamedRequestIdParamGroupingResponse.
     *
     * @param request the request which resulted in this HeadersCustomNamedRequestIdParamGroupingResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HeadersCustomNamedRequestIdParamGroupingResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            HeadersCustomNamedRequestIdParamGroupingHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
