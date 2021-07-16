package fixtures.httpinfrastructure.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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

/** An instance of this class provides access to all the operations defined in MultipleResponses. */
public final class MultipleResponsesImpl {
    /** The proxy service used to perform REST calls. */
    private final MultipleResponsesService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestServiceImpl client;

    /**
     * Initializes an instance of MultipleResponsesImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    MultipleResponsesImpl(AutoRestHttpInfrastructureTestServiceImpl client) {
        this.service =
                RestProxy.create(
                        MultipleResponsesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceMultipleResponses to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface MultipleResponsesService {
        @Get("/http/payloads/200/A/204/none/default/Error/response/200/valid")
        Mono<Response<BinaryData>> get200Model204NoModelDefaultError200Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/204/none/default/Error/response/204/none")
        Mono<Response<BinaryData>> get200Model204NoModelDefaultError204Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/204/none/default/Error/response/201/valid")
        Mono<Response<BinaryData>> get200Model204NoModelDefaultError201Invalid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/204/none/default/Error/response/202/none")
        Mono<Response<BinaryData>> get200Model204NoModelDefaultError202None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/204/none/default/Error/response/400/valid")
        Mono<Response<BinaryData>> get200Model204NoModelDefaultError400Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/B/default/Error/response/200/valid")
        Mono<Response<BinaryData>> get200Model201ModelDefaultError200Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/B/default/Error/response/201/valid")
        Mono<Response<BinaryData>> get200Model201ModelDefaultError201Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/B/default/Error/response/400/valid")
        Mono<Response<BinaryData>> get200Model201ModelDefaultError400Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/200/valid")
        Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError200Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/201/valid")
        Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError201Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/404/valid")
        Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError404Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/400/valid")
        Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError400Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/202/none/204/none/default/Error/response/202/none")
        Mono<Response<Void>> get202None204NoneDefaultError202None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/202/none/204/none/default/Error/response/204/none")
        Mono<Response<Void>> get202None204NoneDefaultError204None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/202/none/204/none/default/Error/response/400/valid")
        Mono<Response<Void>> get202None204NoneDefaultError400Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/202/none/204/none/default/none/response/202/invalid")
        Mono<Response<Void>> get202None204NoneDefaultNone202Invalid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/202/none/204/none/default/none/response/204/none")
        Mono<Response<Void>> get202None204NoneDefaultNone204None(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/202/none/204/none/default/none/response/400/none")
        Mono<Response<Void>> get202None204NoneDefaultNone400None(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/202/none/204/none/default/none/response/400/invalid")
        Mono<Response<Void>> get202None204NoneDefaultNone400Invalid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/default/A/response/200/valid")
        Mono<Response<BinaryData>> getDefaultModelA200Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/default/A/response/200/none")
        Mono<Response<BinaryData>> getDefaultModelA200None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/default/A/response/400/valid")
        Mono<Response<Void>> getDefaultModelA400Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/default/A/response/400/none")
        Mono<Response<Void>> getDefaultModelA400None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/default/none/response/200/invalid")
        Mono<Response<Void>> getDefaultNone200Invalid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/default/none/response/200/none")
        Mono<Response<Void>> getDefaultNone200None(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/default/none/response/400/invalid")
        Mono<Response<Void>> getDefaultNone400Invalid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/default/none/response/400/none")
        Mono<Response<Void>> getDefaultNone400None(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/http/payloads/200/A/response/200/none")
        Mono<Response<BinaryData>> get200ModelA200None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/response/200/valid")
        Mono<Response<BinaryData>> get200ModelA200Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/response/200/invalid")
        Mono<Response<BinaryData>> get200ModelA200Invalid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/response/400/none")
        Mono<Response<BinaryData>> get200ModelA400None(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/response/400/valid")
        Mono<Response<BinaryData>> get200ModelA400Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/response/400/invalid")
        Mono<Response<BinaryData>> get200ModelA400Invalid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/http/payloads/200/A/response/202/valid")
        Mono<Response<BinaryData>> get200ModelA202Valid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError200ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model204NoModelDefaultError200Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError200ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model204NoModelDefaultError200Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError200ValidAsync(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError200ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError200ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError200ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError200Valid(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError200ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError200ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError200ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError204ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model204NoModelDefaultError204Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError204ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model204NoModelDefaultError204Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError204ValidAsync(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError204ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError204ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError204ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError204Valid(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError204ValidAsync(requestOptions).block();
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError204ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError204ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError201InvalidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model204NoModelDefaultError201Invalid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError201InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model204NoModelDefaultError201Invalid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError201InvalidAsync(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError201InvalidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError201InvalidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError201InvalidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError201Invalid(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError201InvalidAsync(requestOptions).block();
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError201InvalidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError201InvalidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError202NoneWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model204NoModelDefaultError202None(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError202NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model204NoModelDefaultError202None(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError202NoneAsync(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError202NoneWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError202NoneAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError202NoneWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError202None(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError202NoneAsync(requestOptions).block();
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError202NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError202NoneWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model204NoModelDefaultError400Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model204NoModelDefaultError400Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError400ValidAsync(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError400ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model204NoModelDefaultError400ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError400ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError400Valid(RequestOptions requestOptions) {
        return get200Model204NoModelDefaultError400ValidAsync(requestOptions).block();
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model204NoModelDefaultError400ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError200ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model201ModelDefaultError200Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError200ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model201ModelDefaultError200Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model201ModelDefaultError200ValidAsync(RequestOptions requestOptions) {
        return get200Model201ModelDefaultError200ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model201ModelDefaultError200ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model201ModelDefaultError200ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model201ModelDefaultError200Valid(RequestOptions requestOptions) {
        return get200Model201ModelDefaultError200ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model201ModelDefaultError200ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model201ModelDefaultError200ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError201ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model201ModelDefaultError201Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError201ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model201ModelDefaultError201Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model201ModelDefaultError201ValidAsync(RequestOptions requestOptions) {
        return get200Model201ModelDefaultError201ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model201ModelDefaultError201ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model201ModelDefaultError201ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model201ModelDefaultError201Valid(RequestOptions requestOptions) {
        return get200Model201ModelDefaultError201ValidAsync(requestOptions).block();
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model201ModelDefaultError201ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model201ModelDefaultError201ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200Model201ModelDefaultError400Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200Model201ModelDefaultError400Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model201ModelDefaultError400ValidAsync(RequestOptions requestOptions) {
        return get200Model201ModelDefaultError400ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200Model201ModelDefaultError400ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200Model201ModelDefaultError400ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model201ModelDefaultError400Valid(RequestOptions requestOptions) {
        return get200Model201ModelDefaultError400ValidAsync(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model201ModelDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200Model201ModelDefaultError400ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200ModelA201ModelC404ModelDDefaultError200Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA201ModelC404ModelDDefaultError200Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError200ValidAsync(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError200ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError200Valid(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError200ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200ModelA201ModelC404ModelDDefaultError201Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA201ModelC404ModelDDefaultError201Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError201ValidAsync(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError201ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError201Valid(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError201ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200ModelA201ModelC404ModelDDefaultError404Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA201ModelC404ModelDDefaultError404Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError404ValidAsync(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError404ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError404Valid(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError404ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get200ModelA201ModelC404ModelDDefaultError400Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA201ModelC404ModelDDefaultError400Valid(
                this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError400ValidAsync(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA201ModelC404ModelDDefaultError400ValidAsync(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError400Valid(RequestOptions requestOptions) {
        return get200ModelA201ModelC404ModelDDefaultError400ValidAsync(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 202 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError202NoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get202None204NoneDefaultError202None(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /** Send a 202 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError202NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get202None204NoneDefaultError202None(this.client.getHost(), accept, requestOptions, context);
    }

    /** Send a 202 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError202NoneAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultError202NoneWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 202 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError202NoneAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultError202NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 202 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError202None(RequestOptions requestOptions) {
        get202None204NoneDefaultError202NoneAsync(requestOptions).block();
    }

    /** Send a 202 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultError202NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultError202NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError204NoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get202None204NoneDefaultError204None(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError204NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get202None204NoneDefaultError204None(this.client.getHost(), accept, requestOptions, context);
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError204NoneAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultError204NoneWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError204NoneAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultError204NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError204None(RequestOptions requestOptions) {
        get202None204NoneDefaultError204NoneAsync(requestOptions).block();
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultError204NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultError204NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError400ValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.get202None204NoneDefaultError400Valid(
                                this.client.getHost(), accept, requestOptions, context));
    }

    /** Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError400ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get202None204NoneDefaultError400Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /** Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError400ValidAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultError400ValidWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError400ValidAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultError400ValidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError400Valid(RequestOptions requestOptions) {
        get202None204NoneDefaultError400ValidAsync(requestOptions).block();
    }

    /** Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultError400ValidWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 202 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone202InvalidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.get202None204NoneDefaultNone202Invalid(this.client.getHost(), requestOptions, context));
    }

    /** Send a 202 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone202InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.get202None204NoneDefaultNone202Invalid(this.client.getHost(), requestOptions, context);
    }

    /** Send a 202 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone202InvalidAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultNone202InvalidWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 202 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone202InvalidAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone202InvalidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 202 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone202Invalid(RequestOptions requestOptions) {
        get202None204NoneDefaultNone202InvalidAsync(requestOptions).block();
    }

    /** Send a 202 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone202InvalidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone202InvalidWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone204NoneWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.get202None204NoneDefaultNone204None(this.client.getHost(), requestOptions, context));
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone204NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.get202None204NoneDefaultNone204None(this.client.getHost(), requestOptions, context);
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone204NoneAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultNone204NoneWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone204NoneAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone204NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone204None(RequestOptions requestOptions) {
        get202None204NoneDefaultNone204NoneAsync(requestOptions).block();
    }

    /** Send a 204 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone204NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone204NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400NoneWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.get202None204NoneDefaultNone400None(this.client.getHost(), requestOptions, context));
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.get202None204NoneDefaultNone400None(this.client.getHost(), requestOptions, context);
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone400NoneAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultNone400NoneWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone400NoneAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone400NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone400None(RequestOptions requestOptions) {
        get202None204NoneDefaultNone400NoneAsync(requestOptions).block();
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone400NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone400NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400InvalidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.get202None204NoneDefaultNone400Invalid(this.client.getHost(), requestOptions, context));
    }

    /** Send a 400 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.get202None204NoneDefaultNone400Invalid(this.client.getHost(), requestOptions, context);
    }

    /** Send a 400 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone400InvalidAsync(RequestOptions requestOptions) {
        return get202None204NoneDefaultNone400InvalidWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone400InvalidAsync(RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone400InvalidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone400Invalid(RequestOptions requestOptions) {
        get202None204NoneDefaultNone400InvalidAsync(requestOptions).block();
    }

    /** Send a 400 response with an unexpected payload {'property': 'value'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone400InvalidWithResponse(
            RequestOptions requestOptions, Context context) {
        return get202None204NoneDefaultNone400InvalidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDefaultModelA200ValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getDefaultModelA200Valid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDefaultModelA200ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getDefaultModelA200Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getDefaultModelA200ValidAsync(RequestOptions requestOptions) {
        return getDefaultModelA200ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getDefaultModelA200ValidAsync(RequestOptions requestOptions, Context context) {
        return getDefaultModelA200ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDefaultModelA200Valid(RequestOptions requestOptions) {
        return getDefaultModelA200ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDefaultModelA200ValidWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultModelA200ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDefaultModelA200NoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getDefaultModelA200None(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDefaultModelA200NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getDefaultModelA200None(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getDefaultModelA200NoneAsync(RequestOptions requestOptions) {
        return getDefaultModelA200NoneWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getDefaultModelA200NoneAsync(RequestOptions requestOptions, Context context) {
        return getDefaultModelA200NoneWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDefaultModelA200None(RequestOptions requestOptions) {
        return getDefaultModelA200NoneAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDefaultModelA200NoneWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultModelA200NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400ValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getDefaultModelA400Valid(this.client.getHost(), accept, requestOptions, context));
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getDefaultModelA400Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultModelA400ValidAsync(RequestOptions requestOptions) {
        return getDefaultModelA400ValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultModelA400ValidAsync(RequestOptions requestOptions, Context context) {
        return getDefaultModelA400ValidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultModelA400Valid(RequestOptions requestOptions) {
        getDefaultModelA400ValidAsync(requestOptions).block();
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultModelA400ValidWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultModelA400ValidWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400NoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getDefaultModelA400None(this.client.getHost(), accept, requestOptions, context));
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getDefaultModelA400None(this.client.getHost(), accept, requestOptions, context);
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultModelA400NoneAsync(RequestOptions requestOptions) {
        return getDefaultModelA400NoneWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultModelA400NoneAsync(RequestOptions requestOptions, Context context) {
        return getDefaultModelA400NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultModelA400None(RequestOptions requestOptions) {
        getDefaultModelA400NoneAsync(requestOptions).block();
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultModelA400NoneWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultModelA400NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 200 response with invalid payload: {'statusCode': '200'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200InvalidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getDefaultNone200Invalid(this.client.getHost(), requestOptions, context));
    }

    /** Send a 200 response with invalid payload: {'statusCode': '200'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.getDefaultNone200Invalid(this.client.getHost(), requestOptions, context);
    }

    /** Send a 200 response with invalid payload: {'statusCode': '200'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone200InvalidAsync(RequestOptions requestOptions) {
        return getDefaultNone200InvalidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 200 response with invalid payload: {'statusCode': '200'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone200InvalidAsync(RequestOptions requestOptions, Context context) {
        return getDefaultNone200InvalidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 200 response with invalid payload: {'statusCode': '200'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone200Invalid(RequestOptions requestOptions) {
        getDefaultNone200InvalidAsync(requestOptions).block();
    }

    /** Send a 200 response with invalid payload: {'statusCode': '200'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone200InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultNone200InvalidWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 200 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200NoneWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getDefaultNone200None(this.client.getHost(), requestOptions, context));
    }

    /** Send a 200 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200NoneWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getDefaultNone200None(this.client.getHost(), requestOptions, context);
    }

    /** Send a 200 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone200NoneAsync(RequestOptions requestOptions) {
        return getDefaultNone200NoneWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 200 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone200NoneAsync(RequestOptions requestOptions, Context context) {
        return getDefaultNone200NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 200 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone200None(RequestOptions requestOptions) {
        getDefaultNone200NoneAsync(requestOptions).block();
    }

    /** Send a 200 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone200NoneWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultNone200NoneWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400InvalidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getDefaultNone400Invalid(this.client.getHost(), requestOptions, context));
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.getDefaultNone400Invalid(this.client.getHost(), requestOptions, context);
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone400InvalidAsync(RequestOptions requestOptions) {
        return getDefaultNone400InvalidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone400InvalidAsync(RequestOptions requestOptions, Context context) {
        return getDefaultNone400InvalidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone400Invalid(RequestOptions requestOptions) {
        getDefaultNone400InvalidAsync(requestOptions).block();
    }

    /** Send a 400 response with valid payload: {'statusCode': '400'}. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone400InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultNone400InvalidWithResponseAsync(requestOptions, context).block();
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400NoneWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getDefaultNone400None(this.client.getHost(), requestOptions, context));
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400NoneWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getDefaultNone400None(this.client.getHost(), requestOptions, context);
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone400NoneAsync(RequestOptions requestOptions) {
        return getDefaultNone400NoneWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone400NoneAsync(RequestOptions requestOptions, Context context) {
        return getDefaultNone400NoneWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone400None(RequestOptions requestOptions) {
        getDefaultNone400NoneAsync(requestOptions).block();
    }

    /** Send a 400 response with no payload. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone400NoneWithResponse(RequestOptions requestOptions, Context context) {
        return getDefaultNone400NoneWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200NoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA200None(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA200None(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA200NoneAsync(RequestOptions requestOptions) {
        return get200ModelA200NoneWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA200NoneAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA200NoneWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA200None(RequestOptions requestOptions) {
        return get200ModelA200NoneAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA200NoneWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA200NoneWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200ValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA200Valid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA200Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA200ValidAsync(RequestOptions requestOptions) {
        return get200ModelA200ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA200ValidAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA200ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA200Valid(RequestOptions requestOptions) {
        return get200ModelA200ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA200ValidWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA200ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200InvalidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA200Invalid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA200Invalid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA200InvalidAsync(RequestOptions requestOptions) {
        return get200ModelA200InvalidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA200InvalidAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA200InvalidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA200Invalid(RequestOptions requestOptions) {
        return get200ModelA200InvalidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA200InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA200InvalidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400NoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA400None(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400NoneWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA400None(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA400NoneAsync(RequestOptions requestOptions) {
        return get200ModelA400NoneWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA400NoneAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA400NoneWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA400None(RequestOptions requestOptions) {
        return get200ModelA400NoneAsync(requestOptions).block();
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA400NoneWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA400NoneWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400ValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA400Valid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA400Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA400ValidAsync(RequestOptions requestOptions) {
        return get200ModelA400ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA400ValidAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA400ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA400Valid(RequestOptions requestOptions) {
        return get200ModelA400ValidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA400ValidWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA400ValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400InvalidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA400Invalid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400InvalidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA400Invalid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA400InvalidAsync(RequestOptions requestOptions) {
        return get200ModelA400InvalidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA400InvalidAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA400InvalidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA400Invalid(RequestOptions requestOptions) {
        return get200ModelA400InvalidAsync(requestOptions).block();
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA400InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA400InvalidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA202ValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.get200ModelA202Valid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA202ValidWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.get200ModelA202Valid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA202ValidAsync(RequestOptions requestOptions) {
        return get200ModelA202ValidWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> get200ModelA202ValidAsync(RequestOptions requestOptions, Context context) {
        return get200ModelA202ValidWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA202Valid(RequestOptions requestOptions) {
        return get200ModelA202ValidAsync(requestOptions).block();
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA202ValidWithResponse(RequestOptions requestOptions, Context context) {
        return get200ModelA202ValidWithResponseAsync(requestOptions, context).block();
    }
}
