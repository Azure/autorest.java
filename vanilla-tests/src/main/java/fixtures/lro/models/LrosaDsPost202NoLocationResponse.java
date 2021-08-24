package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the post202NoLocation operation. */
public final class LrosaDsPost202NoLocationResponse extends ResponseBase<LrosaDsPost202NoLocationHeaders, Void> {
    /**
     * Creates an instance of LrosaDsPost202NoLocationResponse.
     *
     * @param request the request which resulted in this LrosaDsPost202NoLocationResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPost202NoLocationResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Void value,
            LrosaDsPost202NoLocationHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
