package fixtures.custombaseuri;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.custombaseuri.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Paths.
 */
public final class Paths {
    /**
     * The proxy service used to perform REST calls.
     */
    private PathsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestParameterizedHostTestClient client;

    /**
     * Initializes an instance of Paths.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Paths(AutoRestParameterizedHostTestClient client) {
        this.service = RestProxy.create(PathsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestParameterizedHostTestClientPaths to be used by the proxy service
     * to perform REST calls.
     */
    @Host("http://{accountName}{host}")
    @ServiceInterface(name = "AutoRestParameterizedHostTestClientPaths")
    private interface PathsService {
        @Get("/customuri")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getEmpty(@HostParam("accountName") String accountName, @HostParam("host") String host, Context context);
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param accountName simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync(String accountName) {
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getEmpty(accountName, this.client.getHost(), context));
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param accountName simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String accountName) {
        return getEmptyWithResponseAsync(accountName)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param accountName simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty(String accountName) {
        getEmptyAsync(accountName).block();
    }
}
