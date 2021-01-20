package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
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

/** An instance of this class provides access to all the operations defined in SubscriptionInMethods. */
public final class SubscriptionInMethods {
    /** The proxy service used to perform REST calls. */
    private final SubscriptionInMethodsService service;

    /** The service client containing this operation class. */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of SubscriptionInMethods.
     *
     * @param client the instance of the service client containing this operation class.
     */
    SubscriptionInMethods(AutoRestAzureSpecialParametersTestClient client) {
        this.service =
                RestProxy.create(
                        SubscriptionInMethodsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientSubscriptionInMethods to be
     * used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecial")
    private interface SubscriptionInMethodsService {
        @Post("/azurespecials/subscriptionId/method/string/none/path/local/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodLocalValid(
                @HostParam("$host") String host,
                @PathParam("subscriptionId") String subscriptionId,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/azurespecials/subscriptionId/method/string/none/path/local/null/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodLocalNull(
                @HostParam("$host") String host,
                @PathParam("subscriptionId") String subscriptionId,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/azurespecials/subscriptionId/path/string/none/path/local/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postPathLocalValid(
                @HostParam("$host") String host,
                @PathParam("subscriptionId") String subscriptionId,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/azurespecials/subscriptionId/swagger/string/none/path/local/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postSwaggerLocalValid(
                @HostParam("$host") String host,
                @PathParam("subscriptionId") String subscriptionId,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId This should appear as a method parameter, use value '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodLocalValidWithResponseAsync(String subscriptionId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (subscriptionId == null) {
            return Mono.error(new IllegalArgumentException("Parameter subscriptionId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postMethodLocalValid(this.client.getHost(), subscriptionId, accept, context));
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId This should appear as a method parameter, use value '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodLocalValidAsync(String subscriptionId) {
        return postMethodLocalValidWithResponseAsync(subscriptionId).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId This should appear as a method parameter, use value '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postMethodLocalValid(String subscriptionId) {
        postMethodLocalValidAsync(subscriptionId).block();
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = null, client-side validation
     * should prevent you from making this call.
     *
     * @param subscriptionId This should appear as a method parameter, use value null, client-side validation should
     *     prvenet the call.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodLocalNullWithResponseAsync(String subscriptionId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (subscriptionId == null) {
            return Mono.error(new IllegalArgumentException("Parameter subscriptionId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postMethodLocalNull(this.client.getHost(), subscriptionId, accept, context));
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = null, client-side validation
     * should prevent you from making this call.
     *
     * @param subscriptionId This should appear as a method parameter, use value null, client-side validation should
     *     prvenet the call.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodLocalNullAsync(String subscriptionId) {
        return postMethodLocalNullWithResponseAsync(subscriptionId).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = null, client-side validation
     * should prevent you from making this call.
     *
     * @param subscriptionId This should appear as a method parameter, use value null, client-side validation should
     *     prvenet the call.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postMethodLocalNull(String subscriptionId) {
        postMethodLocalNullAsync(subscriptionId).block();
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId Should appear as a method parameter -use value '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postPathLocalValidWithResponseAsync(String subscriptionId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (subscriptionId == null) {
            return Mono.error(new IllegalArgumentException("Parameter subscriptionId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postPathLocalValid(this.client.getHost(), subscriptionId, accept, context));
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId Should appear as a method parameter -use value '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postPathLocalValidAsync(String subscriptionId) {
        return postPathLocalValidWithResponseAsync(subscriptionId).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId Should appear as a method parameter -use value '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postPathLocalValid(String subscriptionId) {
        postPathLocalValidAsync(subscriptionId).block();
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId The subscriptionId, which appears in the path, the value is always '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postSwaggerLocalValidWithResponseAsync(String subscriptionId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (subscriptionId == null) {
            return Mono.error(new IllegalArgumentException("Parameter subscriptionId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postSwaggerLocalValid(this.client.getHost(), subscriptionId, accept, context));
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId The subscriptionId, which appears in the path, the value is always '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postSwaggerLocalValidAsync(String subscriptionId) {
        return postSwaggerLocalValidWithResponseAsync(subscriptionId).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in the method. pass in subscription id = '1234-5678-9012-3456' to
     * succeed.
     *
     * @param subscriptionId The subscriptionId, which appears in the path, the value is always '1234-5678-9012-3456'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postSwaggerLocalValid(String subscriptionId) {
        postSwaggerLocalValidAsync(subscriptionId).block();
    }
}
