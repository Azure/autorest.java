package fixtures.custombaseuri;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.custombaseuri.implementation.PathsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestParameterizedHostTestClient type. */
@ServiceClient(builder = AutoRestParameterizedHostTestClientBuilder.class, isAsync = true)
public final class AutoRestParameterizedHostTestAsyncClient {
    private final PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     *
     * @param serviceClient the service client implementation.
     */
    AutoRestParameterizedHostTestAsyncClient(PathsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponse(String accountName, RequestOptions requestOptions) {
        return this.serviceClient.getEmptyWithResponseAsync(accountName, requestOptions);
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmpty(String accountName, RequestOptions requestOptions) {
        return this.serviceClient.getEmptyAsync(accountName, requestOptions);
    }
}
