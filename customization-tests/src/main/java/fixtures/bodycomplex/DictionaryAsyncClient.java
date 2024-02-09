// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;

import com.azure.core.annotation.ServiceMethod;

import com.azure.core.http.rest.Response;

import fixtures.bodycomplex.implementation.DictionariesImpl;
import fixtures.bodycomplex.implementation.models.DictionaryWrapper;
import fixtures.bodycomplex.implementation.models.ErrorException;

import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AutoRestComplexTestService type.
 */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class, isAsync = true)
public final class DictionaryAsyncClient {
    @Generated
    private final DictionariesImpl serviceClient;

    /**
     * Initializes an instance of DictionaryAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DictionaryAsyncClient(DictionariesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getValidWithResponse() {
        return this.serviceClient.getValidWithResponseAsync();
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getValid() {
        return this.serviceClient.getValidAsync();
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponse(DictionaryWrapper complexBody) {
        return this.serviceClient.putValidWithResponseAsync(complexBody);
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValid(DictionaryWrapper complexBody) {
        return this.serviceClient.putValidAsync(complexBody);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getEmptyWithResponse() {
        return this.serviceClient.getEmptyWithResponseAsync();
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getEmpty() {
        return this.serviceClient.getEmptyAsync();
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponse(DictionaryWrapper complexBody) {
        return this.serviceClient.putEmptyWithResponseAsync(complexBody);
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmpty(DictionaryWrapper complexBody) {
        return this.serviceClient.putEmptyAsync(complexBody);
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNullWithResponse() {
        return this.serviceClient.getNullWithResponseAsync();
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNull() {
        return this.serviceClient.getNullAsync();
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNotProvidedWithResponse() {
        return this.serviceClient.getNotProvidedWithResponseAsync();
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload on successful
     * completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNotProvided() {
        return this.serviceClient.getNotProvidedAsync();
    }
}
