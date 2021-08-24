package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteProvisioning202Accepted200Succeeded operation. */
public final class LROsDeleteProvisioning202Accepted200SucceededResponse
        extends ResponseBase<LROsDeleteProvisioning202Accepted200SucceededHeaders, Product> {
    /**
     * Creates an instance of LROsDeleteProvisioning202Accepted200SucceededResponse.
     *
     * @param request the request which resulted in this LROsDeleteProvisioning202Accepted200SucceededResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDeleteProvisioning202Accepted200SucceededResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LROsDeleteProvisioning202Accepted200SucceededHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
