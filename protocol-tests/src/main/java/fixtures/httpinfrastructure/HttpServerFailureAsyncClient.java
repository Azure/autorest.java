package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.httpinfrastructure.implementation.HttpServerFailuresImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class, isAsync = true)
public final class HttpServerFailureAsyncClient {
    private final HttpServerFailuresImpl serviceClient;

    /**
     * Initializes an instance of HttpServerFailures client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpServerFailureAsyncClient(HttpServerFailuresImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head501WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head501WithResponseAsync(requestOptions);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head501(RequestOptions requestOptions) {
        return this.serviceClient.head501Async(requestOptions);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get501WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get501WithResponseAsync(requestOptions);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get501(RequestOptions requestOptions) {
        return this.serviceClient.get501Async(requestOptions);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post505WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post505WithResponseAsync(requestOptions);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post505(RequestOptions requestOptions) {
        return this.serviceClient.post505Async(requestOptions);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete505WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete505WithResponseAsync(requestOptions);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete505(RequestOptions requestOptions) {
        return this.serviceClient.delete505Async(requestOptions);
    }
}
