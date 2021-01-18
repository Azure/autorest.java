package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
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

/** An instance of this class provides access to all the operations defined in ApiVersionDefaults. */
public final class ApiVersionDefaults {
    /** The proxy service used to perform REST calls. */
    private final ApiVersionDefaultsService service;

    /** The service client containing this operation class. */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of ApiVersionDefaults.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ApiVersionDefaults(AutoRestAzureSpecialParametersTestClient client) {
        this.service =
                RestProxy.create(
                        ApiVersionDefaultsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientApiVersionDefaults to be used
     * by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecial")
    private interface ApiVersionDefaultsService {
        @Get("/azurespecials/apiVersion/method/string/none/query/global/2015-07-01-preview")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodGlobalValid(
                @HostParam("$host") String host, @QueryParam("api-version") String apiVersion, Context context);

        @Get("/azurespecials/apiVersion/method/string/none/query/globalNotProvided/2015-07-01-preview")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getMethodGlobalNotProvidedValid(
                @HostParam("$host") String host, @QueryParam("api-version") String apiVersion, Context context);

        @Get("/azurespecials/apiVersion/path/string/none/query/global/2015-07-01-preview")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getPathGlobalValid(
                @HostParam("$host") String host, @QueryParam("api-version") String apiVersion, Context context);

        @Get("/azurespecials/apiVersion/swagger/string/none/query/global/2015-07-01-preview")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getSwaggerGlobalValid(
                @HostParam("$host") String host, @QueryParam("api-version") String apiVersion, Context context);
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodGlobalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context -> service.getMethodGlobalValid(this.client.getHost(), this.client.getApiVersion(), context));
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodGlobalValidAsync() {
        return getMethodGlobalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodGlobalValid() {
        getMethodGlobalValidAsync().block();
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getMethodGlobalNotProvidedValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.getMethodGlobalNotProvidedValid(
                                this.client.getHost(), this.client.getApiVersion(), context));
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getMethodGlobalNotProvidedValidAsync() {
        return getMethodGlobalNotProvidedValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getMethodGlobalNotProvidedValid() {
        getMethodGlobalNotProvidedValidAsync().block();
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getPathGlobalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context -> service.getPathGlobalValid(this.client.getHost(), this.client.getApiVersion(), context));
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getPathGlobalValidAsync() {
        return getPathGlobalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getPathGlobalValid() {
        getPathGlobalValidAsync().block();
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getSwaggerGlobalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context -> service.getSwaggerGlobalValid(this.client.getHost(), this.client.getApiVersion(), context));
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return method with api-version modeled in global settings.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getSwaggerGlobalValidAsync() {
        return getSwaggerGlobalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * GET method with api-version modeled in global settings.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getSwaggerGlobalValid() {
        getSwaggerGlobalValidAsync().block();
    }
}
