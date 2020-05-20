package fixtures.httpinfrastructure;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.httpinfrastructure.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HttpServerFailures. */
public final class HttpServerFailures {
    /** The proxy service used to perform REST calls. */
    private final HttpServerFailuresService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpServerFailures.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpServerFailures(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpServerFailuresService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpServerFailures to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpServerFailuresService {
        @Head("/http/failure/server/501")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head501(@HostParam("$host") String host, Context context);

        @Get("/http/failure/server/501")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get501(@HostParam("$host") String host, Context context);

        @Post("/http/failure/server/505")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post505(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Delete("/http/failure/server/505")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete505(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head501WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head501(this.client.getHost(), context));
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head501Async() {
        return head501WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head501() {
        head501Async().block();
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get501WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get501(this.client.getHost(), context));
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get501Async() {
        return get501WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get501() {
        get501Async().block();
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post505WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post505(this.client.getHost(), booleanValue, context));
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post505Async() {
        return post505WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post505() {
        post505Async().block();
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete505WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.delete505(this.client.getHost(), booleanValue, context));
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete505Async() {
        return delete505WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete505() {
        delete505Async().block();
    }
}
