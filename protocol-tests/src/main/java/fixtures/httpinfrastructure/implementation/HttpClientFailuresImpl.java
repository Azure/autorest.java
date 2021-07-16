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

/** An instance of this class provides access to all the operations defined in HttpClientFailures. */
public final class HttpClientFailuresImpl {
    /** The proxy service used to perform REST calls. */
    private final HttpClientFailuresService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of HttpClientFailuresImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpClientFailuresImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(
                        HttpClientFailuresService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpClientFailures to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpClientFailuresService {
        @Head("/http/failure/client/400")
        Mono<Response<Void>> head400(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/failure/client/400")
        Mono<Response<Void>> get400(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/failure/client/400")
        Mono<Response<Void>> put400(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/failure/client/400")
        Mono<Response<Void>> patch400(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/failure/client/400")
        Mono<Response<Void>> post400(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/failure/client/400")
        Mono<Response<Void>> delete400(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/failure/client/401")
        Mono<Response<Void>> head401(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/failure/client/402")
        Mono<Response<Void>> get402(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/failure/client/403")
        Mono<Response<Void>> get403(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/failure/client/404")
        Mono<Response<Void>> put404(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/failure/client/405")
        Mono<Response<Void>> patch405(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/failure/client/406")
        Mono<Response<Void>> post406(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/failure/client/407")
        Mono<Response<Void>> delete407(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/failure/client/409")
        Mono<Response<Void>> put409(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/failure/client/410")
        Mono<Response<Void>> head410(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/failure/client/411")
        Mono<Response<Void>> get411(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/failure/client/412")
        Mono<Response<Void>> get412(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/failure/client/413")
        Mono<Response<Void>> put413(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/failure/client/414")
        Mono<Response<Void>> patch414(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/failure/client/415")
        Mono<Response<Void>> post415(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/failure/client/416")
        Mono<Response<Void>> get416(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/failure/client/417")
        Mono<Response<Void>> delete417(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/failure/client/429")
        Mono<Response<Void>> head429(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head400WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head400(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head400WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head400(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head400Async(RequestOptions requestOptions) {
        return head400WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head400Async(RequestOptions requestOptions, Context context) {
        return head400WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head400(RequestOptions requestOptions) {
        head400Async(requestOptions).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head400WithResponse(RequestOptions requestOptions, Context context) {
        return head400WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get400WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get400(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get400WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get400(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get400Async(RequestOptions requestOptions) {
        return get400WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get400Async(RequestOptions requestOptions, Context context) {
        return get400WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get400(RequestOptions requestOptions) {
        get400Async(requestOptions).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get400WithResponse(RequestOptions requestOptions, Context context) {
        return get400WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put400WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put400(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put400WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put400(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put400Async(RequestOptions requestOptions) {
        return put400WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put400Async(RequestOptions requestOptions, Context context) {
        return put400WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put400(RequestOptions requestOptions) {
        put400Async(requestOptions).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put400WithResponse(RequestOptions requestOptions, Context context) {
        return put400WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch400WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch400(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch400WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch400(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch400Async(RequestOptions requestOptions) {
        return patch400WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch400Async(RequestOptions requestOptions, Context context) {
        return patch400WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch400(RequestOptions requestOptions) {
        patch400Async(requestOptions).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch400WithResponse(RequestOptions requestOptions, Context context) {
        return patch400WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post400WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post400(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post400WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post400(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post400Async(RequestOptions requestOptions) {
        return post400WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post400Async(RequestOptions requestOptions, Context context) {
        return post400WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post400(RequestOptions requestOptions) {
        post400Async(requestOptions).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post400WithResponse(RequestOptions requestOptions, Context context) {
        return post400WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete400WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete400(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete400WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete400(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete400Async(RequestOptions requestOptions) {
        return delete400WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete400Async(RequestOptions requestOptions, Context context) {
        return delete400WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete400(RequestOptions requestOptions) {
        delete400Async(requestOptions).block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete400WithResponse(RequestOptions requestOptions, Context context) {
        return delete400WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head401WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head401(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head401WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head401(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head401Async(RequestOptions requestOptions) {
        return head401WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head401Async(RequestOptions requestOptions, Context context) {
        return head401WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head401(RequestOptions requestOptions) {
        head401Async(requestOptions).block();
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head401WithResponse(RequestOptions requestOptions, Context context) {
        return head401WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get402WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get402(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get402WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get402(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get402Async(RequestOptions requestOptions) {
        return get402WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get402Async(RequestOptions requestOptions, Context context) {
        return get402WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get402(RequestOptions requestOptions) {
        get402Async(requestOptions).block();
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get402WithResponse(RequestOptions requestOptions, Context context) {
        return get402WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get403WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get403(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get403WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get403(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get403Async(RequestOptions requestOptions) {
        return get403WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get403Async(RequestOptions requestOptions, Context context) {
        return get403WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get403(RequestOptions requestOptions) {
        get403Async(requestOptions).block();
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get403WithResponse(RequestOptions requestOptions, Context context) {
        return get403WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put404WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put404(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put404WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put404(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put404Async(RequestOptions requestOptions) {
        return put404WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put404Async(RequestOptions requestOptions, Context context) {
        return put404WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put404(RequestOptions requestOptions) {
        put404Async(requestOptions).block();
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put404WithResponse(RequestOptions requestOptions, Context context) {
        return put404WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch405WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch405(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch405WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch405(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch405Async(RequestOptions requestOptions) {
        return patch405WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch405Async(RequestOptions requestOptions, Context context) {
        return patch405WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch405(RequestOptions requestOptions) {
        patch405Async(requestOptions).block();
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch405WithResponse(RequestOptions requestOptions, Context context) {
        return patch405WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post406WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post406(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post406WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post406(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post406Async(RequestOptions requestOptions) {
        return post406WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post406Async(RequestOptions requestOptions, Context context) {
        return post406WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post406(RequestOptions requestOptions) {
        post406Async(requestOptions).block();
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post406WithResponse(RequestOptions requestOptions, Context context) {
        return post406WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete407WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete407(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete407WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete407(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete407Async(RequestOptions requestOptions) {
        return delete407WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete407Async(RequestOptions requestOptions, Context context) {
        return delete407WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete407(RequestOptions requestOptions) {
        delete407Async(requestOptions).block();
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete407WithResponse(RequestOptions requestOptions, Context context) {
        return delete407WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put409WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put409(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put409WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put409(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put409Async(RequestOptions requestOptions) {
        return put409WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put409Async(RequestOptions requestOptions, Context context) {
        return put409WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put409(RequestOptions requestOptions) {
        put409Async(requestOptions).block();
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put409WithResponse(RequestOptions requestOptions, Context context) {
        return put409WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head410WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head410(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head410WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head410(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head410Async(RequestOptions requestOptions) {
        return head410WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head410Async(RequestOptions requestOptions, Context context) {
        return head410WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head410(RequestOptions requestOptions) {
        head410Async(requestOptions).block();
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head410WithResponse(RequestOptions requestOptions, Context context) {
        return head410WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get411WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get411(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get411WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get411(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get411Async(RequestOptions requestOptions) {
        return get411WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get411Async(RequestOptions requestOptions, Context context) {
        return get411WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get411(RequestOptions requestOptions) {
        get411Async(requestOptions).block();
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get411WithResponse(RequestOptions requestOptions, Context context) {
        return get411WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get412WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get412(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get412WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get412(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get412Async(RequestOptions requestOptions) {
        return get412WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get412Async(RequestOptions requestOptions, Context context) {
        return get412WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get412(RequestOptions requestOptions) {
        get412Async(requestOptions).block();
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get412WithResponse(RequestOptions requestOptions, Context context) {
        return get412WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put413WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put413(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put413WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put413(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put413Async(RequestOptions requestOptions) {
        return put413WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put413Async(RequestOptions requestOptions, Context context) {
        return put413WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put413(RequestOptions requestOptions) {
        put413Async(requestOptions).block();
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put413WithResponse(RequestOptions requestOptions, Context context) {
        return put413WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch414WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch414(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch414WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch414(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch414Async(RequestOptions requestOptions) {
        return patch414WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch414Async(RequestOptions requestOptions, Context context) {
        return patch414WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch414(RequestOptions requestOptions) {
        patch414Async(requestOptions).block();
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch414WithResponse(RequestOptions requestOptions, Context context) {
        return patch414WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post415WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post415(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post415WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post415(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post415Async(RequestOptions requestOptions) {
        return post415WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post415Async(RequestOptions requestOptions, Context context) {
        return post415WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post415(RequestOptions requestOptions) {
        post415Async(requestOptions).block();
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post415WithResponse(RequestOptions requestOptions, Context context) {
        return post415WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get416WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get416(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get416WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get416(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get416Async(RequestOptions requestOptions) {
        return get416WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get416Async(RequestOptions requestOptions, Context context) {
        return get416WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get416(RequestOptions requestOptions) {
        get416Async(requestOptions).block();
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get416WithResponse(RequestOptions requestOptions, Context context) {
        return get416WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete417WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete417(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete417WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete417(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete417Async(RequestOptions requestOptions) {
        return delete417WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete417Async(RequestOptions requestOptions, Context context) {
        return delete417WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete417(RequestOptions requestOptions) {
        delete417Async(requestOptions).block();
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete417WithResponse(RequestOptions requestOptions, Context context) {
        return delete417WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head429WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head429(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head429WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head429(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head429Async(RequestOptions requestOptions) {
        return head429WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head429Async(RequestOptions requestOptions, Context context) {
        return head429WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head429(RequestOptions requestOptions) {
        head429Async(requestOptions).block();
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head429WithResponse(RequestOptions requestOptions, Context context) {
        return head429WithResponseAsync(requestOptions, context).block();
    }
}
