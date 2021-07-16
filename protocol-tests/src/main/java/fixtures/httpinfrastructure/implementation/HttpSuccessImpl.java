package fixtures.httpinfrastructure.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.HeaderParam;
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
import com.azure.core.util.BinaryData;
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
        Mono<Response<Void>> head200(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/success/200")
        Mono<Response<Boolean>> get200(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/success/200")
        Mono<Response<Void>> put200(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/success/200")
        Mono<Response<Void>> patch200(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/success/200")
        Mono<Response<Void>> post200(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/success/200")
        Mono<Response<Void>> delete200(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/success/201")
        Mono<Response<Void>> put201(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/success/201")
        Mono<Response<Void>> post201(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/success/202")
        Mono<Response<Void>> put202(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/success/202")
        Mono<Response<Void>> patch202(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/success/202")
        Mono<Response<Void>> post202(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/success/202")
        Mono<Response<Void>> delete202(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/success/204")
        Mono<Response<Void>> head204(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/success/204")
        Mono<Response<Void>> put204(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/success/204")
        Mono<Response<Void>> patch204(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/success/204")
        Mono<Response<Void>> post204(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/success/204")
        Mono<Response<Void>> delete204(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/success/404")
        Mono<Response<Boolean>> head404(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Return 200 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head200(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 200 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head200(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 200 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200Async(RequestOptions requestOptions) {
        return head200WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 200 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200Async(RequestOptions requestOptions, Context context) {
        return head200WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 200 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head200(RequestOptions requestOptions) {
        head200Async(requestOptions).block();
    }

    /**
     * Return 200 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> get200WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get200(this.client.getHost(), accept, requestOptions, context));
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> get200WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200(this.client.getHost(), accept, requestOptions, context);
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> get200Async(RequestOptions requestOptions) {
        return get200WithResponseAsync(requestOptions)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> get200Async(RequestOptions requestOptions, Context context) {
        return get200WithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean get200(RequestOptions requestOptions) {
        Boolean value = get200Async(requestOptions).block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> get200WithResponse(RequestOptions requestOptions, Context context) {
        return get200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put200(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put200(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put200Async(RequestOptions requestOptions) {
        return put200WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put200Async(RequestOptions requestOptions, Context context) {
        return put200WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put200(RequestOptions requestOptions) {
        put200Async(requestOptions).block();
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put200WithResponse(RequestOptions requestOptions, Context context) {
        return put200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch200(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch200(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch200Async(RequestOptions requestOptions) {
        return patch200WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch200Async(RequestOptions requestOptions, Context context) {
        return patch200WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch200(RequestOptions requestOptions) {
        patch200Async(requestOptions).block();
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch200WithResponse(RequestOptions requestOptions, Context context) {
        return patch200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post200(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post200(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post200Async(RequestOptions requestOptions) {
        return post200WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post200Async(RequestOptions requestOptions, Context context) {
        return post200WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post200(RequestOptions requestOptions) {
        post200Async(requestOptions).block();
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post200WithResponse(RequestOptions requestOptions, Context context) {
        return post200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete200(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete200(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete200Async(RequestOptions requestOptions) {
        return delete200WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete200Async(RequestOptions requestOptions, Context context) {
        return delete200WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete200(RequestOptions requestOptions) {
        delete200Async(requestOptions).block();
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete200WithResponse(RequestOptions requestOptions, Context context) {
        return delete200WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put201(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put201(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put201Async(RequestOptions requestOptions) {
        return put201WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put201Async(RequestOptions requestOptions, Context context) {
        return put201WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put201(RequestOptions requestOptions) {
        put201Async(requestOptions).block();
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put201WithResponse(RequestOptions requestOptions, Context context) {
        return put201WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post201(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post201(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post201Async(RequestOptions requestOptions) {
        return post201WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post201Async(RequestOptions requestOptions, Context context) {
        return post201WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post201(RequestOptions requestOptions) {
        post201Async(requestOptions).block();
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post201WithResponse(RequestOptions requestOptions, Context context) {
        return post201WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put202(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put202(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put202Async(RequestOptions requestOptions) {
        return put202WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put202Async(RequestOptions requestOptions, Context context) {
        return put202WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put202(RequestOptions requestOptions) {
        put202Async(requestOptions).block();
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put202WithResponse(RequestOptions requestOptions, Context context) {
        return put202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch202(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch202(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch202Async(RequestOptions requestOptions) {
        return patch202WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch202Async(RequestOptions requestOptions, Context context) {
        return patch202WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch202(RequestOptions requestOptions) {
        patch202Async(requestOptions).block();
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch202WithResponse(RequestOptions requestOptions, Context context) {
        return patch202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post202(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post202(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post202Async(RequestOptions requestOptions) {
        return post202WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post202Async(RequestOptions requestOptions, Context context) {
        return post202WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202(RequestOptions requestOptions) {
        post202Async(requestOptions).block();
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post202WithResponse(RequestOptions requestOptions, Context context) {
        return post202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete202(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete202(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete202Async(RequestOptions requestOptions) {
        return delete202WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete202Async(RequestOptions requestOptions, Context context) {
        return delete202WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202(RequestOptions requestOptions) {
        delete202Async(requestOptions).block();
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete202WithResponse(RequestOptions requestOptions, Context context) {
        return delete202WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 204 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head204(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 204 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head204(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 204 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204Async(RequestOptions requestOptions) {
        return head204WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 204 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204Async(RequestOptions requestOptions, Context context) {
        return head204WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 204 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head204(RequestOptions requestOptions) {
        head204Async(requestOptions).block();
    }

    /**
     * Return 204 status code if successful.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head204WithResponse(RequestOptions requestOptions, Context context) {
        return head204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put204(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put204(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put204Async(RequestOptions requestOptions) {
        return put204WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put204Async(RequestOptions requestOptions, Context context) {
        return put204WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put204(RequestOptions requestOptions) {
        put204Async(requestOptions).block();
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put204WithResponse(RequestOptions requestOptions, Context context) {
        return put204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch204(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch204(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch204Async(RequestOptions requestOptions) {
        return patch204WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch204Async(RequestOptions requestOptions, Context context) {
        return patch204WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch204(RequestOptions requestOptions) {
        patch204Async(requestOptions).block();
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch204WithResponse(RequestOptions requestOptions, Context context) {
        return patch204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post204(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post204(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post204Async(RequestOptions requestOptions) {
        return post204WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post204Async(RequestOptions requestOptions, Context context) {
        return post204WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post204(RequestOptions requestOptions) {
        post204Async(requestOptions).block();
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post204WithResponse(RequestOptions requestOptions, Context context) {
        return post204WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete204(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete204(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete204Async(RequestOptions requestOptions) {
        return delete204WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete204Async(RequestOptions requestOptions, Context context) {
        return delete204WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete204(RequestOptions requestOptions) {
        delete204Async(requestOptions).block();
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> head404WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head404(this.client.getHost(), accept, requestOptions, context));
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> head404WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head404(this.client.getHost(), accept, requestOptions, context);
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head404Async(RequestOptions requestOptions) {
        return head404WithResponseAsync(requestOptions)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head404Async(RequestOptions requestOptions, Context context) {
        return head404WithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean head404(RequestOptions requestOptions) {
        Boolean value = head404Async(requestOptions).block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
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
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> head404WithResponse(RequestOptions requestOptions, Context context) {
        return head404WithResponseAsync(requestOptions, context).block();
    }
}
