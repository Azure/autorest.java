package fixtures.httpinfrastructure;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import fixtures.httpinfrastructure.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpServerFailures.
 */
public final class HttpServerFailures {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpServerFailuresService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpServerFailures.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpServerFailures(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpServerFailuresService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpServerFailures to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceHttpServerFailures")
    private interface HttpServerFailuresService {
        @Head("/http/failure/server/501")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head501(@HostParam("$host") String host);

        @Get("/http/failure/server/501")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get501(@HostParam("$host") String host);

        @Post("/http/failure/server/505")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post505(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Delete("/http/failure/server/505")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete505(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head501WithResponseAsync() {
        return service.head501(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head501Async() {
        return head501WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head501() {
        head501Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get501WithResponseAsync() {
        return service.get501(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get501Async() {
        return get501WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get501() {
        get501Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post505WithResponseAsync() {
        final boolean booleanValue = true;
        return service.post505(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post505Async() {
        return post505WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post505() {
        post505Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete505WithResponseAsync() {
        final boolean booleanValue = true;
        return service.delete505(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete505Async() {
        return delete505WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete505() {
        delete505Async().block();
    }
}
