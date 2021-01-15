package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;

/** Contains all response data for the deleteProvisioning202DeletingFailed200 operation. */
public final class LROsDeleteProvisioning202DeletingFailed200Response
    extends ResponseBase<LROsDeleteProvisioning202DeletingFailed200Headers, ProductInner> {
    /**
     * Creates an instance of LROsDeleteProvisioning202DeletingFailed200Response.
     *
     * @param request the request which resulted in this LROsDeleteProvisioning202DeletingFailed200Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDeleteProvisioning202DeletingFailed200Response(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        ProductInner value,
        LROsDeleteProvisioning202DeletingFailed200Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public ProductInner getValue() {
        return super.getValue();
    }
}
