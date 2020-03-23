package fixtures.xmlservice.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the getHeaders operation.
 */
public final class XmlsGetHeadersResponse extends ResponseBase<XmlsGetHeadersHeaders, Void> {
    /**
     * Creates an instance of XmlsGetHeadersResponse.
     * 
     * @param request the request which resulted in this XmlsGetHeadersResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public XmlsGetHeadersResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, XmlsGetHeadersHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
