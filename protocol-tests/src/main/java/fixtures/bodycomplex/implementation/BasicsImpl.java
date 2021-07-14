package fixtures.bodycomplex.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
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

/** An instance of this class provides access to all the operations defined in Basics. */
public final class BasicsImpl {
    /** The proxy service used to perform REST calls. */
    private final BasicsService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of BasicsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    BasicsImpl(AutoRestComplexTestServiceImpl client) {
        this.service = RestProxy.create(BasicsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceBasics to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface BasicsService {
        @Get("/complex/basic/valid")
        Mono<Response<BinaryData>> getValid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/complex/basic/valid")
        Mono<Response<Void>> putValid(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @BodyParam("application/json") BinaryData complexBody,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/complex/basic/invalid")
        Mono<Response<BinaryData>> getInvalid(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/complex/basic/empty")
        Mono<Response<BinaryData>> getEmpty(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/complex/basic/null")
        Mono<Response<BinaryData>> getNull(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/complex/basic/notprovided")
        Mono<Response<BinaryData>> getNotProvided(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getValid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getValid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getValidAsync(RequestOptions requestOptions) {
        return getValidWithResponseAsync(requestOptions)
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
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getValidAsync(RequestOptions requestOptions, Context context) {
        return getValidWithResponseAsync(requestOptions, context)
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
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getValid(RequestOptions requestOptions) {
        return getValidAsync(requestOptions).block();
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions, Context context) {
        return getValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(BinaryData complexBody, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.putValid(
                                this.client.getHost(),
                                this.client.getApiVersion(),
                                complexBody,
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.putValid(
                this.client.getHost(), this.client.getApiVersion(), complexBody, accept, requestOptions, context);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(BinaryData complexBody, RequestOptions requestOptions) {
        return putValidWithResponseAsync(complexBody, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return putValidWithResponseAsync(complexBody, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(BinaryData complexBody, RequestOptions requestOptions) {
        putValidAsync(complexBody, requestOptions).block();
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return putValidWithResponseAsync(complexBody, requestOptions, context).block();
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getInvalidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getInvalid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getInvalidWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getInvalid(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getInvalidAsync(RequestOptions requestOptions) {
        return getInvalidWithResponseAsync(requestOptions)
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
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getInvalidAsync(RequestOptions requestOptions, Context context) {
        return getInvalidWithResponseAsync(requestOptions, context)
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
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getInvalid(RequestOptions requestOptions) {
        return getInvalidAsync(requestOptions).block();
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getInvalidWithResponse(RequestOptions requestOptions, Context context) {
        return getInvalidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getEmpty(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getEmpty(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getEmptyAsync(RequestOptions requestOptions) {
        return getEmptyWithResponseAsync(requestOptions)
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
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getEmptyAsync(RequestOptions requestOptions, Context context) {
        return getEmptyWithResponseAsync(requestOptions, context)
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
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getEmpty(RequestOptions requestOptions) {
        return getEmptyAsync(requestOptions).block();
    }

    /**
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return getEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getNull(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNullAsync(RequestOptions requestOptions) {
        return getNullWithResponseAsync(requestOptions)
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
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNullAsync(RequestOptions requestOptions, Context context) {
        return getNullWithResponseAsync(requestOptions, context)
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
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNull(RequestOptions requestOptions) {
        return getNullAsync(requestOptions).block();
    }

    /**
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNullWithResponse(RequestOptions requestOptions, Context context) {
        return getNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getNotProvided(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getNotProvided(this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNotProvidedAsync(RequestOptions requestOptions) {
        return getNotProvidedWithResponseAsync(requestOptions)
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
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNotProvidedAsync(RequestOptions requestOptions, Context context) {
        return getNotProvidedWithResponseAsync(requestOptions, context)
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
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNotProvided(RequestOptions requestOptions) {
        return getNotProvidedAsync(requestOptions).block();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNotProvidedWithResponse(RequestOptions requestOptions, Context context) {
        return getNotProvidedWithResponseAsync(requestOptions, context).block();
    }
}
