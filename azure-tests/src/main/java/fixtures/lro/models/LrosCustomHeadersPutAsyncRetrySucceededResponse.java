package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;

/** Contains all response data for the putAsyncRetrySucceeded operation. */
public final class LrosCustomHeadersPutAsyncRetrySucceededResponse
    extends ResponseBase<LrosCustomHeadersPutAsyncRetrySucceededHeaders, ProductInner> {
    /**
     * Creates an instance of LrosCustomHeadersPutAsyncRetrySucceededResponse.
     *
     * @param request the request which resulted in this LrosCustomHeadersPutAsyncRetrySucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosCustomHeadersPutAsyncRetrySucceededResponse(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        ProductInner value,
        LrosCustomHeadersPutAsyncRetrySucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public ProductInner getValue() {
        return super.getValue();
    }
}
