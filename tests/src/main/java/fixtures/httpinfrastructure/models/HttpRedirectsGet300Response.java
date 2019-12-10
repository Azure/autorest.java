package fixtures.httpinfrastructure.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import java.util.List;

/**
 * Contains all response data for the get300 operation.
 */
public final class HttpRedirectsGet300Response extends ResponseBase<HttpRedirectsGet300Headers, List<String>> {
    /**
     * Creates an instance of HttpRedirectsGet300Response.
     * 
     * @param request the request which resulted in this HttpRedirectsGet300Response.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public HttpRedirectsGet300Response(HttpRequest request, int statusCode, HttpHeaders rawHeaders, List<String> value, HttpRedirectsGet300Headers headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }

    /**
     * @return the deserialized response body.
     */
    @Override
    public List<String> getValue() {
        return super.getValue();
    }
}
