package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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

/** An instance of this class provides access to all the operations defined in ApiVersionLocals. */
public final class ApiVersionLocals {
    /** The proxy service used to perform REST calls. */
    private final ApiVersionLocalsService service;

    /** The service client containing this operation class. */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of ApiVersionLocals.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ApiVersionLocals(AutoRestAzureSpecialParametersTestClient client) {
        this.service =
                RestProxy.create(
                        ApiVersionLocalsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientApiVersionLocals to be used
     * by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecial")
    private interface ApiVersionLocalsService {
        @Get("/azurespecials/apiVersion/method/string/none/query/local/2.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodLocalValid(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/apiVersion/method/string/none/query/local/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodLocalNull(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/apiVersion/path/string/none/query/local/2.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getPathLocalValid(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/azurespecials/apiVersion/swagger/string/none/query/local/2.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getSwaggerLocalValid(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodLocalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String apiVersion = "2.0";
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getMethodLocalValid(this.client.getHost(), apiVersion, accept, context));
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodLocalValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String apiVersion = "2.0";
        final String accept = "application/json";
        return service.getMethodLocalValid(this.client.getHost(), apiVersion, accept, context);
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodLocalValidAsync() {
        return getMethodLocalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodLocalValidAsync(Context context) {
        return getMethodLocalValidWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodLocalValid() {
        getMethodLocalValidAsync().block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getMethodLocalValidWithResponse(Context context) {
        return getMethodLocalValidWithResponseAsync(context).block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @param apiVersion This should appear as a method parameter, use value null, this should result in no serialized
     *     parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodLocalNullWithResponseAsync(String apiVersion) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getMethodLocalNull(this.client.getHost(), apiVersion, accept, context));
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @param apiVersion This should appear as a method parameter, use value null, this should result in no serialized
     *     parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodLocalNullWithResponseAsync(String apiVersion, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getMethodLocalNull(this.client.getHost(), apiVersion, accept, context);
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @param apiVersion This should appear as a method parameter, use value null, this should result in no serialized
     *     parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodLocalNullAsync(String apiVersion) {
        return getMethodLocalNullWithResponseAsync(apiVersion).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodLocalNullAsync() {
        final String apiVersion = null;
        return getMethodLocalNullWithResponseAsync(apiVersion).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @param apiVersion This should appear as a method parameter, use value null, this should result in no serialized
     *     parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodLocalNullAsync(String apiVersion, Context context) {
        return getMethodLocalNullWithResponseAsync(apiVersion, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @param apiVersion This should appear as a method parameter, use value null, this should result in no serialized
     *     parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodLocalNull(String apiVersion) {
        getMethodLocalNullAsync(apiVersion).block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodLocalNull() {
        final String apiVersion = null;
        getMethodLocalNullAsync(apiVersion).block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = null to succeed.
     *
     * @param apiVersion This should appear as a method parameter, use value null, this should result in no serialized
     *     parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getMethodLocalNullWithResponse(String apiVersion, Context context) {
        return getMethodLocalNullWithResponseAsync(apiVersion, context).block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathLocalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String apiVersion = "2.0";
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getPathLocalValid(this.client.getHost(), apiVersion, accept, context));
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathLocalValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String apiVersion = "2.0";
        final String accept = "application/json";
        return service.getPathLocalValid(this.client.getHost(), apiVersion, accept, context);
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathLocalValidAsync() {
        return getPathLocalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathLocalValidAsync(Context context) {
        return getPathLocalValidWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getPathLocalValid() {
        getPathLocalValidAsync().block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getPathLocalValidWithResponse(Context context) {
        return getPathLocalValidWithResponseAsync(context).block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerLocalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String apiVersion = "2.0";
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getSwaggerLocalValid(this.client.getHost(), apiVersion, accept, context));
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerLocalValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String apiVersion = "2.0";
        final String accept = "application/json";
        return service.getSwaggerLocalValid(this.client.getHost(), apiVersion, accept, context);
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerLocalValidAsync() {
        return getSwaggerLocalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerLocalValidAsync(Context context) {
        return getSwaggerLocalValidWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getSwaggerLocalValid() {
        getSwaggerLocalValidAsync().block();
    }

    /**
     * Get method with api-version modeled in the method. pass in api-version = '2.0' to succeed.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in the method.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getSwaggerLocalValidWithResponse(Context context) {
        return getSwaggerLocalValidWithResponseAsync(context).block();
    }
}
