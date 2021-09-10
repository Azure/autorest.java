package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the patch200SucceededIgnoreHeaders operation. */
public final class LROsPatch200SucceededIgnoreHeadersResponse
        extends ResponseBase<LROsPatch200SucceededIgnoreHeadersHeaders, Product> {
    /**
     * Creates an instance of LROsPatch200SucceededIgnoreHeadersResponse.
     *
     * @param request the request which resulted in this LROsPatch200SucceededIgnoreHeadersResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPatch200SucceededIgnoreHeadersResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LROsPatch200SucceededIgnoreHeadersHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
