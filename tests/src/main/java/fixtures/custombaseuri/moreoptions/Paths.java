package fixtures.custombaseuri.moreoptions;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
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
import fixtures.custombaseuri.moreoptions.models.ErrorException;
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
    private AutoRestParameterizedCustomHostTestClient client;

    /**
     * Initializes an instance of Paths.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Paths(AutoRestParameterizedCustomHostTestClient client) {
        this.service = RestProxy.create(PathsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestParameterizedCustomHostTestClientPaths to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{vault}{secret}{dnsSuffix}")
    @ServiceInterface(name = "AutoRestParameterizedCustomHostTestClientPaths")
    private interface PathsService {
        @Get("/customuri/{subscriptionId}/{keyName}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getEmpty(@HostParam("vault") String vault, @HostParam("secret") String secret, @HostParam("dnsSuffix") String dnsSuffix, @PathParam("keyName") String keyName, @PathParam("subscriptionId") String subscriptionId, @QueryParam("keyVersion") String keyVersion, Context context);
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault simple string.
     * @param secret simple string.
     * @param keyName 
     * @param keyVersion 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync(String vault, String secret, String keyName, String keyVersion) {
        if (vault == null) {
            throw new IllegalArgumentException("Parameter vault is required and cannot be null.");
        }
        if (secret == null) {
            throw new IllegalArgumentException("Parameter secret is required and cannot be null.");
        }
        if (this.client.getDnsSuffix() == null) {
            throw new IllegalArgumentException("Parameter this.client.getDnsSuffix() is required and cannot be null.");
        }
        if (keyName == null) {
            throw new IllegalArgumentException("Parameter keyName is required and cannot be null.");
        }
        if (this.client.getSubscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.getSubscriptionId() is required and cannot be null.");
        }
        return FluxUtil.withContext(context -> service.getEmpty(vault, secret, this.client.getDnsSuffix(), keyName, this.client.getSubscriptionId(), keyVersion, context));
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault simple string.
     * @param secret simple string.
     * @param keyName 
     * @param keyVersion 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String vault, String secret, String keyName, String keyVersion) {
        return getEmptyWithResponseAsync(vault, secret, keyName, keyVersion)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault simple string.
     * @param secret simple string.
     * @param keyName 
     * @param keyVersion 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty(String vault, String secret, String keyName, String keyVersion) {
        getEmptyAsync(vault, secret, keyName, keyVersion).block();
    }
}
