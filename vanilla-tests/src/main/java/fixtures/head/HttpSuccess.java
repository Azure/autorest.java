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
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpSuccess.
 */
public final class HttpSuccess {
    /**
     * The proxy service used to perform REST calls.
     */
    private final HttpSuccessService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestHeadTestService client;

    /**
     * Initializes an instance of HttpSuccess.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    HttpSuccess(AutoRestHeadTestService client) {
        this.service = RestProxy.create(HttpSuccessService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHeadTestServiceHttpSuccess to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHeadTestServ")
    private interface HttpSuccessService {
        @Head("/http/success/200")
        @ExpectedResponses({200, 404})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Boolean>> head200(@HostParam("$host") String host, Context context);

        @Head("/http/success/204")
        @ExpectedResponses({204, 404})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Boolean>> head204(@HostParam("$host") String host, Context context);

        @Head("/http/success/404")
        @ExpectedResponses({204, 404})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Boolean>> head404(@HostParam("$host") String host, Context context);
    }

    /**
     * Return 200 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> head200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head200(this.client.getHost(), context));
    }

    /**
     * Return 200 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head200Async() {
        return head200WithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Return 200 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean head200() {
        return head200Async().block();
    }

    /**
     * Return 204 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> head204WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head204(this.client.getHost(), context));
    }

    /**
     * Return 204 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head204Async() {
        return head204WithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Return 204 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean head204() {
        return head204Async().block();
    }

    /**
     * Return 404 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> head404WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head404(this.client.getHost(), context));
    }

    /**
     * Return 404 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head404Async() {
        return head404WithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Return 404 status code if successful.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean head404() {
        return head404Async().block();
    }
}
