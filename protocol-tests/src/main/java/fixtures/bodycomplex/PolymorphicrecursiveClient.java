// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.PolymorphicrecursivesImpl;

/**
 * Initializes a new instance of the synchronous AutoRestComplexTestServiceClient type.
 */
@ServiceClient(builder = PolymorphicrecursiveClientBuilder.class)
public final class PolymorphicrecursiveClient {
    @Generated
    private final PolymorphicrecursivesImpl serviceClient;

    /**
     * Initializes an instance of PolymorphicrecursiveClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PolymorphicrecursiveClient(PolymorphicrecursivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     species: String (Optional)
     *     length: float (Required)
     *     siblings (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types that are polymorphic and have recursive references along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponse(requestOptions);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     species: String (Optional)
     *     length: float (Required)
     *     siblings (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidWithResponse(complexBody, requestOptions);
    }
}
