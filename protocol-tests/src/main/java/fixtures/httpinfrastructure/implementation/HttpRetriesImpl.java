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

/** An instance of this class provides access to all the operations defined in HttpRetries. */
public final class HttpRetriesImpl {
    /** The proxy service used to perform REST calls. */
    private final HttpRetriesService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of HttpRetriesImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpRetriesImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(HttpRetriesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpRetries to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpRetriesService {
        @Head("/http/retry/408")
        Mono<Response<Void>> head408(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/retry/500")
        Mono<Response<Void>> put500(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/retry/500")
        Mono<Response<Void>> patch500(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/retry/502")
        Mono<Response<Void>> get502(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/retry/503")
        Mono<Response<Void>> post503(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/http/retry/503")
        Mono<Response<Void>> delete503(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/retry/504")
        Mono<Response<Void>> put504(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/retry/504")
        Mono<Response<Void>> patch504(@HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head408WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head408(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head408WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head408(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head408Async(RequestOptions requestOptions) {
        return head408WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head408Async(RequestOptions requestOptions, Context context) {
        return head408WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head408(RequestOptions requestOptions) {
        head408Async(requestOptions).block();
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head408WithResponse(RequestOptions requestOptions, Context context) {
        return head408WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put500WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put500(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put500WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put500(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put500Async(RequestOptions requestOptions) {
        return put500WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put500Async(RequestOptions requestOptions, Context context) {
        return put500WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put500(RequestOptions requestOptions) {
        put500Async(requestOptions).block();
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put500WithResponse(RequestOptions requestOptions, Context context) {
        return put500WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch500WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch500(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch500WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch500(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch500Async(RequestOptions requestOptions) {
        return patch500WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch500Async(RequestOptions requestOptions, Context context) {
        return patch500WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch500(RequestOptions requestOptions) {
        patch500Async(requestOptions).block();
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch500WithResponse(RequestOptions requestOptions, Context context) {
        return patch500WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get502WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get502(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get502WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get502(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get502Async(RequestOptions requestOptions) {
        return get502WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get502Async(RequestOptions requestOptions, Context context) {
        return get502WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get502(RequestOptions requestOptions) {
        get502Async(requestOptions).block();
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get502WithResponse(RequestOptions requestOptions, Context context) {
        return get502WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post503WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post503(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post503WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post503(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post503Async(RequestOptions requestOptions) {
        return post503WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post503Async(RequestOptions requestOptions, Context context) {
        return post503WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post503(RequestOptions requestOptions) {
        post503Async(requestOptions).block();
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post503WithResponse(RequestOptions requestOptions, Context context) {
        return post503WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete503WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.delete503(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete503WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete503(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete503Async(RequestOptions requestOptions) {
        return delete503WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete503Async(RequestOptions requestOptions, Context context) {
        return delete503WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete503(RequestOptions requestOptions) {
        delete503Async(requestOptions).block();
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete503WithResponse(RequestOptions requestOptions, Context context) {
        return delete503WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put504WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put504(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put504WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put504(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put504Async(RequestOptions requestOptions) {
        return put504WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put504Async(RequestOptions requestOptions, Context context) {
        return put504WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put504(RequestOptions requestOptions) {
        put504Async(requestOptions).block();
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put504WithResponse(RequestOptions requestOptions, Context context) {
        return put504WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch504WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch504(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch504WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch504(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch504Async(RequestOptions requestOptions) {
        return patch504WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch504Async(RequestOptions requestOptions, Context context) {
        return patch504WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch504(RequestOptions requestOptions) {
        patch504Async(requestOptions).block();
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch504WithResponse(RequestOptions requestOptions, Context context) {
        return patch504WithResponseAsync(requestOptions, context).block();
    }
}
