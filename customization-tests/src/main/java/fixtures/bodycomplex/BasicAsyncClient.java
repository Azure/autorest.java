// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import fixtures.bodycomplex.implementation.BasicsImpl;
import fixtures.bodycomplex.implementation.models.Basic;
import fixtures.bodycomplex.implementation.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AutoRestComplexTestService type.
 */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class, isAsync = true)
public final class BasicAsyncClient {
    @Generated
    private final BasicsImpl serviceClient;

    /**
     * Initializes an instance of BasicAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    BasicAsyncClient(BasicsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getValidWithResponse() {
        return this.serviceClient.getValidWithResponseAsync();
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getValid() {
        return this.serviceClient.getValidAsync();
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponse(Basic complexBody) {
        return this.serviceClient.putValidWithResponseAsync(complexBody);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValid(Basic complexBody) {
        return this.serviceClient.putValidAsync(complexBody);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getInvalidWithResponse() {
        return this.serviceClient.getInvalidWithResponseAsync();
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getInvalid() {
        return this.serviceClient.getInvalidAsync();
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getEmptyWithResponse() {
        return this.serviceClient.getEmptyWithResponseAsync();
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getEmpty() {
        return this.serviceClient.getEmptyAsync();
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getNullWithResponse() {
        return this.serviceClient.getNullWithResponseAsync();
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNull() {
        return this.serviceClient.getNullAsync();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getNotProvidedWithResponse() {
        return this.serviceClient.getNotProvidedWithResponseAsync();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNotProvided() {
        return this.serviceClient.getNotProvidedAsync();
    }
}
