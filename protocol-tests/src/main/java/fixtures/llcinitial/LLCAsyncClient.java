package fixtures.llcinitial;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.llcinitial.implementation.ParamsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous LLCClient type. */
@ServiceClient(builder = LLCClientBuilder.class, isAsync = true)
public final class LLCAsyncClient {
    private final ParamsImpl serviceClient;

    /**
     * Initializes an instance of Params client.
     *
     * @param serviceClient the service client implementation.
     */
    LLCAsyncClient(ParamsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter1</td><td>String</td><td>Yes</td><td>I am a required parameter</td></tr>
     *     <tr><td>parameter2</td><td>String</td><td>Yes</td><td>I am a required parameter</td></tr>
     *     <tr><td>parameter3</td><td>String</td><td>Yes</td><td>I am a required parameter and I'm last in Swagger</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getRequiredWithResponseAsync(requestOptions);
    }
}
