package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the patch202RetryWithAsyncAndLocationHeader operation. */
public final class LROsPatch202RetryWithAsyncAndLocationHeaderResponse
        extends ResponseBase<LROsPatch202RetryWithAsyncAndLocationHeaderHeaders, Product> {
    /**
     * Creates an instance of LROsPatch202RetryWithAsyncAndLocationHeaderResponse.
     *
     * @param request the request which resulted in this LROsPatch202RetryWithAsyncAndLocationHeaderResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsPatch202RetryWithAsyncAndLocationHeaderResponse(
            HttpRequest request,
            int statusCode,
            HttpHeaders rawHeaders,
            Product value,
            LROsPatch202RetryWithAsyncAndLocationHeaderHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /** @return the deserialized response body. */
    @Override
    public Product getValue() {
        return super.getValue();
    }
}
