package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the post202NoRetry204 operation. */
public final class LROsPost202NoRetry204Response extends ResponseBase<LROsPost202NoRetry204Headers, Product> {
    /**
     * Creates an instance of LROsPost202NoRetry204Response.
     *
     * @param request the request which resulted in this LROsPost202NoRetry204Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPost202NoRetry204Response(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LROsPost202NoRetry204Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
