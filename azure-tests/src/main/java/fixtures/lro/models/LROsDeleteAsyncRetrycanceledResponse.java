package fixtures.lro.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/** Contains all response data for the deleteAsyncRetrycanceled operation. */
public final class LROsDeleteAsyncRetrycanceledResponse
    extends ResponseBase<LROsDeleteAsyncRetrycanceledHeaders, Void> {
    /**
     * Creates an instance of LROsDeleteAsyncRetrycanceledResponse.
     *
     * @param request the request which resulted in this LROsDeleteAsyncRetrycanceledResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public LROsDeleteAsyncRetrycanceledResponse(
        HttpRequest request,
        int statusCode,
        HttpHeaders rawHeaders,
        Void value,
        LROsDeleteAsyncRetrycanceledHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
