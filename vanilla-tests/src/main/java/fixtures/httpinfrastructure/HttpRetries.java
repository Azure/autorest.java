package fixtures.httpinfrastructure;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
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

/** An instance of this class provides access to all the operations defined in HttpRetries. */
public final class HttpRetries {
    /** The proxy service used to perform REST calls. */
    private final HttpRetriesService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpRetries.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpRetries(AutoRestHttpInfrastructureTestService client) {
        this.service =
                RestProxy.create(HttpRetriesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpRetries to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpRetriesService {
        @Head("/http/retry/408")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head408(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/http/retry/500")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put500(
                @HostParam("$host") String host,
                @BodyParam("application/json") Boolean booleanValue,
                @HeaderParam("Accept") String accept,
                Context context);

        @Patch("/http/retry/500")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch500(
                @HostParam("$host") String host,
                @BodyParam("application/json") Boolean booleanValue,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/http/retry/502")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get502(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/http/retry/503")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post503(
                @HostParam("$host") String host,
                @BodyParam("application/json") Boolean booleanValue,
                @HeaderParam("Accept") String accept,
                Context context);

        @Delete("/http/retry/503")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete503(
                @HostParam("$host") String host,
                @BodyParam("application/json") Boolean booleanValue,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/http/retry/504")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put504(
                @HostParam("$host") String host,
                @BodyParam("application/json") Boolean booleanValue,
                @HeaderParam("Accept") String accept,
                Context context);

        @Patch("/http/retry/504")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch504(
                @HostParam("$host") String host,
                @BodyParam("application/json") Boolean booleanValue,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head408WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.head408(this.client.getHost(), accept, context));
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head408Async() {
        return head408WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head408() {
        head408Async().block();
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put500WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put500(this.client.getHost(), booleanValue, accept, context));
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put500Async() {
        return put500WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put500() {
        put500Async().block();
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch500WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.patch500(this.client.getHost(), booleanValue, accept, context));
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch500Async() {
        return patch500WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch500() {
        patch500Async().block();
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get502WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get502(this.client.getHost(), accept, context));
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get502Async() {
        return get502WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get502() {
        get502Async().block();
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post503WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post503(this.client.getHost(), booleanValue, accept, context));
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post503Async() {
        return post503WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post503() {
        post503Async().block();
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete503WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete503(this.client.getHost(), booleanValue, accept, context));
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete503Async() {
        return delete503WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete503() {
        delete503Async().block();
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put504WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put504(this.client.getHost(), booleanValue, accept, context));
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put504Async() {
        return put504WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put504() {
        put504Async().block();
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch504WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.patch504(this.client.getHost(), booleanValue, accept, context));
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch504Async() {
        return patch504WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch504() {
        patch504Async().block();
    }
}
