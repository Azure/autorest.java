package fixtures.httpinfrastructure.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HttpFailures. */
public final class HttpFailuresImpl {
    /** The proxy service used to perform REST calls. */
    private final HttpFailuresService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of HttpFailuresImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpFailuresImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(HttpFailuresService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpFailures to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpFailuresService {
        @Get("/http/failure/emptybody/error")
        Mono<Response<Boolean>> getEmptyError(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/failure/nomodel/error")
        Mono<Response<Boolean>> getNoModelError(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/failure/nomodel/empty")
        Mono<Response<Boolean>> getNoModelEmpty(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Get empty error form server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getEmptyErrorWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getEmptyError(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get empty error form server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getEmptyErrorWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getEmptyError(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get empty error form server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getEmptyErrorWithResponse(RequestOptions requestOptions, Context context) {
        return getEmptyErrorWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get empty error form server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNoModelErrorWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getNoModelError(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get empty error form server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNoModelErrorWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNoModelError(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get empty error form server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty error form server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getNoModelErrorWithResponse(RequestOptions requestOptions, Context context) {
        return getNoModelErrorWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get empty response from server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty response from server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNoModelEmptyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getNoModelEmpty(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get empty response from server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty response from server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNoModelEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNoModelEmpty(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get empty response from server.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return empty response from server.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getNoModelEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return getNoModelEmptyWithResponseAsync(requestOptions, context).block();
    }
}
