package fixtures.headexceptions;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.implementation.RestProxy;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HeadExceptions.
 */
public final class HeadExceptions {
    /**
     * The proxy service used to perform REST calls.
     */
    private HeadExceptionsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHeadExceptionTestService client;

    /**
     * Initializes an instance of HeadExceptions.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HeadExceptions(AutoRestHeadExceptionTestService client) {
        this.service = RestProxy.create(HeadExceptionsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHeadExceptionTestServiceHeadExceptions to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHeadExceptionTestServiceHeadExceptions")
    private interface HeadExceptionsService {
        @Head("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> head200(@HostParam("$host") String Host);

        @Head("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> head204(@HostParam("$host") String Host);

        @Head("/http/success/404")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> head404(@HostParam("$host") String Host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync() {
        return service.head200(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200Async() {
        return head200WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head200() {
        head200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync() {
        return service.head204(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204Async() {
        return head204WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head204() {
        head204Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head404WithResponseAsync() {
        return service.head404(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head404Async() {
        return head404WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head404() {
        head404Async().block();
    }
}
