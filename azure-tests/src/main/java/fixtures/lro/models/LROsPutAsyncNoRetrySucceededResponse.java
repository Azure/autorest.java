package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;

/**
 * Contains all response data for the putAsyncNoRetrySucceeded operation.
 */
public final class LROsPutAsyncNoRetrySucceededResponse extends ResponseBase<LROsPutAsyncNoRetrySucceededHeaders, ProductInner> {
    /**
     * Creates an instance of LROsPutAsyncNoRetrySucceededResponse.
     * 
     * @param request the request which resulted in this LROsPutAsyncNoRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPutAsyncNoRetrySucceededResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, ProductInner value, LROsPutAsyncNoRetrySucceededHeaders headers) {
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
