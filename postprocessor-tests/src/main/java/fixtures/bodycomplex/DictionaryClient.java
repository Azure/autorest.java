// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.bodycomplex.implementation.DictionariesImpl;
import fixtures.bodycomplex.implementation.models.DictionaryWrapper;
import fixtures.bodycomplex.implementation.models.ErrorException;

/** Initializes a new instance of the synchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class DictionaryClient {
    private final DictionariesImpl serviceClient;

    /**
     * Initializes an instance of Dictionaries client.
     *
     * @param serviceClient the service client implementation.
     */
    DictionaryClient(DictionariesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with dictionary property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getValid() {
        return this.serviceClient.getValid();
    }

    /**
     * Get complex types with dictionary property.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getValidWithResponse(Context context) {
        return this.serviceClient.getValidWithResponse(context);
    }

    /**
     * Put complex types with dictionary property.
     *
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     *     "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(DictionaryWrapper complexBody) {
        this.serviceClient.putValid(complexBody);
    }

    /**
     * Put complex types with dictionary property.
     *
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     *     "xls":"excel", "exe":"", "":null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(DictionaryWrapper complexBody, Context context) {
        return this.serviceClient.putValidWithResponse(complexBody, context);
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getEmpty() {
        return this.serviceClient.getEmpty();
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getEmptyWithResponse(Context context) {
        return this.serviceClient.getEmptyWithResponse(context);
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(DictionaryWrapper complexBody) {
        this.serviceClient.putEmpty(complexBody);
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * @param complexBody Please put an empty dictionary.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(DictionaryWrapper complexBody, Context context) {
        return this.serviceClient.putEmptyWithResponse(complexBody, context);
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNull() {
        return this.serviceClient.getNull();
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getNullWithResponse(Context context) {
        return this.serviceClient.getNullWithResponse(context);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNotProvided() {
        return this.serviceClient.getNotProvided();
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getNotProvidedWithResponse(Context context) {
        return this.serviceClient.getNotProvidedWithResponse(context);
    }
}
