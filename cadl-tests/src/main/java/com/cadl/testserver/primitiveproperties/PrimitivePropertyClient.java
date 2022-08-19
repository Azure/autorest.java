// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.primitiveproperties;

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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.cadl.testserver.primitiveproperties.models.PrimitivePropertyModel;

/** Initializes a new instance of the synchronous PrimitivePropertyClient type. */
@ServiceClient(builder = PrimitivePropertyClientBuilder.class)
public final class PrimitivePropertyClient {
    @Generated private final PrimitivePropertyAsyncClient client;

    /**
     * Initializes an instance of PrimitivePropertyClient class.
     *
     * @param client the async client.
     */
    @Generated
    PrimitivePropertyClient(PrimitivePropertyAsyncClient client) {
        this.client = client;
    }

    /**
     * The getModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     requiredString: String (Required)
     *     requiredBytes: byte[] (Required)
     *     requiredInt: long (Required)
     *     requiredLong: long (Required)
     *     requiredSafeInt: long (Required)
     *     requiredFloat: double (Required)
     *     requiredDouble: double (Required)
     *     requiredBodyDateTime: OffsetDateTime (Required)
     *     requiredDuration: Duration (Required)
     *     requiredBoolean: boolean (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     requiredString: String (Required)
     *     requiredBytes: byte[] (Required)
     *     requiredInt: long (Required)
     *     requiredLong: long (Required)
     *     requiredSafeInt: long (Required)
     *     requiredFloat: double (Required)
     *     requiredDouble: double (Required)
     *     requiredBodyDateTime: OffsetDateTime (Required)
     *     requiredDuration: Duration (Required)
     *     requiredBoolean: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Round-trip model with primitive properties to show serialization and deserialization of each.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return round-trip model with primitive properties to show serialization and deserialization of each along with
     *     {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.getModelWithResponse(input, requestOptions).block();
    }

    /*
     * Generated convenience method for getModelWithResponse
     */
    /**
     * The getModel operation.
     *
     * @param input Round-trip model with primitive properties to show serialization and deserialization of each.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return round-trip model with primitive properties to show serialization and deserialization of each.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PrimitivePropertyModel getModel(PrimitivePropertyModel input) {
        RequestOptions requestOptions = new RequestOptions();
        return getModelWithResponse(BinaryData.fromObject(input), requestOptions)
                .getValue()
                .toObject(PrimitivePropertyModel.class);
    }

    /*
     * Generated convenience method for getModelWithResponse
     */
    /**
     * The getModel operation.
     *
     * @param input Round-trip model with primitive properties to show serialization and deserialization of each.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return round-trip model with primitive properties to show serialization and deserialization of each along with
     *     {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<PrimitivePropertyModel> getModelWithResponse(PrimitivePropertyModel input, Context context) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        Response<BinaryData> protocolMethodResponse =
                getModelWithResponse(BinaryData.fromObject(input), requestOptions);
        return new SimpleResponse<>(
                protocolMethodResponse, protocolMethodResponse.getValue().toObject(PrimitivePropertyModel.class));
    }
}
