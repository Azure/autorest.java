package fixtures.bodycomplex;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.bodycomplex.models.MyBaseType;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Flattencomplexs.
 */
public final class Flattencomplexs {
    /**
     * The proxy service used to perform REST calls.
     */
    private FlattencomplexsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Flattencomplexs.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Flattencomplexs(AutoRestComplexTestService client) {
        this.service = RestProxy.create(FlattencomplexsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServiceFlattencomplexs to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServiceFlattencomplexs")
    private interface FlattencomplexsService {
        @Get("/complex/flatten/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyBaseType>> getValid(@HostParam("$host") String host);
    }

    /**
     * MISSING·OPERATION-DESCRIPTION.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyBaseType>> getValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getValid(this.client.getHost());
    }

    /**
     * MISSING·OPERATION-DESCRIPTION.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyBaseType> getValidAsync() {
        return getValidWithResponseAsync()
            .flatMap((SimpleResponse<MyBaseType> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * MISSING·OPERATION-DESCRIPTION.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyBaseType getValid() {
        return getValidAsync().block();
    }
}
