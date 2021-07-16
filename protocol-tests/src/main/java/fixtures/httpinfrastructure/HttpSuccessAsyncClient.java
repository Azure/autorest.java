package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.httpinfrastructure.implementation.HttpSuccessImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class, isAsync = true)
public final class HttpSuccessAsyncClient {
    private final HttpSuccessImpl serviceClient;

    /**
     * Initializes an instance of HttpSuccess client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpSuccessAsyncClient(HttpSuccessImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /** Return 200 status code if successful. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head200WithResponseAsync(requestOptions);
    }

    /** Return 200 status code if successful. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200(RequestOptions requestOptions) {
        return this.serviceClient.head200Async(requestOptions);
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> get200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200WithResponseAsync(requestOptions);
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> get200(RequestOptions requestOptions) {
        return this.serviceClient.get200Async(requestOptions);
    }

    /** Put boolean value true returning 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put200WithResponseAsync(requestOptions);
    }

    /** Put boolean value true returning 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put200(RequestOptions requestOptions) {
        return this.serviceClient.put200Async(requestOptions);
    }

    /** Patch true Boolean value in request returning 200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch200WithResponseAsync(requestOptions);
    }

    /** Patch true Boolean value in request returning 200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch200(RequestOptions requestOptions) {
        return this.serviceClient.patch200Async(requestOptions);
    }

    /** Post bollean value true in request that returns a 200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post200WithResponseAsync(requestOptions);
    }

    /** Post bollean value true in request that returns a 200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post200(RequestOptions requestOptions) {
        return this.serviceClient.post200Async(requestOptions);
    }

    /** Delete simple boolean value true returns 200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete200WithResponseAsync(requestOptions);
    }

    /** Delete simple boolean value true returns 200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete200(RequestOptions requestOptions) {
        return this.serviceClient.delete200Async(requestOptions);
    }

    /** Put true Boolean value in request returns 201. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put201WithResponseAsync(requestOptions);
    }

    /** Put true Boolean value in request returns 201. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put201(RequestOptions requestOptions) {
        return this.serviceClient.put201Async(requestOptions);
    }

    /** Post true Boolean value in request returns 201 (Created). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post201WithResponseAsync(requestOptions);
    }

    /** Post true Boolean value in request returns 201 (Created). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post201(RequestOptions requestOptions) {
        return this.serviceClient.post201Async(requestOptions);
    }

    /** Put true Boolean value in request returns 202 (Accepted). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put202WithResponseAsync(requestOptions);
    }

    /** Put true Boolean value in request returns 202 (Accepted). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put202(RequestOptions requestOptions) {
        return this.serviceClient.put202Async(requestOptions);
    }

    /** Patch true Boolean value in request returns 202. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch202WithResponseAsync(requestOptions);
    }

    /** Patch true Boolean value in request returns 202. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch202(RequestOptions requestOptions) {
        return this.serviceClient.patch202Async(requestOptions);
    }

    /** Post true Boolean value in request returns 202 (Accepted). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post202WithResponseAsync(requestOptions);
    }

    /** Post true Boolean value in request returns 202 (Accepted). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post202(RequestOptions requestOptions) {
        return this.serviceClient.post202Async(requestOptions);
    }

    /** Delete true Boolean value in request returns 202 (accepted). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete202WithResponseAsync(requestOptions);
    }

    /** Delete true Boolean value in request returns 202 (accepted). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete202(RequestOptions requestOptions) {
        return this.serviceClient.delete202Async(requestOptions);
    }

    /** Return 204 status code if successful. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head204WithResponseAsync(requestOptions);
    }

    /** Return 204 status code if successful. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204(RequestOptions requestOptions) {
        return this.serviceClient.head204Async(requestOptions);
    }

    /** Put true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put204WithResponseAsync(requestOptions);
    }

    /** Put true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put204(RequestOptions requestOptions) {
        return this.serviceClient.put204Async(requestOptions);
    }

    /** Patch true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch204WithResponseAsync(requestOptions);
    }

    /** Patch true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch204(RequestOptions requestOptions) {
        return this.serviceClient.patch204Async(requestOptions);
    }

    /** Post true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post204WithResponseAsync(requestOptions);
    }

    /** Post true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post204(RequestOptions requestOptions) {
        return this.serviceClient.post204Async(requestOptions);
    }

    /** Delete true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete204WithResponseAsync(requestOptions);
    }

    /** Delete true Boolean value in request returns 204 (no content). */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete204(RequestOptions requestOptions) {
        return this.serviceClient.delete204Async(requestOptions);
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> head404WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head404WithResponseAsync(requestOptions);
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head404(RequestOptions requestOptions) {
        return this.serviceClient.head404Async(requestOptions);
    }
}
