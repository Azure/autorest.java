package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.httpinfrastructure.implementation.HttpFailuresImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class, isAsync = true)
public final class HttpFailureAsyncClient {
    private final HttpFailuresImpl serviceClient;

    /**
     * Initializes an instance of HttpFailures client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpFailureAsyncClient(HttpFailuresImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get empty error form server.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getEmptyErrorWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyErrorWithResponseAsync(requestOptions);
    }

    /**
     * Get empty error form server.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getEmptyError(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyErrorAsync(requestOptions);
    }

    /**
     * Get empty error form server.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNoModelErrorWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNoModelErrorWithResponseAsync(requestOptions);
    }

    /**
     * Get empty error form server.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNoModelError(RequestOptions requestOptions) {
        return this.serviceClient.getNoModelErrorAsync(requestOptions);
    }

    /**
     * Get empty response from server.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty response from server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNoModelEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNoModelEmptyWithResponseAsync(requestOptions);
    }

    /**
     * Get empty response from server.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty response from server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNoModelEmpty(RequestOptions requestOptions) {
        return this.serviceClient.getNoModelEmptyAsync(requestOptions);
    }
}
