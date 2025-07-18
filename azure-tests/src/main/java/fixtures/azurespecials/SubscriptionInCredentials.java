// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
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

/**
 * An instance of this class provides access to all the operations defined in SubscriptionInCredentials.
 */
public final class SubscriptionInCredentials {
    /**
     * The proxy service used to perform REST calls.
     */
    private final SubscriptionInCredentialsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of SubscriptionInCredentials.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    SubscriptionInCredentials(AutoRestAzureSpecialParametersTestClient client) {
        this.service = RestProxy.create(SubscriptionInCredentialsService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientSubscriptionInCredentials to
     * be used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecialParametersTestClientSubscriptionInCredentials")
    public interface SubscriptionInCredentialsService {
        @Post("/azurespecials/subscriptionId/method/string/none/path/global/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodGlobalValid(@HostParam("$host") String host,
            @PathParam("subscriptionId") String subscriptionId, @HeaderParam("Accept") String accept, Context context);

        @Post("/azurespecials/subscriptionId/method/string/none/path/global/null/{subscriptionId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodGlobalNull(@HostParam("$host") String host,
            @PathParam("subscriptionId") String subscriptionId, @HeaderParam("Accept") String accept, Context context);

        @Post("/azurespecials/subscriptionId/method/string/none/path/globalNotProvided/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postMethodGlobalNotProvidedValid(@HostParam("$host") String host,
            @PathParam("subscriptionId") String subscriptionId, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Accept") String accept, Context context);

        @Post("/azurespecials/subscriptionId/path/string/none/path/global/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postPathGlobalValid(@HostParam("$host") String host,
            @PathParam("subscriptionId") String subscriptionId, @HeaderParam("Accept") String accept, Context context);

        @Post("/azurespecials/subscriptionId/swagger/string/none/path/global/1234-5678-9012-3456/{subscriptionId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postSwaggerGlobalValid(@HostParam("$host") String host,
            @PathParam("subscriptionId") String subscriptionId, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalValidWithResponseAsync() {
        return FluxUtil.withContext(context -> postMethodGlobalValidWithResponseAsync(context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.postMethodGlobalValid(this.client.getHost(), this.client.getSubscriptionId(), accept, context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalValidAsync() {
        return postMethodGlobalValidWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalValidAsync(Context context) {
        return postMethodGlobalValidWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> postMethodGlobalValidWithResponse(Context context) {
        return postMethodGlobalValidWithResponseAsync(context).block();
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
        postMethodGlobalValidWithResponse(Context.NONE);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalNullWithResponseAsync() {
        return FluxUtil.withContext(context -> postMethodGlobalNullWithResponseAsync(context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalNullWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.postMethodGlobalNull(this.client.getHost(), this.client.getSubscriptionId(), accept, context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalNullAsync() {
        return postMethodGlobalNullWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalNullAsync(Context context) {
        return postMethodGlobalNullWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to null, and
     * client-side validation should prevent you from making this call.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> postMethodGlobalNullWithResponse(Context context) {
        return postMethodGlobalNullWithResponseAsync(context).block();
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
        postMethodGlobalNullWithResponse(Context.NONE);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalNotProvidedValidWithResponseAsync() {
        return FluxUtil.withContext(context -> postMethodGlobalNotProvidedValidWithResponseAsync(context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postMethodGlobalNotProvidedValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.postMethodGlobalNotProvidedValid(this.client.getHost(), this.client.getSubscriptionId(),
            this.client.getApiVersion(), accept, context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalNotProvidedValidAsync() {
        return postMethodGlobalNotProvidedValidWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postMethodGlobalNotProvidedValidAsync(Context context) {
        return postMethodGlobalNotProvidedValidWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> postMethodGlobalNotProvidedValidWithResponse(Context context) {
        return postMethodGlobalNotProvidedValidWithResponseAsync(context).block();
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
        postMethodGlobalNotProvidedValidWithResponse(Context.NONE);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postPathGlobalValidWithResponseAsync() {
        return FluxUtil.withContext(context -> postPathGlobalValidWithResponseAsync(context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postPathGlobalValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.postPathGlobalValid(this.client.getHost(), this.client.getSubscriptionId(), accept, context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postPathGlobalValidAsync() {
        return postPathGlobalValidWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postPathGlobalValidAsync(Context context) {
        return postPathGlobalValidWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> postPathGlobalValidWithResponse(Context context) {
        return postPathGlobalValidWithResponseAsync(context).block();
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
        postPathGlobalValidWithResponse(Context.NONE);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postSwaggerGlobalValidWithResponseAsync() {
        return FluxUtil.withContext(context -> postSwaggerGlobalValidWithResponseAsync(context));
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postSwaggerGlobalValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.postSwaggerGlobalValid(this.client.getHost(), this.client.getSubscriptionId(), accept, context);
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postSwaggerGlobalValidAsync() {
        return postSwaggerGlobalValidWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postSwaggerGlobalValidAsync(Context context) {
        return postSwaggerGlobalValidWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * POST method with subscriptionId modeled in credentials. Set the credential subscriptionId to
     * '1234-5678-9012-3456' to succeed.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> postSwaggerGlobalValidWithResponse(Context context) {
        return postSwaggerGlobalValidWithResponseAsync(context).block();
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
        postSwaggerGlobalValidWithResponse(Context.NONE);
    }
}
