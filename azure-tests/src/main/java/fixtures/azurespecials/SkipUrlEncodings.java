package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.azurespecials.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in SkipUrlEncodings. */
public final class SkipUrlEncodings {
    /** The proxy service used to perform REST calls. */
    private final SkipUrlEncodingsService service;

    /** The service client containing this operation class. */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of SkipUrlEncodings.
     *
     * @param client the instance of the service client containing this operation class.
     */
    SkipUrlEncodings(AutoRestAzureSpecialParametersTestClient client) {
        this.service =
                RestProxy.create(
                        SkipUrlEncodingsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientSkipUrlEncodings to be used
     * by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecial")
    private interface SkipUrlEncodingsService {
        @Get("/azurespecials/skipUrlEncoding/method/path/valid/{unencodedPathParam}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodPathValid(
                @HostParam("$host") String host,
                @PathParam(value = "unencodedPathParam", encoded = true) String unencodedPathParam,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/skipUrlEncoding/path/path/valid/{unencodedPathParam}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getPathValid(
                @HostParam("$host") String host,
                @PathParam(value = "unencodedPathParam", encoded = true) String unencodedPathParam,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/skipUrlEncoding/swagger/path/valid/{unencodedPathParam}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getSwaggerPathValid(
                @HostParam("$host") String host,
                @PathParam(value = "unencodedPathParam", encoded = true) String unencodedPathParam,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/skipUrlEncoding/method/query/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodQueryValid(
                @HostParam("$host") String host,
                @QueryParam(value = "q1", encoded = true) String q1,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/skipUrlEncoding/method/query/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodQueryNull(
                @HostParam("$host") String host,
                @QueryParam(value = "q1", encoded = true) String q1,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/skipUrlEncoding/path/query/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getPathQueryValid(
                @HostParam("$host") String host,
                @QueryParam(value = "q1", encoded = true) String q1,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/skipUrlEncoding/swagger/query/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getSwaggerQueryValid(
                @HostParam("$host") String host,
                @QueryParam(value = "q1", encoded = true) String q1,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodPathValidWithResponseAsync(String unencodedPathParam) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (unencodedPathParam == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter unencodedPathParam is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getMethodPathValid(this.client.getHost(), unencodedPathParam, accept, context));
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodPathValidWithResponseAsync(String unencodedPathParam, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (unencodedPathParam == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter unencodedPathParam is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getMethodPathValid(this.client.getHost(), unencodedPathParam, accept, context);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodPathValidAsync(String unencodedPathParam) {
        return getMethodPathValidWithResponseAsync(unencodedPathParam).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodPathValidAsync(String unencodedPathParam, Context context) {
        return getMethodPathValidWithResponseAsync(unencodedPathParam, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodPathValid(String unencodedPathParam) {
        getMethodPathValidAsync(unencodedPathParam).block();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getMethodPathValidWithResponse(String unencodedPathParam, Context context) {
        return getMethodPathValidWithResponseAsync(unencodedPathParam, context).block();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathValidWithResponseAsync(String unencodedPathParam) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (unencodedPathParam == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter unencodedPathParam is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getPathValid(this.client.getHost(), unencodedPathParam, accept, context));
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathValidWithResponseAsync(String unencodedPathParam, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (unencodedPathParam == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter unencodedPathParam is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getPathValid(this.client.getHost(), unencodedPathParam, accept, context);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathValidAsync(String unencodedPathParam) {
        return getPathValidWithResponseAsync(unencodedPathParam).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathValidAsync(String unencodedPathParam, Context context) {
        return getPathValidWithResponseAsync(unencodedPathParam, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getPathValid(String unencodedPathParam) {
        getPathValidAsync(unencodedPathParam).block();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param unencodedPathParam Unencoded path parameter with value 'path1/path2/path3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getPathValidWithResponse(String unencodedPathParam, Context context) {
        return getPathValidWithResponseAsync(unencodedPathParam, context).block();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerPathValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String unencodedPathParam = "path1/path2/path3";
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getSwaggerPathValid(this.client.getHost(), unencodedPathParam, accept, context));
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerPathValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String unencodedPathParam = "path1/path2/path3";
        final String accept = "application/json";
        return service.getSwaggerPathValid(this.client.getHost(), unencodedPathParam, accept, context);
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerPathValidAsync() {
        return getSwaggerPathValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerPathValidAsync(Context context) {
        return getSwaggerPathValidWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getSwaggerPathValid() {
        getSwaggerPathValidAsync().block();
    }

    /**
     * Get method with unencoded path parameter with value 'path1/path2/path3'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded path parameter with value 'path1/path2/path3' along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getSwaggerPathValidWithResponse(Context context) {
        return getSwaggerPathValidWithResponseAsync(context).block();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodQueryValidWithResponseAsync(String q1) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (q1 == null) {
            return Mono.error(new IllegalArgumentException("Parameter q1 is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getMethodQueryValid(this.client.getHost(), q1, accept, context));
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodQueryValidWithResponseAsync(String q1, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (q1 == null) {
            return Mono.error(new IllegalArgumentException("Parameter q1 is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getMethodQueryValid(this.client.getHost(), q1, accept, context);
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodQueryValidAsync(String q1) {
        return getMethodQueryValidWithResponseAsync(q1).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodQueryValidAsync(String q1, Context context) {
        return getMethodQueryValidWithResponseAsync(q1, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodQueryValid(String q1) {
        getMethodQueryValidAsync(q1).block();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getMethodQueryValidWithResponse(String q1, Context context) {
        return getMethodQueryValidWithResponseAsync(q1, context).block();
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value null along with {@link Response} on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodQueryNullWithResponseAsync(String q1) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getMethodQueryNull(this.client.getHost(), q1, accept, context));
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value null along with {@link Response} on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodQueryNullWithResponseAsync(String q1, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getMethodQueryNull(this.client.getHost(), q1, accept, context);
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodQueryNullAsync(String q1) {
        return getMethodQueryNullWithResponseAsync(q1).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodQueryNullAsync() {
        final String q1 = null;
        return getMethodQueryNullWithResponseAsync(q1).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodQueryNullAsync(String q1, Context context) {
        return getMethodQueryNullWithResponseAsync(q1, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodQueryNull(String q1) {
        getMethodQueryNullAsync(q1).block();
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodQueryNull() {
        final String q1 = null;
        getMethodQueryNullAsync(q1).block();
    }

    /**
     * Get method with unencoded query parameter with value null.
     *
     * @param q1 Unencoded query parameter with value null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value null along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getMethodQueryNullWithResponse(String q1, Context context) {
        return getMethodQueryNullWithResponseAsync(q1, context).block();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathQueryValidWithResponseAsync(String q1) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (q1 == null) {
            return Mono.error(new IllegalArgumentException("Parameter q1 is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getPathQueryValid(this.client.getHost(), q1, accept, context));
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathQueryValidWithResponseAsync(String q1, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (q1 == null) {
            return Mono.error(new IllegalArgumentException("Parameter q1 is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getPathQueryValid(this.client.getHost(), q1, accept, context);
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathQueryValidAsync(String q1) {
        return getPathQueryValidWithResponseAsync(q1).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathQueryValidAsync(String q1, Context context) {
        return getPathQueryValidWithResponseAsync(q1, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getPathQueryValid(String q1) {
        getPathQueryValidAsync(q1).block();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param q1 Unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getPathQueryValidWithResponse(String q1, Context context) {
        return getPathQueryValidWithResponseAsync(q1, context).block();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerQueryValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String q1 = "value1&q2=value2&q3=value3";
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getSwaggerQueryValid(this.client.getHost(), q1, accept, context));
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerQueryValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String q1 = "value1&q2=value2&q3=value3";
        final String accept = "application/json";
        return service.getSwaggerQueryValid(this.client.getHost(), q1, accept, context);
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerQueryValidAsync() {
        return getSwaggerQueryValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' on successful
     *     completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerQueryValidAsync(Context context) {
        return getSwaggerQueryValidWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getSwaggerQueryValid() {
        getSwaggerQueryValidAsync().block();
    }

    /**
     * Get method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with unencoded query parameter with value 'value1&amp;q2=value2&amp;q3=value3' along with {@link
     *     Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getSwaggerQueryValidWithResponse(Context context) {
        return getSwaggerQueryValidWithResponseAsync(context).block();
    }
}
