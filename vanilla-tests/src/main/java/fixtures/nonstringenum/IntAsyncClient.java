// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonstringenum;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import fixtures.nonstringenum.implementation.IntsImpl;
import fixtures.nonstringenum.models.IntEnum;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous NonStringEnumsClient type. */
public final class IntAsyncClient {
    @Generated private final IntsImpl serviceClient;

    /**
     * Initializes an instance of IntAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    IntAsyncClient(IntsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Put an int enum.
     *
     * @param input Input int enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putWithResponse(IntEnum input) {
        return this.serviceClient.putWithResponseAsync(input);
    }

    /**
     * Put an int enum.
     *
     * @param input Input int enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> put(IntEnum input) {
        return this.serviceClient.putAsync(input);
    }

    /**
     * Put an int enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> put() {
        return this.serviceClient.putAsync();
    }

    /**
     * Get an int enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<IntEnum>> getWithResponse() {
        return this.serviceClient.getWithResponseAsync();
    }

    /**
     * Get an int enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IntEnum> get() {
        return this.serviceClient.getAsync();
    }
}
