package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.azurespecials.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in SubscriptionInCredentials. */
public final class SubscriptionInCredentials {
    /** The proxy service used to perform REST calls. */
    private final SubscriptionInCredentialsService service;

    /** The service client containing this operation class. */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of SubscriptionInCredentials.
     *
     * @param client the instance of the service client containing this operation class.
     */
    SubscriptionInCredentials(AutoRestAzureSpecialParametersTestClient client) {
        this.service =
                RestProxy.create(
                        SubscriptionInCredentialsService.class,
                        client.getHttpPipeline(),
                        client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientSubscriptionInCredentials to
     * be used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecial")
    private interface SubscriptionInCredentialsService {
        @Post("/azurespecials/subscriptionId/method/string/none/path/global/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodGlobalValid(
                @HostParam("$host") String host, @PathParam("subscriptionId") String subscriptionId, Context context);

        @Post("/azurespecials/subscriptionId/method/string/none/path/global/null/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodGlobalNull(
                @HostParam("$host") String host, @PathParam("subscriptionId") String subscriptionId, Context context);

        @Post(
                "/azurespecials/subscriptionId/method/string/none/path/globalNotProvided/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodGlobalNotProvidedValid(
                @HostParam("$host") String host,
                @PathParam("subscriptionId") String subscriptionId,
                @QueryParam("api-version") String apiVersion,
                Context context);

        @Post("/azurespecials/subscriptionId/path/string/none/path/global/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postPathGlobalValid(
                @HostParam("$host") String host, @PathParam("subscriptionId") String subscriptionId, Context context);

        @Post("/azurespecials/subscriptionId/swagger/string/none/path/global/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postSwaggerGlobalValid(
                @HostParam("$host") String host, @PathParam("subscriptionId") String subscriptionId, Context context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.postMethodGlobalValid(this.client.getHost(), this.client.getSubscriptionId(), context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalValidAsync() {
        return postMethodGlobalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postMethodGlobalValid() {
        postMethodGlobalValidAsync().block();
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.postMethodGlobalNull(this.client.getHost(), this.client.getSubscriptionId(), context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalNullAsync() {
        return postMethodGlobalNullWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postMethodGlobalNull() {
        postMethodGlobalNullAsync().block();
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalNotProvidedValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.postMethodGlobalNotProvidedValid(
                                this.client.getHost(),
                                this.client.getSubscriptionId(),
                                this.client.getApiVersion(),
                                context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalNotProvidedValidAsync() {
        return postMethodGlobalNotProvidedValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postMethodGlobalNotProvidedValid() {
        postMethodGlobalNotProvidedValidAsync().block();
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postPathGlobalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.postPathGlobalValid(this.client.getHost(), this.client.getSubscriptionId(), context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postPathGlobalValidAsync() {
        return postPathGlobalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postPathGlobalValid() {
        postPathGlobalValidAsync().block();
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postSwaggerGlobalValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.postSwaggerGlobalValid(
                                this.client.getHost(), this.client.getSubscriptionId(), context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postSwaggerGlobalValidAsync() {
        return postSwaggerGlobalValidWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postSwaggerGlobalValid() {
        postSwaggerGlobalValidAsync().block();
    }
}
