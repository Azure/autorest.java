package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;

/**
 * Contains all response data for the putAsyncRelativeRetry400 operation.
 */
public final class LrosaDsPutAsyncRelativeRetry400Response extends ResponseBase<LrosaDsPutAsyncRelativeRetry400Headers, ProductInner> {
    /**
     * Creates an instance of LrosaDsPutAsyncRelativeRetry400Response.
     * 
     * @param request the request which resulted in this LrosaDsPutAsyncRelativeRetry400Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LrosaDsPutAsyncRelativeRetry400Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, ProductInner value, LrosaDsPutAsyncRelativeRetry400Headers headers) {
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
