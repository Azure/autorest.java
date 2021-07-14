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
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/integer")
        Mono<Response<Void>> responseInteger(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/long")
        Mono<Response<Void>> paramLong(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/long")
        Mono<Response<Void>> responseLong(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/float")
        Mono<Response<Void>> paramFloat(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/float")
        Mono<Response<Void>> responseFloat(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/double")
        Mono<Response<Void>> paramDouble(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/double")
        Mono<Response<Void>> responseDouble(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/bool")
        Mono<Response<Void>> paramBool(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/bool")
        Mono<Response<Void>> responseBool(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/string")
        Mono<Response<Void>> paramString(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/string")
        Mono<Response<Void>> responseString(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/date")
        Mono<Response<Void>> paramDate(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/date")
        Mono<Response<Void>> responseDate(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/datetime")
        Mono<Response<Void>> paramDatetime(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/datetime")
        Mono<Response<Void>> responseDatetime(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/datetimerfc1123")
        Mono<Response<Void>> paramDatetimeRfc1123(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/datetimerfc1123")
        Mono<Response<Void>> responseDatetimeRfc1123(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/duration")
        Mono<Response<Void>> paramDuration(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/duration")
        Mono<Response<Void>> responseDuration(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/byte")
        Mono<Response<Void>> paramByte(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/byte")
        Mono<Response<Void>> responseByte(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/param/prim/enum")
        Mono<Response<Void>> paramEnum(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/header/response/prim/enum")
        Mono<Response<Void>> responseEnum(
                @HostParam("$host") String host,
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramExistingKey(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramExistingKey(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(RequestOptions requestOptions) {
        return paramExistingKeyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(RequestOptions requestOptions, Context context) {
        return paramExistingKeyWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(RequestOptions requestOptions) {
        paramExistingKeyAsync(requestOptions).block();
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramExistingKeyWithResponse(RequestOptions requestOptions, Context context) {
        return paramExistingKeyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramProtectedKey(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramProtectedKey(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(RequestOptions requestOptions) {
        return paramProtectedKeyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(RequestOptions requestOptions, Context context) {
        return paramProtectedKeyWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramProtectedKey(RequestOptions requestOptions) {
        paramProtectedKeyAsync(requestOptions).block();
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramProtectedKeyWithResponse(RequestOptions requestOptions, Context context) {
        return paramProtectedKeyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Send a post request with header values 1 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramInteger(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Send a post request with header values 1 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramInteger(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Send a post request with header values 1 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(RequestOptions requestOptions) {
        return paramIntegerWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Send a post request with header values 1 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(RequestOptions requestOptions, Context context) {
        return paramIntegerWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Send a post request with header values 1 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramInteger(RequestOptions requestOptions) {
        paramIntegerAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Send a post request with header values 1 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramIntegerWithResponse(RequestOptions requestOptions, Context context) {
        return paramIntegerWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseIntegerWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseInteger(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseIntegerWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseInteger(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(RequestOptions requestOptions) {
        return responseIntegerWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(RequestOptions requestOptions, Context context) {
        return responseIntegerWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseInteger(RequestOptions requestOptions) {
        responseIntegerAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseIntegerWithResponse(RequestOptions requestOptions, Context context) {
        return responseIntegerWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Send a post request with header values 105 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramLong(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Send a post request with header values 105 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramLong(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Send a post request with header values 105 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(RequestOptions requestOptions) {
        return paramLongWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Send a post request with header values 105 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(RequestOptions requestOptions, Context context) {
        return paramLongWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Send a post request with header values 105 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramLong(RequestOptions requestOptions) {
        paramLongAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Send a post request with header values 105 or -2</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramLongWithResponse(RequestOptions requestOptions, Context context) {
        return paramLongWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseLongWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseLong(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseLongWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseLong(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(RequestOptions requestOptions) {
        return responseLongWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(RequestOptions requestOptions, Context context) {
        return responseLongWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseLong(RequestOptions requestOptions) {
        responseLongAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseLongWithResponse(RequestOptions requestOptions, Context context) {
        return responseLongWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramFloat(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramFloat(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(RequestOptions requestOptions) {
        return paramFloatWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(RequestOptions requestOptions, Context context) {
        return paramFloatWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramFloat(RequestOptions requestOptions) {
        paramFloatAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramFloatWithResponse(RequestOptions requestOptions, Context context) {
        return paramFloatWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseFloatWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseFloat(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseFloatWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseFloat(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(RequestOptions requestOptions) {
        return responseFloatWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(RequestOptions requestOptions, Context context) {
        return responseFloatWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseFloat(RequestOptions requestOptions) {
        responseFloatAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseFloatWithResponse(RequestOptions requestOptions, Context context) {
        return responseFloatWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramDouble(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDouble(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(RequestOptions requestOptions) {
        return paramDoubleWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(RequestOptions requestOptions, Context context) {
        return paramDoubleWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDouble(RequestOptions requestOptions) {
        paramDoubleAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDoubleWithResponse(RequestOptions requestOptions, Context context) {
        return paramDoubleWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDoubleWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDouble(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDoubleWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDouble(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(RequestOptions requestOptions) {
        return responseDoubleWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(RequestOptions requestOptions, Context context) {
        return responseDoubleWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDouble(RequestOptions requestOptions) {
        responseDoubleAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDoubleWithResponse(RequestOptions requestOptions, Context context) {
        return responseDoubleWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Send a post request with header values true or false</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramBool(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Send a post request with header values true or false</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramBool(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Send a post request with header values true or false</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(RequestOptions requestOptions) {
        return paramBoolWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Send a post request with header values true or false</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(RequestOptions requestOptions, Context context) {
        return paramBoolWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Send a post request with header values true or false</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramBool(RequestOptions requestOptions) {
        paramBoolAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Send a post request with header values true or false</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramBoolWithResponse(RequestOptions requestOptions, Context context) {
        return paramBoolWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseBoolWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseBool(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseBoolWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseBool(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(RequestOptions requestOptions) {
        return responseBoolWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(RequestOptions requestOptions, Context context) {
        return responseBoolWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseBool(RequestOptions requestOptions) {
        responseBoolAsync(requestOptions).block();
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseBoolWithResponse(RequestOptions requestOptions, Context context) {
        return responseBoolWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramString(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramString(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(RequestOptions requestOptions) {
        return paramStringWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(RequestOptions requestOptions, Context context) {
        return paramStringWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramString(RequestOptions requestOptions) {
        paramStringAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramStringWithResponse(RequestOptions requestOptions, Context context) {
        return paramStringWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseStringWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseString(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseStringWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseString(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(RequestOptions requestOptions) {
        return responseStringWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(RequestOptions requestOptions, Context context) {
        return responseStringWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseString(RequestOptions requestOptions) {
        responseStringAsync(requestOptions).block();
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseStringWithResponse(RequestOptions requestOptions, Context context) {
        return responseStringWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramDate(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDate(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(RequestOptions requestOptions) {
        return paramDateWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(RequestOptions requestOptions, Context context) {
        return paramDateWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDate(RequestOptions requestOptions) {
        paramDateAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDateWithResponse(RequestOptions requestOptions, Context context) {
        return paramDateWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDateWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDate(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDateWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDate(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(RequestOptions requestOptions) {
        return responseDateWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(RequestOptions requestOptions, Context context) {
        return responseDateWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDate(RequestOptions requestOptions) {
        responseDateAsync(requestOptions).block();
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDateWithResponse(RequestOptions requestOptions, Context context) {
        return responseDateWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramDatetime(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDatetime(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(RequestOptions requestOptions) {
        return paramDatetimeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(RequestOptions requestOptions, Context context) {
        return paramDatetimeWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetime(RequestOptions requestOptions) {
        paramDatetimeAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDatetimeWithResponse(RequestOptions requestOptions, Context context) {
        return paramDatetimeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDatetime(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDatetime(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(RequestOptions requestOptions) {
        return responseDatetimeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(RequestOptions requestOptions, Context context) {
        return responseDatetimeWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetime(RequestOptions requestOptions) {
        responseDatetimeAsync(requestOptions).block();
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDatetimeWithResponse(RequestOptions requestOptions, Context context) {
        return responseDatetimeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramDatetimeRfc1123(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDatetimeRfc1123(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(RequestOptions requestOptions) {
        return paramDatetimeRfc1123WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(RequestOptions requestOptions, Context context) {
        return paramDatetimeRfc1123WithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetimeRfc1123(RequestOptions requestOptions) {
        paramDatetimeRfc1123Async(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDatetimeRfc1123WithResponse(RequestOptions requestOptions, Context context) {
        return paramDatetimeRfc1123WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeRfc1123WithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDatetimeRfc1123(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeRfc1123WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDatetimeRfc1123(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(RequestOptions requestOptions) {
        return responseDatetimeRfc1123WithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(RequestOptions requestOptions, Context context) {
        return responseDatetimeRfc1123WithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetimeRfc1123(RequestOptions requestOptions) {
        responseDatetimeRfc1123Async(requestOptions).block();
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDatetimeRfc1123WithResponse(RequestOptions requestOptions, Context context) {
        return responseDatetimeRfc1123WithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramDuration(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramDuration(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(RequestOptions requestOptions) {
        return paramDurationWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(RequestOptions requestOptions, Context context) {
        return paramDurationWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDuration(RequestOptions requestOptions) {
        paramDurationAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDurationWithResponse(RequestOptions requestOptions, Context context) {
        return paramDurationWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDurationWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseDuration(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDurationWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseDuration(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(RequestOptions requestOptions) {
        return responseDurationWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(RequestOptions requestOptions, Context context) {
        return responseDurationWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDuration(RequestOptions requestOptions) {
        responseDurationAsync(requestOptions).block();
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDurationWithResponse(RequestOptions requestOptions, Context context) {
        return responseDurationWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramByte(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramByte(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(RequestOptions requestOptions) {
        return paramByteWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(RequestOptions requestOptions, Context context) {
        return paramByteWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramByte(RequestOptions requestOptions) {
        paramByteAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramByteWithResponse(RequestOptions requestOptions, Context context) {
        return paramByteWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseByteWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseByte(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseByteWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseByte(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(RequestOptions requestOptions) {
        return responseByteWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(RequestOptions requestOptions, Context context) {
        return responseByteWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseByte(RequestOptions requestOptions) {
        responseByteAsync(requestOptions).block();
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseByteWithResponse(RequestOptions requestOptions, Context context) {
        return responseByteWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values 'GREY' </td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.paramEnum(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values 'GREY' </td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.paramEnum(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values 'GREY' </td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(RequestOptions requestOptions) {
        return paramEnumWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values 'GREY' </td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(RequestOptions requestOptions, Context context) {
        return paramEnumWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values 'GREY' </td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramEnum(RequestOptions requestOptions) {
        paramEnumAsync(requestOptions).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Send a post request with header values 'GREY' </td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramEnumWithResponse(RequestOptions requestOptions, Context context) {
        return paramEnumWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseEnumWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.responseEnum(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseEnumWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.responseEnum(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(RequestOptions requestOptions) {
        return responseEnumWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(RequestOptions requestOptions, Context context) {
        return responseEnumWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseEnum(RequestOptions requestOptions) {
        responseEnumAsync(requestOptions).block();
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseEnumWithResponse(RequestOptions requestOptions, Context context) {
        return responseEnumWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
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
     * <p><strong>Optional Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Optional Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Description</th></tr>
     *     <tr><td>accept</td><td>String</td><td>Accept header</td></tr>
     * </table>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> customRequestIdWithResponse(RequestOptions requestOptions, Context context) {
        return customRequestIdWithResponseAsync(requestOptions, context).block();
    }
}
