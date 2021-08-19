package fixtures.mediatypes;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.mediatypes.implementation.MediaTypesClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous MediaTypesClient type. */
@ServiceClient(builder = MediaTypesClientBuilder.class, isAsync = true)
public final class MediaTypesAsyncClient {
    private final MediaTypesClientImpl serviceClient;

    /**
     * Initializes an instance of MediaTypesClient client.
     *
     * @param serviceClient the service client implementation.
     */
    MediaTypesAsyncClient(MediaTypesClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Analyze body, that could be different media types.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> analyzeBodyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.analyzeBodyWithResponseAsync(requestOptions);
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> analyzeBodyNoAcceptHeaderWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.analyzeBodyNoAcceptHeaderWithResponseAsync(requestOptions);
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> contentTypeWithEncodingWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.contentTypeWithEncodingWithResponseAsync(requestOptions);
    }
}
