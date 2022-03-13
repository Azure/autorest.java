// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonstringenum;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import fixtures.nonstringenum.implementation.IntsImpl;
import fixtures.nonstringenum.models.IntEnum;

/** Initializes a new instance of the synchronous NonStringEnumsClient type. */
public final class IntClient {
    @Generated private final IntsImpl serviceClient;

    /**
     * Initializes an instance of IntClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    IntClient(IntsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Put an int enum.
     *
     * @param input Input int enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put(IntEnum input) {
        return this.serviceClient.put(input);
    }

    /**
     * Put an int enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put() {
        return this.serviceClient.put();
    }

    /**
     * Get an int enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IntEnum get() {
        return this.serviceClient.get();
    }
}
