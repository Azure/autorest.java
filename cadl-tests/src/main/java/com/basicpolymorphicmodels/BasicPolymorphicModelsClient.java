// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.basicpolymorphicmodels;

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
import com.basicpolymorphicmodels.models.BaseClass;
import com.basicpolymorphicmodels.models.ModelWithPolymorphicProperty;

/** Initializes a new instance of the synchronous BasicPolymorphicModelsClient type. */
@ServiceClient(builder = BasicPolymorphicModelsClientBuilder.class)
public final class BasicPolymorphicModelsClient {
    @Generated private final BasicPolymorphicModelsAsyncClient client;

    /**
     * Initializes an instance of BasicPolymorphicModelsClient class.
     *
     * @param client the async client.
     */
    @Generated
    BasicPolymorphicModelsClient(BasicPolymorphicModelsAsyncClient client) {
        this.client = client;
    }

    /**
     * The setValue operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     baseClassProperty: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     baseClassProperty: String (Required)
     * }
     * }</pre>
     *
     * @param input Example base type.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return example base type along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> setValueWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.setValueWithResponse(input, requestOptions).block();
    }

    /**
     * The setValueWithPolymorphicProperty operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     polymorphicProperty (Required): {
     *         baseClassProperty: String (Required)
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     polymorphicProperty (Required): {
     *         baseClassProperty: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param input Illustrates case where a basic model has polymorphic properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return illustrates case where a basic model has polymorphic properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> setValueWithPolymorphicPropertyWithResponse(
            BinaryData input, RequestOptions requestOptions) {
        return this.client.setValueWithPolymorphicPropertyWithResponse(input, requestOptions).block();
    }

    /**
     * The setValue operation.
     *
     * @param input Example base type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return example base type.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BaseClass setValue(BaseClass input) {
        // Generated convenience method for setValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return setValueWithResponse(BinaryData.fromObject(input), requestOptions).getValue().toObject(BaseClass.class);
    }

    /**
     * The setValue operation.
     *
     * @param input Example base type.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return example base type along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BaseClass> setValueWithResponse(BaseClass input, Context context) {
        // Generated convenience method for setValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        Response<BinaryData> protocolMethodResponse =
                setValueWithResponse(BinaryData.fromObject(input), requestOptions);
        return new SimpleResponse<>(
                protocolMethodResponse, protocolMethodResponse.getValue().toObject(BaseClass.class));
    }

    /**
     * The setValueWithPolymorphicProperty operation.
     *
     * @param input Illustrates case where a basic model has polymorphic properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return illustrates case where a basic model has polymorphic properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ModelWithPolymorphicProperty setValueWithPolymorphicProperty(ModelWithPolymorphicProperty input) {
        // Generated convenience method for setValueWithPolymorphicPropertyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return setValueWithPolymorphicPropertyWithResponse(BinaryData.fromObject(input), requestOptions)
                .getValue()
                .toObject(ModelWithPolymorphicProperty.class);
    }

    /**
     * The setValueWithPolymorphicProperty operation.
     *
     * @param input Illustrates case where a basic model has polymorphic properties.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return illustrates case where a basic model has polymorphic properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ModelWithPolymorphicProperty> setValueWithPolymorphicPropertyWithResponse(
            ModelWithPolymorphicProperty input, Context context) {
        // Generated convenience method for setValueWithPolymorphicPropertyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        Response<BinaryData> protocolMethodResponse =
                setValueWithPolymorphicPropertyWithResponse(BinaryData.fromObject(input), requestOptions);
        return new SimpleResponse<>(
                protocolMethodResponse, protocolMethodResponse.getValue().toObject(ModelWithPolymorphicProperty.class));
    }
}
