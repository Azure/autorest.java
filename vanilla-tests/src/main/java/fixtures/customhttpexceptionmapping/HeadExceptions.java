package fixtures.customhttpexceptionmapping;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ResourceExistsException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HeadExceptions. */
public final class HeadExceptions {
    /** The proxy service used to perform REST calls. */
    private final HeadExceptionsService service;

    /** The service client containing this operation class. */
    private final AutoRestHeadExceptionTestService client;

    /**
     * Initializes an instance of HeadExceptions.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HeadExceptions(AutoRestHeadExceptionTestService client) {
        this.service =
                RestProxy.create(HeadExceptionsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHeadExceptionTestServiceHeadExceptions to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHeadExceptio")
    public interface HeadExceptionsService {
        @Head("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ResourceExistsException.class,
                code = {404})
        @UnexpectedResponseExceptionType(ResourceNotFoundException.class)
        Mono<Response<Void>> head200(@HostParam("$host") String host, Context context);

        @Head("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ResourceExistsException.class,
                code = {404})
        @UnexpectedResponseExceptionType(ResourceNotFoundException.class)
        Mono<Response<Void>> head204(@HostParam("$host") String host, Context context);

        @Head("/http/success/404")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ResourceExistsException.class,
                code = {404})
        @UnexpectedResponseExceptionType(ResourceNotFoundException.class)
        Mono<Response<Void>> head404(@HostParam("$host") String host, Context context);
    }

    /**
     * Return 200 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head200(this.client.getHost(), context));
    }

    /**
     * Return 200 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200Async() {
        return head200WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 200 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head200() {
        head200Async().block();
    }

    /**
     * Return 204 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head204(this.client.getHost(), context));
    }

    /**
     * Return 204 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204Async() {
        return head204WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 204 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head204() {
        head204Async().block();
    }

    /**
     * Return 404 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head404WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head404(this.client.getHost(), context));
    }

    /**
     * Return 404 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head404Async() {
        return head404WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 404 status code if successful.
     *
     * @throws ResourceNotFoundException thrown if the request is rejected by server.
     * @throws ResourceExistsException thrown if the request is rejected by server on status code 404.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head404() {
        head404Async().block();
    }
}
