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
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HttpRedirects. */
public final class HttpRedirectsImpl {
    /** The proxy service used to perform REST calls. */
    private final HttpRedirectsService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of HttpRedirectsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpRedirectsImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(HttpRedirectsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpRedirects to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpRedirectsService {
        @Head("/http/redirect/300")
        Mono<Response<Void>> head300(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/redirect/300")
        Mono<Response<BinaryData>> get300(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/redirect/301")
        Mono<Response<Void>> head301(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/redirect/301")
        Mono<Response<Void>> get301(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/redirect/301")
        Mono<Response<Void>> put301(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/redirect/302")
        Mono<Response<Void>> head302(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/redirect/302")
        Mono<Response<Void>> get302(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/redirect/302")
        Mono<Response<Void>> patch302(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/redirect/303")
        Mono<Response<Void>> post303(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/redirect/307")
        Mono<Response<Void>> head307(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/redirect/307")
        Mono<Response<Void>> get307(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/http/redirect/307")
        Mono<Response<Void>> put307(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/http/redirect/307")
        Mono<Response<Void>> patch307(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/http/redirect/307")
        Mono<Response<Void>> post307(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/http/redirect/307")
        Mono<Response<Void>> delete307(@HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head300WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head300(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head300WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head300(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head300WithResponse(RequestOptions requestOptions, Context context) {
        return head300WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     String
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get300WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get300(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     String
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get300WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get300(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     String
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get300WithResponse(RequestOptions requestOptions, Context context) {
        return get300WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head301WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head301(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head301WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head301(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head301WithResponse(RequestOptions requestOptions, Context context) {
        return head301WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get301WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get301(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get301WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get301(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get301WithResponse(RequestOptions requestOptions, Context context) {
        return get301WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put301WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put301(this.client.getHost(), requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put301WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put301(this.client.getHost(), requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put301WithResponse(RequestOptions requestOptions, Context context) {
        return put301WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head302WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head302(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head302WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head302(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head302WithResponse(RequestOptions requestOptions, Context context) {
        return head302WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get302WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get302(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get302WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get302(this.client.getHost(), requestOptions, context);
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get302WithResponse(RequestOptions requestOptions, Context context) {
        return get302WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch302WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch302(this.client.getHost(), requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch302WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch302(this.client.getHost(), requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch302WithResponse(RequestOptions requestOptions, Context context) {
        return patch302WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post303WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post303(this.client.getHost(), requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post303WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post303(this.client.getHost(), requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post303WithResponse(RequestOptions requestOptions, Context context) {
        return post303WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Redirect with 307, resulting in a 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head307WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head307(this.client.getHost(), requestOptions, context));
    }

    /**
     * Redirect with 307, resulting in a 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head307WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.head307(this.client.getHost(), requestOptions, context);
    }

    /**
     * Redirect with 307, resulting in a 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head307WithResponse(RequestOptions requestOptions, Context context) {
        return head307WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Redirect get with 307, resulting in a 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get307WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.get307(this.client.getHost(), requestOptions, context));
    }

    /**
     * Redirect get with 307, resulting in a 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get307WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.get307(this.client.getHost(), requestOptions, context);
    }

    /**
     * Redirect get with 307, resulting in a 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get307WithResponse(RequestOptions requestOptions, Context context) {
        return get307WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put307WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put307(this.client.getHost(), requestOptions, context));
    }

    /**
     * Put redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put307WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put307(this.client.getHost(), requestOptions, context);
    }

    /**
     * Put redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put307WithResponse(RequestOptions requestOptions, Context context) {
        return put307WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch307WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.patch307(this.client.getHost(), requestOptions, context));
    }

    /**
     * Patch redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch307WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.patch307(this.client.getHost(), requestOptions, context);
    }

    /**
     * Patch redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch307WithResponse(RequestOptions requestOptions, Context context) {
        return patch307WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post307WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post307(this.client.getHost(), requestOptions, context));
    }

    /**
     * Post redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post307WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post307(this.client.getHost(), requestOptions, context);
    }

    /**
     * Post redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post307WithResponse(RequestOptions requestOptions, Context context) {
        return post307WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Delete redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete307WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.delete307(this.client.getHost(), requestOptions, context));
    }

    /**
     * Delete redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete307WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete307(this.client.getHost(), requestOptions, context);
    }

    /**
     * Delete redirected with 307, resulting in a 200 after redirect.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete307WithResponse(RequestOptions requestOptions, Context context) {
        return delete307WithResponseAsync(requestOptions, context).block();
    }
}
