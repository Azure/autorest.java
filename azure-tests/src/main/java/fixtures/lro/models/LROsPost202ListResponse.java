package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import fixtures.lro.fluent.models.ProductInner;
import java.util.List;

/**
 * Contains all response data for the post202List operation.
 */
public final class LROsPost202ListResponse extends ResponseBase<LROsPost202ListHeaders, List<ProductInner>> {
    /**
     * Creates an instance of LROsPost202ListResponse.
     * 
     * @param request the request which resulted in this LROsPost202ListResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPost202ListResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, List<ProductInner> value, LROsPost202ListHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /**
     * @return the deserialized response body.
     */
    @Override
    public List<ProductInner> getValue() {
        return super.getValue();
    }
}
