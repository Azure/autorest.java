package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.FlattencomplexesImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class, isAsync = true)
public final class FlattencomplexAsyncClient {
    private final FlattencomplexesImpl serviceClient;

    /**
     * Initializes an instance of Flattencomplexes client.
     *
     * @param serviceClient the service client implementation.
     */
    FlattencomplexAsyncClient(FlattencomplexesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * <strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     propB1: String
     *     helper: {
     *         propBH1: String
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponseAsync(requestOptions);
    }
}
