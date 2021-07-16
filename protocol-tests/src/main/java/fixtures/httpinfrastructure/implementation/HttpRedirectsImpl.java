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
import java.util.List;
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
        Mono<Response<Void>> head300(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/redirect/300")
        Mono<Response<List<String>>> get300(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/redirect/301")
        Mono<Response<Void>> head301(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/redirect/301")
        Mono<Response<Void>> get301(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/redirect/301")
        Mono<Response<Void>> put301(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/redirect/302")
        Mono<Response<Void>> head302(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/redirect/302")
        Mono<Response<Void>> get302(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/redirect/302")
        Mono<Response<Void>> patch302(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/redirect/303")
        Mono<Response<Void>> post303(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/http/redirect/307")
        Mono<Response<Void>> head307(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/redirect/307")
        Mono<Response<Void>> get307(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/http/redirect/307")
        Mono<Response<Void>> put307(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Patch("/http/redirect/307")
        Mono<Response<Void>> patch307(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/http/redirect/307")
        Mono<Response<Void>> post307(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/http/redirect/307")
        Mono<Response<Void>> delete307(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData booleanValue,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head300WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head300(this.client.getHost(), accept, requestOptions, context));
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head300WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head300(this.client.getHost(), accept, requestOptions, context);
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head300Async(RequestOptions requestOptions) {
        return head300WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head300Async(RequestOptions requestOptions, Context context) {
        return head300WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head300(RequestOptions requestOptions) {
        head300Async(requestOptions).block();
    }

    /** Return 300 status code and redirect to /http/success/200. */
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> get300WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get300(this.client.getHost(), accept, requestOptions, context));
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> get300WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get300(this.client.getHost(), accept, requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> get300Async(RequestOptions requestOptions) {
        return get300WithResponseAsync(requestOptions)
                .flatMap(
                        (Response<List<String>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> get300Async(RequestOptions requestOptions, Context context) {
        return get300WithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<List<String>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> get300(RequestOptions requestOptions) {
        return get300Async(requestOptions).block();
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<List<String>> get300WithResponse(RequestOptions requestOptions, Context context) {
        return get300WithResponseAsync(requestOptions, context).block();
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head301WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head301(this.client.getHost(), accept, requestOptions, context));
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head301WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head301(this.client.getHost(), accept, requestOptions, context);
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head301Async(RequestOptions requestOptions) {
        return head301WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head301Async(RequestOptions requestOptions, Context context) {
        return head301WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head301(RequestOptions requestOptions) {
        head301Async(requestOptions).block();
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head301WithResponse(RequestOptions requestOptions, Context context) {
        return head301WithResponseAsync(requestOptions, context).block();
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get301WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get301(this.client.getHost(), accept, requestOptions, context));
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get301WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get301(this.client.getHost(), accept, requestOptions, context);
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get301Async(RequestOptions requestOptions) {
        return get301WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get301Async(RequestOptions requestOptions, Context context) {
        return get301WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get301(RequestOptions requestOptions) {
        get301Async(requestOptions).block();
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get301WithResponse(RequestOptions requestOptions, Context context) {
        return get301WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put301WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put301(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put301WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put301(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put301Async(RequestOptions requestOptions) {
        return put301WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put301Async(RequestOptions requestOptions, Context context) {
        return put301WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put301(RequestOptions requestOptions) {
        put301Async(requestOptions).block();
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put301WithResponse(RequestOptions requestOptions, Context context) {
        return put301WithResponseAsync(requestOptions, context).block();
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head302WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head302(this.client.getHost(), accept, requestOptions, context));
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head302WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head302(this.client.getHost(), accept, requestOptions, context);
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head302Async(RequestOptions requestOptions) {
        return head302WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head302Async(RequestOptions requestOptions, Context context) {
        return head302WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head302(RequestOptions requestOptions) {
        head302Async(requestOptions).block();
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head302WithResponse(RequestOptions requestOptions, Context context) {
        return head302WithResponseAsync(requestOptions, context).block();
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get302WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get302(this.client.getHost(), accept, requestOptions, context));
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get302WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get302(this.client.getHost(), accept, requestOptions, context);
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get302Async(RequestOptions requestOptions) {
        return get302WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get302Async(RequestOptions requestOptions, Context context) {
        return get302WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get302(RequestOptions requestOptions) {
        get302Async(requestOptions).block();
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get302WithResponse(RequestOptions requestOptions, Context context) {
        return get302WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch302WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch302(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch302WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch302(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch302Async(RequestOptions requestOptions) {
        return patch302WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch302Async(RequestOptions requestOptions, Context context) {
        return patch302WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch302(RequestOptions requestOptions) {
        patch302Async(requestOptions).block();
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch302WithResponse(RequestOptions requestOptions, Context context) {
        return patch302WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post303WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post303(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post303WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post303(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post303Async(RequestOptions requestOptions) {
        return post303WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post303Async(RequestOptions requestOptions, Context context) {
        return post303WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post303(RequestOptions requestOptions) {
        post303Async(requestOptions).block();
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post303WithResponse(RequestOptions requestOptions, Context context) {
        return post303WithResponseAsync(requestOptions, context).block();
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head307WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head307(this.client.getHost(), accept, requestOptions, context));
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head307WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.head307(this.client.getHost(), accept, requestOptions, context);
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head307Async(RequestOptions requestOptions) {
        return head307WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head307Async(RequestOptions requestOptions, Context context) {
        return head307WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head307(RequestOptions requestOptions) {
        head307Async(requestOptions).block();
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head307WithResponse(RequestOptions requestOptions, Context context) {
        return head307WithResponseAsync(requestOptions, context).block();
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get307WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get307(this.client.getHost(), accept, requestOptions, context));
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get307WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get307(this.client.getHost(), accept, requestOptions, context);
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get307Async(RequestOptions requestOptions) {
        return get307WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get307Async(RequestOptions requestOptions, Context context) {
        return get307WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get307(RequestOptions requestOptions) {
        get307Async(requestOptions).block();
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get307WithResponse(RequestOptions requestOptions, Context context) {
        return get307WithResponseAsync(requestOptions, context).block();
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put307WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put307(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put307WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.put307(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put307Async(RequestOptions requestOptions) {
        return put307WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put307Async(RequestOptions requestOptions, Context context) {
        return put307WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put307(RequestOptions requestOptions) {
        put307Async(requestOptions).block();
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put307WithResponse(RequestOptions requestOptions, Context context) {
        return put307WithResponseAsync(requestOptions, context).block();
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch307WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.patch307(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch307WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.patch307(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch307Async(RequestOptions requestOptions) {
        return patch307WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch307Async(RequestOptions requestOptions, Context context) {
        return patch307WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch307(RequestOptions requestOptions) {
        patch307Async(requestOptions).block();
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch307WithResponse(RequestOptions requestOptions, Context context) {
        return patch307WithResponseAsync(requestOptions, context).block();
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post307WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post307(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post307WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.post307(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post307Async(RequestOptions requestOptions) {
        return post307WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post307Async(RequestOptions requestOptions, Context context) {
        return post307WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post307(RequestOptions requestOptions) {
        post307Async(requestOptions).block();
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post307WithResponse(RequestOptions requestOptions, Context context) {
        return post307WithResponseAsync(requestOptions, context).block();
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete307WithResponseAsync(RequestOptions requestOptions) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete307(this.client.getHost(), booleanValue, accept, requestOptions, context));
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete307WithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData booleanValue = BinaryData.fromObject("true");
        final String accept = "application/json";
        return service.delete307(this.client.getHost(), booleanValue, accept, requestOptions, context);
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete307Async(RequestOptions requestOptions) {
        return delete307WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete307Async(RequestOptions requestOptions, Context context) {
        return delete307WithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete307(RequestOptions requestOptions) {
        delete307Async(requestOptions).block();
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete307WithResponse(RequestOptions requestOptions, Context context) {
        return delete307WithResponseAsync(requestOptions, context).block();
    }
}
