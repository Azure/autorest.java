package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;

/**
 * Contains all response data for the delete202Retry200 operation.
 */
public final class LROsDelete202Retry200Response extends ResponseBase<LROsDelete202Retry200Headers, ProductInner> {
    /**
     * Creates an instance of LROsDelete202Retry200Response.
     * 
     * @param request the request which resulted in this LROsDelete202Retry200Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDelete202Retry200Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, ProductInner value, LROsDelete202Retry200Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /**
     * @return the deserialized response body.
     */
    @Override
    public ProductInner getValue() {
        return super.getValue();
    }
}
