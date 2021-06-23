package fixtures.custombaseuri.implementation;

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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Paths. */
public final class PathsImpl {
    /** The proxy service used to perform REST calls. */
    private final PathsService service;

    /** The service client containing this operation class. */
    private final AutoRestParameterizedHostTestClientImpl client;

    /**
     * Initializes an instance of PathsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PathsImpl(AutoRestParameterizedHostTestClientImpl client) {
        this.service = RestProxy.create(PathsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestParameterizedHostTestClientPaths to be used by the proxy
     * service to perform REST calls.
     */
    @Host("http://{accountName}{host}")
    @ServiceInterface(name = "AutoRestParameterize")
    private interface PathsService {
        @Get("/customuri")
        Mono<Response<Void>> getEmpty(
                @HostParam("accountName") String accountName,
                @HostParam("host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync(String accountName, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getEmpty(accountName, this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync(
            String accountName, RequestOptions requestOptions, Context context) {
        final String accept = "application/json";
        return service.getEmpty(accountName, this.client.getHost(), accept, requestOptions, context);
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String accountName, RequestOptions requestOptions) {
        return getEmptyWithResponseAsync(accountName, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String accountName, RequestOptions requestOptions, Context context) {
        return getEmptyWithResponseAsync(accountName, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty(String accountName, RequestOptions requestOptions) {
        getEmptyAsync(accountName, requestOptions).block();
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @return a DynamicRequest where customizations can be made before sent to the service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getEmptyWithResponse(String accountName, RequestOptions requestOptions, Context context) {
        return getEmptyWithResponseAsync(accountName, requestOptions, context).block();
    }
}
