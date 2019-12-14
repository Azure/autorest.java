package fixtures.httpinfrastructure;

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
import fixtures.httpinfrastructure.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpFailures.
 */
public final class HttpFailures {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpFailuresService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpFailures.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpFailures(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpFailuresService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpFailures to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceHttpFailures")
    private interface HttpFailuresService {
        @Get("/http/failure/emptybody/error")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getEmptyError(@HostParam("$host") String host);

        @Get("/http/failure/nomodel/error")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Boolean>> getNoModelError(@HostParam("$host") String host);

        @Get("/http/failure/nomodel/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Boolean>> getNoModelEmpty(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getEmptyErrorWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmptyError(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getEmptyErrorAsync() {
        return getEmptyErrorWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getEmptyError() {
        return getEmptyErrorAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getNoModelErrorWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNoModelError(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNoModelErrorAsync() {
        return getNoModelErrorWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getNoModelError() {
        return getNoModelErrorAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getNoModelEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNoModelEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNoModelEmptyAsync() {
        return getNoModelEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getNoModelEmpty() {
        return getNoModelEmptyAsync().block();
    }
}
