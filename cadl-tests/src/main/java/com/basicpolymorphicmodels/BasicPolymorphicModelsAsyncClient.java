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
import com.azure.core.util.BinaryData;
import com.basicpolymorphicmodels.implementation.BasicPolymorphicModelsImpl;
import com.basicpolymorphicmodels.models.BaseClass;
import com.basicpolymorphicmodels.models.ModelWithPolymorphicProperty;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous BasicPolymorphicModelsClient type. */
@ServiceClient(builder = BasicPolymorphicModelsClientBuilder.class, isAsync = true)
public final class BasicPolymorphicModelsAsyncClient {
    @Generated private final BasicPolymorphicModelsImpl serviceClient;

    /**
     * Initializes an instance of BasicPolymorphicModelsAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    BasicPolymorphicModelsAsyncClient(BasicPolymorphicModelsImpl serviceClient) {
        this.serviceClient = serviceClient;
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
     * @return example base type along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> setValueWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.setValueWithResponseAsync(input, requestOptions);
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
     * @return illustrates case where a basic model has polymorphic properties along with {@link Response} on successful
     *     completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> setValueWithPolymorphicPropertyWithResponse(
            BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.setValueWithPolymorphicPropertyWithResponseAsync(input, requestOptions);
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
     * @return example base type on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BaseClass> setValue(BaseClass input) {
        // Generated convenience method for setValueWithResponse

        RequestOptions requestOptions = new RequestOptions();
        return setValueWithResponse(BinaryData.fromObject(input), requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(BaseClass.class));
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
     * @return illustrates case where a basic model has polymorphic properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ModelWithPolymorphicProperty> setValueWithPolymorphicProperty(ModelWithPolymorphicProperty input) {
        // Generated convenience method for setValueWithPolymorphicPropertyWithResponse

        RequestOptions requestOptions = new RequestOptions();
        return setValueWithPolymorphicPropertyWithResponse(BinaryData.fromObject(input), requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(ModelWithPolymorphicProperty.class));
    }
}
