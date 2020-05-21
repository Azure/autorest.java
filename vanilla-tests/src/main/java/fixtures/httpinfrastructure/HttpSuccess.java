package fixtures.httpinfrastructure;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.httpinfrastructure.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in HttpSuccess. */
public final class HttpSuccess {
    /** The proxy service used to perform REST calls. */
    private final HttpSuccessService service;

    /** The service client containing this operation class. */
    private final AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpSuccess.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HttpSuccess(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpSuccessService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestHttpInfrastructureTestServiceHttpSuccess to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpSuccessService {
        @Head("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head200(@HostParam("$host") String host, Context context);

        @Get("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> get200(@HostParam("$host") String host, Context context);

        @Put("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put200(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Patch("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch200(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Post("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post200(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Delete("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete200(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Put("/http/success/201")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put201(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Post("/http/success/201")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post201(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Put("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put202(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Patch("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch202(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Post("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post202(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Delete("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete202(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Head("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head204(@HostParam("$host") String host, Context context);

        @Put("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put204(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Patch("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch204(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Post("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post204(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Delete("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete204(
                @HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Head("/http/success/404")
        @ExpectedResponses({204, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> head404(@HostParam("$host") String host, Context context);
    }

    /**
     * Return 200 status code if successful.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
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
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200Async() {
        return head200WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 200 status code if successful.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head200() {
        head200Async().block();
    }

    /**
     * Get 200 success.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> get200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get200(this.client.getHost(), context));
    }

    /**
     * Get 200 success.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> get200Async() {
        return get200WithResponseAsync()
                .flatMap(
                        (SimpleResponse<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get 200 success.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean get200() {
        Boolean value = get200Async().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.put200(this.client.getHost(), booleanValue, context));
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put200Async() {
        return put200WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put200() {
        put200Async().block();
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.patch200(this.client.getHost(), booleanValue, context));
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch200Async() {
        return patch200WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch200() {
        patch200Async().block();
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post200(this.client.getHost(), booleanValue, context));
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post200Async() {
        return post200WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post200() {
        post200Async().block();
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.delete200(this.client.getHost(), booleanValue, context));
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete200Async() {
        return delete200WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete200() {
        delete200Async().block();
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.put201(this.client.getHost(), booleanValue, context));
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put201Async() {
        return put201WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put201() {
        put201Async().block();
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post201(this.client.getHost(), booleanValue, context));
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post201Async() {
        return post201WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post201() {
        post201Async().block();
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.put202(this.client.getHost(), booleanValue, context));
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put202Async() {
        return put202WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put202() {
        put202Async().block();
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.patch202(this.client.getHost(), booleanValue, context));
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch202Async() {
        return patch202WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch202() {
        patch202Async().block();
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post202(this.client.getHost(), booleanValue, context));
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post202Async() {
        return post202WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202() {
        post202Async().block();
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.delete202(this.client.getHost(), booleanValue, context));
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete202Async() {
        return delete202WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202() {
        delete202Async().block();
    }

    /**
     * Return 204 status code if successful.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
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
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204Async() {
        return head204WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 204 status code if successful.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head204() {
        head204Async().block();
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.put204(this.client.getHost(), booleanValue, context));
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put204Async() {
        return put204WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put204() {
        put204Async().block();
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.patch204(this.client.getHost(), booleanValue, context));
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch204Async() {
        return patch204WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch204() {
        patch204Async().block();
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post204(this.client.getHost(), booleanValue, context));
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post204Async() {
        return post204WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post204() {
        post204Async().block();
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.delete204(this.client.getHost(), booleanValue, context));
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete204Async() {
        return delete204WithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete204() {
        delete204Async().block();
    }

    /**
     * Return 404 status code.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> head404WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head404(this.client.getHost(), context));
    }

    /**
     * Return 404 status code.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> head404Async() {
        return head404WithResponseAsync()
                .flatMap(
                        (SimpleResponse<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Return 404 status code.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean head404() {
        Boolean value = head404Async().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }
}
