package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.httpinfrastructure.implementation.HttpClientFailuresImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class, isAsync = true)
public final class HttpClientFailureAsyncClient {
    private final HttpClientFailuresImpl serviceClient;

    /**
     * Initializes an instance of HttpClientFailures client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpClientFailureAsyncClient(HttpClientFailuresImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head400WithResponseAsync(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get400WithResponseAsync(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put400WithResponseAsync(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch400WithResponseAsync(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post400WithResponseAsync(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete400WithResponseAsync(requestOptions);
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head401WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head401WithResponseAsync(requestOptions);
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get402WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get402WithResponseAsync(requestOptions);
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get403WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get403WithResponseAsync(requestOptions);
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put404WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put404WithResponseAsync(requestOptions);
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch405WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch405WithResponseAsync(requestOptions);
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post406WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post406WithResponseAsync(requestOptions);
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete407WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete407WithResponseAsync(requestOptions);
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put409WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put409WithResponseAsync(requestOptions);
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head410WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head410WithResponseAsync(requestOptions);
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get411WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get411WithResponseAsync(requestOptions);
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get412WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get412WithResponseAsync(requestOptions);
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put413WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put413WithResponseAsync(requestOptions);
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch414WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch414WithResponseAsync(requestOptions);
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post415WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post415WithResponseAsync(requestOptions);
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get416WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get416WithResponseAsync(requestOptions);
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete417WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete417WithResponseAsync(requestOptions);
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head429WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head429WithResponseAsync(requestOptions);
    }
}
