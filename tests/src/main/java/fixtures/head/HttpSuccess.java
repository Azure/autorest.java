package fixtures.head;

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
import com.azure.core.http.rest.RestProxy;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpSuccess.
 */
public final class HttpSuccess {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpSuccessService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHeadTestService client;

    /**
     * Initializes an instance of HttpSuccess.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpSuccess(AutoRestHeadTestService client) {
        this.service = RestProxy.create(HttpSuccessService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHeadTestServiceHttpSuccess to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHeadTestServiceHttpSuccess")
    private interface HttpSuccessService {
        @Head("/http/success/200")
        @ExpectedResponses({200, 404})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> head200(@HostParam("$host") String host);

        @Head("/http/success/204")
        @ExpectedResponses({204, 404})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> head204(@HostParam("$host") String host);

        @Head("/http/success/404")
        @ExpectedResponses({204, 404})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> head404(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
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
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
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
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
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
