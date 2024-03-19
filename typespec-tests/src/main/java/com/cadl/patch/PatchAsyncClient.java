// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.patch;

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
import com.azure.core.util.FluxUtil;
import com.cadl.patch.implementation.JsonMergePatchHelper;
import com.cadl.patch.implementation.PatchesImpl;
import com.cadl.patch.models.Fish;
import com.cadl.patch.models.Resource;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous PatchClient type.
 */
@ServiceClient(builder = PatchClientBuilder.class, isAsync = true)
public final class PatchAsyncClient {
    @Generated
    private final PatchesImpl serviceClient;

    /**
     * Initializes an instance of PatchAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PatchAsyncClient(PatchesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The createOrUpdateResource operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     fish (Optional): {
     *         kind: String (Required)
     *         id: String (Required)
     *         name: String (Required)
     *         age: int (Optional, Required on create)
     *         color: String (Optional)
     *     }
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     fish (Optional): {
     *         kind: String (Required)
     *         id: String (Required)
     *         name: String (Required)
     *         age: int (Optional, Required on create)
     *         color: String (Optional)
     *     }
     * }
     * }</pre>
     * 
     * @param resource The resource parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateResourceWithResponse(BinaryData resource,
        RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateResourceWithResponseAsync(resource, requestOptions);
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     fish (Optional): {
     *         kind: String (Required)
     *         id: String (Required)
     *         name: String (Required)
     *         age: int (Optional, Required on create)
     *         color: String (Optional)
     *     }
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     fish (Optional): {
     *         kind: String (Required)
     *         id: String (Required)
     *         name: String (Required)
     *         age: int (Optional, Required on create)
     *         color: String (Optional)
     *     }
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateOptionalResourceWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateOptionalResourceWithResponseAsync(requestOptions);
    }

    /**
     * The createOrUpdateFish operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Required)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Optional, Required on create)
     *     color: String (Optional)
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Required)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Optional, Required on create)
     *     color: String (Optional)
     * }
     * }</pre>
     * 
     * @param fish This is base model for polymorphic multiple levels inheritance with a discriminator.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateFishWithResponse(BinaryData fish, RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateFishWithResponseAsync(fish, requestOptions);
    }

    /**
     * The createOrUpdateResource operation.
     * 
     * @param resource The resource parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> createOrUpdateResource(Resource resource) {
        // Generated convenience method for createOrUpdateResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        BinaryData resourceInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(resource).toBytes());
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, false);
        return createOrUpdateResourceWithResponse(resourceInBinaryData, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * 
     * @param resource The resource parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> createOrUpdateOptionalResource(Resource resource) {
        // Generated convenience method for createOrUpdateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (resource != null) {
            JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
            BinaryData resourceInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(resource).toBytes());
            JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, false);
            requestOptions.setBody(resourceInBinaryData);
        }
        return createOrUpdateOptionalResourceWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> createOrUpdateOptionalResource() {
        // Generated convenience method for createOrUpdateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createOrUpdateOptionalResourceWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * The createOrUpdateFish operation.
     * 
     * @param fish This is base model for polymorphic multiple levels inheritance with a discriminator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> createOrUpdateFish(Fish fish) {
        // Generated convenience method for createOrUpdateFishWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getFishAccessor().prepareModelForJsonMergePatch(fish, true);
        BinaryData fishInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(fish).toBytes());
        JsonMergePatchHelper.getFishAccessor().prepareModelForJsonMergePatch(fish, false);
        return createOrUpdateFishWithResponse(fishInBinaryData, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Fish.class));
    }
}
