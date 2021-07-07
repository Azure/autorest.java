package fixtures.header.implementation;

import com.azure.core.annotation.HeaderParam;
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

/** An instance of this class provides access to all the operations defined in Headers. */
public final class HeadersImpl {
    /** The proxy service used to perform REST calls. */
    private final HeadersService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATHeaderServiceImpl client;

    /**
     * Initializes an instance of HeadersImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HeadersImpl(AutoRestSwaggerBATHeaderServiceImpl client) {
        this.service = RestProxy.create(HeadersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATHeaderServiceHeaders to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATHe")
    private interface HeadersService {
        @Post("/header/param/existingkey")
        Mono<Response<Void>> paramExistingKey(
                @HostParam("$host") String host,
                @HeaderParam("User-Agent") String userAgent,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/existingkey")
        Mono<Response<Void>> responseExistingKey(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/protectedkey")
        Mono<Response<Void>> paramProtectedKey(
                @HostParam("$host") String host,
                @HeaderParam("Content-Type") String contentType,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/protectedkey")
        Mono<Response<Void>> responseProtectedKey(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/integer")
        Mono<Response<Void>> paramInteger(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") int value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/integer")
        Mono<Response<Void>> responseInteger(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/long")
        Mono<Response<Void>> paramLong(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") long value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/long")
        Mono<Response<Void>> responseLong(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/float")
        Mono<Response<Void>> paramFloat(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") float value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/float")
        Mono<Response<Void>> responseFloat(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/double")
        Mono<Response<Void>> paramDouble(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") double value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/double")
        Mono<Response<Void>> responseDouble(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/bool")
        Mono<Response<Void>> paramBool(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") boolean value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/bool")
        Mono<Response<Void>> responseBool(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/string")
        Mono<Response<Void>> paramString(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/string")
        Mono<Response<Void>> responseString(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/date")
        Mono<Response<Void>> paramDate(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") String value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/date")
        Mono<Response<Void>> responseDate(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/datetime")
        Mono<Response<Void>> paramDatetime(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") String value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/datetime")
        Mono<Response<Void>> responseDatetime(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/datetimerfc1123")
        Mono<Response<Void>> paramDatetimeRfc1123(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/datetimerfc1123")
        Mono<Response<Void>> responseDatetimeRfc1123(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/duration")
        Mono<Response<Void>> paramDuration(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") String value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/duration")
        Mono<Response<Void>> responseDuration(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/byte")
        Mono<Response<Void>> paramByte(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") String value,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/byte")
        Mono<Response<Void>> responseByte(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/enum")
        Mono<Response<Void>> paramEnum(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/enum")
        Mono<Response<Void>> responseEnum(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/custom/x-ms-client-request-id/9C4D50EE-2D56-4CD3-8152-34347DC9F2B0")
        Mono<Response<Void>> customRequestId(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(String userAgent, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramExistingKey(this.client.getHost(), userAgent, accept, requestOptions, context));
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(
            String userAgent, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramExistingKey(this.client.getHost(), userAgent, accept, requestOptions, context);
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(String userAgent, RequestOptions requestOptions) {
        return paramExistingKeyWithResponseAsync(userAgent, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(String userAgent, RequestOptions requestOptions, Context context) {
        return paramExistingKeyWithResponseAsync(userAgent, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(String userAgent, RequestOptions requestOptions) {
        paramExistingKeyAsync(userAgent, requestOptions).block();
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramExistingKeyWithResponse(
            String userAgent, RequestOptions requestOptions, Context context) {
        return paramExistingKeyWithResponseAsync(userAgent, requestOptions, context).block();
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseExistingKeyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseExistingKey(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseExistingKeyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseExistingKey(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseExistingKeyAsync(RequestOptions requestOptions) {
        return responseExistingKeyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseExistingKeyAsync(RequestOptions requestOptions, Context context) {
        return responseExistingKeyWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseExistingKey(RequestOptions requestOptions) {
        responseExistingKeyAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseExistingKeyWithResponse(RequestOptions requestOptions, Context context) {
        return responseExistingKeyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(String contentType, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.paramProtectedKey(this.client.getHost(), contentType, accept, requestOptions, context));
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(
            String contentType, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramProtectedKey(this.client.getHost(), contentType, accept, requestOptions, context);
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(String contentType, RequestOptions requestOptions) {
        return paramProtectedKeyWithResponseAsync(contentType, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(String contentType, RequestOptions requestOptions, Context context) {
        return paramProtectedKeyWithResponseAsync(contentType, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramProtectedKey(String contentType, RequestOptions requestOptions) {
        paramProtectedKeyAsync(contentType, requestOptions).block();
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramProtectedKeyWithResponse(
            String contentType, RequestOptions requestOptions, Context context) {
        return paramProtectedKeyWithResponseAsync(contentType, requestOptions, context).block();
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseProtectedKeyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseProtectedKey(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseProtectedKeyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseProtectedKey(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseProtectedKeyAsync(RequestOptions requestOptions) {
        return responseProtectedKeyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseProtectedKeyAsync(RequestOptions requestOptions, Context context) {
        return responseProtectedKeyWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseProtectedKey(RequestOptions requestOptions) {
        responseProtectedKeyAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseProtectedKeyWithResponse(RequestOptions requestOptions, Context context) {
        return responseProtectedKeyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(
            String scenario, int value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.paramInteger(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(
            String scenario, int value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramInteger(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(String scenario, int value, RequestOptions requestOptions) {
        return paramIntegerWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(String scenario, int value, RequestOptions requestOptions, Context context) {
        return paramIntegerWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramInteger(String scenario, int value, RequestOptions requestOptions) {
        paramIntegerAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramIntegerWithResponse(
            String scenario, int value, RequestOptions requestOptions, Context context) {
        return paramIntegerWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseIntegerWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseInteger(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseIntegerWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseInteger(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(String scenario, RequestOptions requestOptions) {
        return responseIntegerWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseIntegerWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseInteger(String scenario, RequestOptions requestOptions) {
        responseIntegerAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseIntegerWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseIntegerWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(String scenario, long value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramLong(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(
            String scenario, long value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramLong(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(String scenario, long value, RequestOptions requestOptions) {
        return paramLongWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(String scenario, long value, RequestOptions requestOptions, Context context) {
        return paramLongWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramLong(String scenario, long value, RequestOptions requestOptions) {
        paramLongAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramLongWithResponse(
            String scenario, long value, RequestOptions requestOptions, Context context) {
        return paramLongWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseLongWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseLong(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseLongWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseLong(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(String scenario, RequestOptions requestOptions) {
        return responseLongWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseLongWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseLong(String scenario, RequestOptions requestOptions) {
        responseLongAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseLongWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseLongWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(
            String scenario, float value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramFloat(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(
            String scenario, float value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramFloat(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(String scenario, float value, RequestOptions requestOptions) {
        return paramFloatWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(String scenario, float value, RequestOptions requestOptions, Context context) {
        return paramFloatWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramFloat(String scenario, float value, RequestOptions requestOptions) {
        paramFloatAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramFloatWithResponse(
            String scenario, float value, RequestOptions requestOptions, Context context) {
        return paramFloatWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseFloatWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseFloat(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseFloatWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseFloat(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(String scenario, RequestOptions requestOptions) {
        return responseFloatWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseFloatWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseFloat(String scenario, RequestOptions requestOptions) {
        responseFloatAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseFloatWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseFloatWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(
            String scenario, double value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.paramDouble(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(
            String scenario, double value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDouble(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(String scenario, double value, RequestOptions requestOptions) {
        return paramDoubleWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(String scenario, double value, RequestOptions requestOptions, Context context) {
        return paramDoubleWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDouble(String scenario, double value, RequestOptions requestOptions) {
        paramDoubleAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDoubleWithResponse(
            String scenario, double value, RequestOptions requestOptions, Context context) {
        return paramDoubleWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDoubleWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDouble(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDoubleWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDouble(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(String scenario, RequestOptions requestOptions) {
        return responseDoubleWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseDoubleWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDouble(String scenario, RequestOptions requestOptions) {
        responseDoubleAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDoubleWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseDoubleWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(
            String scenario, boolean value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramBool(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(
            String scenario, boolean value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramBool(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(String scenario, boolean value, RequestOptions requestOptions) {
        return paramBoolWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(String scenario, boolean value, RequestOptions requestOptions, Context context) {
        return paramBoolWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramBool(String scenario, boolean value, RequestOptions requestOptions) {
        paramBoolAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramBoolWithResponse(
            String scenario, boolean value, RequestOptions requestOptions, Context context) {
        return paramBoolWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseBoolWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseBool(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseBoolWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseBool(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(String scenario, RequestOptions requestOptions) {
        return responseBoolWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseBoolWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseBool(String scenario, RequestOptions requestOptions) {
        responseBoolAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseBoolWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseBoolWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramString(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramString(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(String scenario, RequestOptions requestOptions) {
        return paramStringWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(String scenario, RequestOptions requestOptions, Context context) {
        return paramStringWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramString(String scenario, RequestOptions requestOptions) {
        paramStringAsync(scenario, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramStringWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return paramStringWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseStringWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseString(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseStringWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseString(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(String scenario, RequestOptions requestOptions) {
        return responseStringWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseStringWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseString(String scenario, RequestOptions requestOptions) {
        responseStringAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseStringWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseStringWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramDate(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDate(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(String scenario, String value, RequestOptions requestOptions) {
        return paramDateWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramDateWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDate(String scenario, String value, RequestOptions requestOptions) {
        paramDateAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDateWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramDateWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDateWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDate(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDateWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDate(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(String scenario, RequestOptions requestOptions) {
        return responseDateWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseDateWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDate(String scenario, RequestOptions requestOptions) {
        responseDateAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDateWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseDateWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.paramDatetime(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDatetime(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(String scenario, String value, RequestOptions requestOptions) {
        return paramDatetimeWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramDatetimeWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetime(String scenario, String value, RequestOptions requestOptions) {
        paramDatetimeAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDatetimeWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramDatetimeWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDatetime(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDatetime(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(String scenario, RequestOptions requestOptions) {
        return responseDatetimeWithResponseAsync(scenario, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseDatetimeWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetime(String scenario, RequestOptions requestOptions) {
        responseDatetimeAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDatetimeWithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return responseDatetimeWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.paramDatetimeRfc1123(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDatetimeRfc1123(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(String scenario, RequestOptions requestOptions) {
        return paramDatetimeRfc1123WithResponseAsync(scenario, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(String scenario, RequestOptions requestOptions, Context context) {
        return paramDatetimeRfc1123WithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetimeRfc1123(String scenario, RequestOptions requestOptions) {
        paramDatetimeRfc1123Async(scenario, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDatetimeRfc1123WithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return paramDatetimeRfc1123WithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeRfc1123WithResponseAsync(
            String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.responseDatetimeRfc1123(
                                this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeRfc1123WithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDatetimeRfc1123(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(String scenario, RequestOptions requestOptions) {
        return responseDatetimeRfc1123WithResponseAsync(scenario, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(String scenario, RequestOptions requestOptions, Context context) {
        return responseDatetimeRfc1123WithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetimeRfc1123(String scenario, RequestOptions requestOptions) {
        responseDatetimeRfc1123Async(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDatetimeRfc1123WithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return responseDatetimeRfc1123WithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.paramDuration(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDuration(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(String scenario, String value, RequestOptions requestOptions) {
        return paramDurationWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramDurationWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDuration(String scenario, String value, RequestOptions requestOptions) {
        paramDurationAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDurationWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramDurationWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDurationWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDuration(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDurationWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDuration(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(String scenario, RequestOptions requestOptions) {
        return responseDurationWithResponseAsync(scenario, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseDurationWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDuration(String scenario, RequestOptions requestOptions) {
        responseDurationAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDurationWithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return responseDurationWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramByte(this.client.getHost(), scenario, value, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramByte(this.client.getHost(), scenario, value, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(String scenario, String value, RequestOptions requestOptions) {
        return paramByteWithResponseAsync(scenario, value, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramByteWithResponseAsync(scenario, value, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramByte(String scenario, String value, RequestOptions requestOptions) {
        paramByteAsync(scenario, value, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramByteWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return paramByteWithResponseAsync(scenario, value, requestOptions, context).block();
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseByteWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseByte(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseByteWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseByte(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(String scenario, RequestOptions requestOptions) {
        return responseByteWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseByteWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseByte(String scenario, RequestOptions requestOptions) {
        responseByteAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseByteWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseByteWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramEnum(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramEnum(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(String scenario, RequestOptions requestOptions) {
        return paramEnumWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(String scenario, RequestOptions requestOptions, Context context) {
        return paramEnumWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramEnum(String scenario, RequestOptions requestOptions) {
        paramEnumAsync(scenario, requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramEnumWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return paramEnumWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseEnumWithResponseAsync(String scenario, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseEnum(this.client.getHost(), scenario, accept, requestOptions, context));
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseEnumWithResponseAsync(
            String scenario, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseEnum(this.client.getHost(), scenario, accept, requestOptions, context);
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(String scenario, RequestOptions requestOptions) {
        return responseEnumWithResponseAsync(scenario, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(String scenario, RequestOptions requestOptions, Context context) {
        return responseEnumWithResponseAsync(scenario, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseEnum(String scenario, RequestOptions requestOptions) {
        responseEnumAsync(scenario, requestOptions).block();
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseEnumWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return responseEnumWithResponseAsync(scenario, requestOptions, context).block();
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> customRequestIdWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.customRequestId(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> customRequestIdWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.customRequestId(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customRequestIdAsync(RequestOptions requestOptions) {
        return customRequestIdWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customRequestIdAsync(RequestOptions requestOptions, Context context) {
        return customRequestIdWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customRequestId(RequestOptions requestOptions) {
        customRequestIdAsync(requestOptions).block();
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> customRequestIdWithResponse(RequestOptions requestOptions, Context context) {
        return customRequestIdWithResponseAsync(requestOptions, context).block();
    }
}
