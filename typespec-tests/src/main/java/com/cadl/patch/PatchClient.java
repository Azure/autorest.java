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
import com.cadl.patch.implementation.JsonMergePatchHelper;
import com.cadl.patch.implementation.PatchesImpl;
import com.cadl.patch.models.Fish;
import com.cadl.patch.models.Resource;

/**
 * Initializes a new instance of the synchronous PatchClient type.
 */
@ServiceClient(builder = PatchClientBuilder.class)
public final class PatchClient {
    @Generated
    private final PatchesImpl serviceClient;

    /**
     * Initializes an instance of PatchClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PatchClient(PatchesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The createOrUpdateResource operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Required): {
     *         String (Required): {
     *             name: String (Required)
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
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Required): {
     *         String (Required): {
     *             name: String (Required)
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
     * }
     * }</pre>
     * 
     * @param resource The resource parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateResourceWithResponse(BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateResourceWithResponse(resource, requestOptions);
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Required): {
     *         String (Required): {
     *             name: String (Required)
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
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Required): {
     *         String (Required): {
     *             name: String (Required)
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
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateOptionalResourceWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateOptionalResourceWithResponse(requestOptions);
    }

    /**
     * The createOrUpdateFish operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Required)
     *     color: String (Optional)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Required)
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
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with
     * {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateFishWithResponse(BinaryData fish, RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateFishWithResponse(fish, requestOptions);
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
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource createOrUpdateResource(Resource resource) {
        // Generated convenience method for createOrUpdateResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        BinaryData resourceInBinaryData = BinaryData.fromObject(resource);
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, false);
        return createOrUpdateResourceWithResponse(resourceInBinaryData, requestOptions).getValue()
            .toObject(Resource.class);
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
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource createOrUpdateOptionalResource(Resource resource) {
        // Generated convenience method for createOrUpdateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (resource != null) {
            JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
            BinaryData resourceInBinaryData = BinaryData.fromObject(resource);
            JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, false);
            requestOptions.setBody(resourceInBinaryData);
        }
        return createOrUpdateOptionalResourceWithResponse(requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource createOrUpdateOptionalResource() {
        // Generated convenience method for createOrUpdateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createOrUpdateOptionalResourceWithResponse(requestOptions).getValue().toObject(Resource.class);
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
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Fish createOrUpdateFish(Fish fish) {
        // Generated convenience method for createOrUpdateFishWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getFishAccessor().prepareModelForJsonMergePatch(fish, true);
        BinaryData fishInBinaryData = BinaryData.fromObject(fish);
        JsonMergePatchHelper.getFishAccessor().prepareModelForJsonMergePatch(fish, false);
        return createOrUpdateFishWithResponse(fishInBinaryData, requestOptions).getValue().toObject(Fish.class);
    }
}
