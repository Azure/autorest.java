package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;

/**
 * Contains all response data for the deleteProvisioning202Accepted200Succeeded operation.
 */
public final class LroRetrysDeleteProvisioning202Accepted200SucceededResponse extends ResponseBase<LroRetrysDeleteProvisioning202Accepted200SucceededHeaders, ProductInner> {
    /**
     * Creates an instance of LroRetrysDeleteProvisioning202Accepted200SucceededResponse.
     * 
     * @param request the request which resulted in this LroRetrysDeleteProvisioning202Accepted200SucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LroRetrysDeleteProvisioning202Accepted200SucceededResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, ProductInner value, LroRetrysDeleteProvisioning202Accepted200SucceededHeaders headers) {
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
