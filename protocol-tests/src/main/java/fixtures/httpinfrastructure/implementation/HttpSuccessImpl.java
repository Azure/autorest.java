package fixtures.httpinfrastructure.implementation;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HttpSuccess. */
public final class HttpSuccessImpl {
    /** The proxy service used to perform REST calls. */
    private final HttpSuccessService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of HttpSuccessImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpSuccessImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(HttpSuccessService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpSuccess to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpSuccessService {
        @Head("/http/success/200")
        Mono<Response<Void>> head200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/success/200")
        Mono<Response<Boolean>> get200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/success/200")
        Mono<Response<Void>> put200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/success/200")
        Mono<Response<Void>> patch200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/success/200")
        Mono<Response<Void>> post200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/http/success/200")
        Mono<Response<Void>> delete200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/success/201")
        Mono<Response<Void>> put201(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/success/201")
        Mono<Response<Void>> post201(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/success/202")
        Mono<Response<Void>> put202(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/success/202")
        Mono<Response<Void>> patch202(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/success/202")
        Mono<Response<Void>> post202(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/http/success/202")
        Mono<Response<Void>> delete202(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/204")
        Mono<Response<Void>> head204(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/success/204")
        Mono<Response<Void>> put204(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/success/204")
        Mono<Response<Void>> patch204(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/success/204")
        Mono<Response<Void>> post204(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/http/success/204")
        Mono<Response<Void>> delete204(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/404")
        Mono<Response<Boolean>> head404(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Return 200 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 200 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 200 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head200WithResponse(RequestOptions requestOptions, Context context) {
        return head200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> get200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> get200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> get200WithResponse(RequestOptions requestOptions, Context context) {
        return get200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put200WithResponse(RequestOptions requestOptions, Context context) {
        return put200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch200WithResponse(RequestOptions requestOptions, Context context) {
        return patch200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post200WithResponse(RequestOptions requestOptions, Context context) {
        return post200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.delete200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete200WithResponse(RequestOptions requestOptions, Context context) {
        return delete200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put201(this.client.getHost(), requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put201(this.client.getHost(), requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put201WithResponse(RequestOptions requestOptions, Context context) {
        return put201WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post201(this.client.getHost(), requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post201(this.client.getHost(), requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post201WithResponse(RequestOptions requestOptions, Context context) {
        return post201WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put202(this.client.getHost(), requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put202(this.client.getHost(), requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put202WithResponse(RequestOptions requestOptions, Context context) {
        return put202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch202(this.client.getHost(), requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch202(this.client.getHost(), requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch202WithResponse(RequestOptions requestOptions, Context context) {
        return patch202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post202(this.client.getHost(), requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post202(this.client.getHost(), requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post202WithResponse(RequestOptions requestOptions, Context context) {
        return post202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.delete202(this.client.getHost(), requestOptions, context));
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete202(this.client.getHost(), requestOptions, context);
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete202WithResponse(RequestOptions requestOptions, Context context) {
        return delete202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 204 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 204 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 204 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head204WithResponse(RequestOptions requestOptions, Context context) {
        return head204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put204WithResponse(RequestOptions requestOptions, Context context) {
        return put204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch204WithResponse(RequestOptions requestOptions, Context context) {
        return patch204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post204WithResponse(RequestOptions requestOptions, Context context) {
        return post204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.delete204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete204WithResponse(RequestOptions requestOptions, Context context) {
        return delete204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> head404WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head404(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> head404WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head404(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> head404WithResponse(RequestOptions requestOptions, Context context) {
        return head404WithResponseAsync(requestOptions, context).block();
    }
}
