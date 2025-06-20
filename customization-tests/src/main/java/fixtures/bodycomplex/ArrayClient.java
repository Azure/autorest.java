// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.bodycomplex.implementation.ArraysImpl;
import fixtures.bodycomplex.implementation.models.ArrayWrapper;
import fixtures.bodycomplex.implementation.models.ErrorException;

/**
 * The sync client containing Array operations.
 */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class ArrayClient {

    @Generated
    private final ArraysImpl serviceClient;

    /**
     * Initializes an instance of ArrayClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    ArrayClient(ArraysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with array property.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ArrayWrapper> getValidWithResponse(Context context) {
        return this.serviceClient.getValidWithResponse(context);
    }

    /**
     * Get complex types with array property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getValid() {
        return this.serviceClient.getValid();
    }

    /**
     * Put complex types with array property.
     *
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;S#$(*Y", "The quick brown fox
     * jumps over the lazy dog".
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(ArrayWrapper complexBody, Context context) {
        return this.serviceClient.putValidWithResponse(complexBody, context);
    }

    /**
     * Put complex types with array property.
     *
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;S#$(*Y", "The quick brown fox
     * jumps over the lazy dog".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return The ArrayClient itself
     * @see ArrayAsyncClient#putValid(ArrayWrapper)
     * @since 1.0.0-beta.1
     */
    @Generated
    ArrayClient putValid(ArrayWrapper complexBody) {
        this.serviceClient.putValid(complexBody);
        return this;
    }

    /**
     * Get complex types with array property which is empty.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property which is empty along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ArrayWrapper> getEmptyWithResponse(Context context) {
        return this.serviceClient.getEmptyWithResponse(context);
    }

    /**
     * Get complex types with array property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property which is empty.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getEmpty() {
        return this.serviceClient.getEmpty();
    }

    /**
     * Put complex types with array property which is empty.
     *
     * @param complexBody Please put an empty array.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(ArrayWrapper complexBody, Context context) {
        return this.serviceClient.putEmptyWithResponse(complexBody, context);
    }

    /**
     * Put complex types with array property which is empty.
     *
     * @param complexBody Please put an empty array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(ArrayWrapper complexBody) {
        this.serviceClient.putEmpty(complexBody);
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property while server doesn't provide a response payload along with
     * {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ArrayWrapper> getNotProvidedWithResponse(Context context) {
        return this.serviceClient.getNotProvidedWithResponse(context);
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property while server doesn't provide a response payload.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getNotProvided() {
        return this.serviceClient.getNotProvided();
    }
}
