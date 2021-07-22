package fixtures.httpinfrastructure.implementation;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HttpServerFailures. */
public final class HttpServerFailuresImpl {
    /** The proxy service used to perform REST calls. */
    private final HttpServerFailuresService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of HttpServerFailuresImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpServerFailuresImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(
                        HttpServerFailuresService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpServerFailures to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpServerFailuresService {
        @Head("/http/failure/server/501")
        Mono<Response<Void>> head501(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/failure/server/501")
        Mono<Response<Void>> get501(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/failure/server/505")
        Mono<Response<Void>> post505(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/http/failure/server/505")
        Mono<Response<Void>> delete505(@HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head501WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head501(this.client.getHost(), requestOptions, context));
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head501WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head501(this.client.getHost(), requestOptions, context);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head501Async(RequestOptions requestOptions) {
        return head501WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head501Async(RequestOptions requestOptions, Context context) {
        return head501WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head501(RequestOptions requestOptions) {
        head501Async(requestOptions).block();
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head501WithResponse(RequestOptions requestOptions, Context context) {
        return head501WithResponseAsync(requestOptions, context).block();
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get501WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get501(this.client.getHost(), requestOptions, context));
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get501WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get501(this.client.getHost(), requestOptions, context);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get501Async(RequestOptions requestOptions) {
        return get501WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get501Async(RequestOptions requestOptions, Context context) {
        return get501WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get501(RequestOptions requestOptions) {
        get501Async(requestOptions).block();
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get501WithResponse(RequestOptions requestOptions, Context context) {
        return get501WithResponseAsync(requestOptions, context).block();
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post505WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post505(this.client.getHost(), requestOptions, context));
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post505WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post505(this.client.getHost(), requestOptions, context);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post505Async(RequestOptions requestOptions) {
        return post505WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post505Async(RequestOptions requestOptions, Context context) {
        return post505WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post505(RequestOptions requestOptions) {
        post505Async(requestOptions).block();
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post505WithResponse(RequestOptions requestOptions, Context context) {
        return post505WithResponseAsync(requestOptions, context).block();
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete505WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.delete505(this.client.getHost(), requestOptions, context));
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete505WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete505(this.client.getHost(), requestOptions, context);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete505Async(RequestOptions requestOptions) {
        return delete505WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete505Async(RequestOptions requestOptions, Context context) {
        return delete505WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete505(RequestOptions requestOptions) {
        delete505Async(requestOptions).block();
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete505WithResponse(RequestOptions requestOptions, Context context) {
        return delete505WithResponseAsync(requestOptions, context).block();
    }
}
