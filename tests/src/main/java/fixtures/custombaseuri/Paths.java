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
import com.azure.core.implementation.RestProxy;
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
    public Paths(AutoRestParameterizedHostTestClient client) {
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
        Mono<Response<Void>> getEmpty(@HostParam("accountName") String AccountName, @HostParam("host") String Host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync() {
        return service.getEmpty(this.client.getAccountName(), this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty() {
        getEmptyAsync().block();
    }
}
