package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.httpinfrastructure.implementation.HttpRetriesImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class, isAsync = true)
public final class HttpRetryAsyncClient {
    private final HttpRetriesImpl serviceClient;

    /**
     * Initializes an instance of HttpRetries client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpRetryAsyncClient(HttpRetriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /** Return 408 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head408WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head408WithResponseAsync(requestOptions);
    }

    /** Return 408 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head408(RequestOptions requestOptions) {
        return this.serviceClient.head408Async(requestOptions);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put500WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put500WithResponseAsync(requestOptions);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put500(RequestOptions requestOptions) {
        return this.serviceClient.put500Async(requestOptions);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch500WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch500WithResponseAsync(requestOptions);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch500(RequestOptions requestOptions) {
        return this.serviceClient.patch500Async(requestOptions);
    }

    /** Return 502 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get502WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get502WithResponseAsync(requestOptions);
    }

    /** Return 502 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get502(RequestOptions requestOptions) {
        return this.serviceClient.get502Async(requestOptions);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post503WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post503WithResponseAsync(requestOptions);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post503(RequestOptions requestOptions) {
        return this.serviceClient.post503Async(requestOptions);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete503WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete503WithResponseAsync(requestOptions);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete503(RequestOptions requestOptions) {
        return this.serviceClient.delete503Async(requestOptions);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put504WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put504WithResponseAsync(requestOptions);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put504(RequestOptions requestOptions) {
        return this.serviceClient.put504Async(requestOptions);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch504WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch504WithResponseAsync(requestOptions);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch504(RequestOptions requestOptions) {
        return this.serviceClient.patch504Async(requestOptions);
    }
}
