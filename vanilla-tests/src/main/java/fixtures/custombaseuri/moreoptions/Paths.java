// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.custombaseuri.moreoptions;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.custombaseuri.moreoptions.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Paths.
 */
public final class Paths {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PathsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestParameterizedCustomHostTestClient client;

    /**
     * Initializes an instance of Paths.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Paths(AutoRestParameterizedCustomHostTestClient client) {
        this.service = RestProxy.create(PathsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestParameterizedCustomHostTestClientPaths to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{vault}{secret}{dnsSuffix}")
    @ServiceInterface(name = "AutoRestParameterizedCustomHostTestClientPaths")
    public interface PathsService {
        @Get("/customuri/{subscriptionId}/{keyName}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getEmpty(@HostParam("vault") String vault, @HostParam("secret") String secret,
            @HostParam("dnsSuffix") String dnsSuffix, @PathParam("keyName") String keyName,
            @PathParam("subscriptionId") String subscriptionId, @QueryParam("keyVersion") String keyVersion,
            @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @param keyVersion The key version. Default value 'v1'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync(String vault, String secret, String keyName,
        String keyVersion) {
        return FluxUtil.withContext(context -> getEmptyWithResponseAsync(vault, secret, keyName, keyVersion, context));
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @param keyVersion The key version. Default value 'v1'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getEmptyWithResponseAsync(String vault, String secret, String keyName,
        String keyVersion, Context context) {
        if (vault == null) {
            return Mono.error(new IllegalArgumentException("Parameter vault is required and cannot be null."));
        }
        if (secret == null) {
            return Mono.error(new IllegalArgumentException("Parameter secret is required and cannot be null."));
        }
        if (this.client.getDnsSuffix() == null) {
            return Mono.error(
                new IllegalArgumentException("Parameter this.client.getDnsSuffix() is required and cannot be null."));
        }
        if (keyName == null) {
            return Mono.error(new IllegalArgumentException("Parameter keyName is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getEmpty(vault, secret, this.client.getDnsSuffix(), keyName, this.client.getSubscriptionId(),
            keyVersion, accept, context);
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @param keyVersion The key version. Default value 'v1'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String vault, String secret, String keyName, String keyVersion) {
        return getEmptyWithResponseAsync(vault, secret, keyName, keyVersion).flatMap(ignored -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String vault, String secret, String keyName) {
        final String keyVersion = null;
        return getEmptyWithResponseAsync(vault, secret, keyName, keyVersion).flatMap(ignored -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @param keyVersion The key version. Default value 'v1'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyAsync(String vault, String secret, String keyName, String keyVersion, Context context) {
        return getEmptyWithResponseAsync(vault, secret, keyName, keyVersion, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @param keyVersion The key version. Default value 'v1'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getEmptyWithResponse(String vault, String secret, String keyName, String keyVersion,
        Context context) {
        return getEmptyWithResponseAsync(vault, secret, keyName, keyVersion, context).block();
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @param keyVersion The key version. Default value 'v1'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty(String vault, String secret, String keyName, String keyVersion) {
        getEmptyWithResponse(vault, secret, keyName, keyVersion, Context.NONE);
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param vault The vault name, e.g. https://myvault.
     * @param secret Secret value.
     * @param keyName The key name with value 'key1'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty(String vault, String secret, String keyName) {
        final String keyVersion = null;
        getEmptyWithResponse(vault, secret, keyName, keyVersion, Context.NONE);
    }
}
