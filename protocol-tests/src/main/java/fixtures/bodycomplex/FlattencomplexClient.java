// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.FlattencomplexesImpl;

/** Initializes a new instance of the synchronous AutoRestComplexTestServiceClient type. */
@ServiceClient(builder = FlattencomplexClientBuilder.class)
public final class FlattencomplexClient {
    @Generated private final FlattencomplexesImpl serviceClient;

    /**
     * Initializes an instance of Flattencomplexes client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    FlattencomplexClient(FlattencomplexesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * <strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     propB1: String
     *     helper: {
     *         propBH1: String
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponse(requestOptions);
    }
}
