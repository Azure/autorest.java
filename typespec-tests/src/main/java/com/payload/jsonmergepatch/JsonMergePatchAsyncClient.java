// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.jsonmergepatch;

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
import com.payload.jsonmergepatch.implementation.JsonMergePatchClientImpl;
import com.payload.jsonmergepatch.implementation.JsonMergePatchHelper;
import com.payload.jsonmergepatch.models.Resource;
import com.payload.jsonmergepatch.models.ResourcePatch;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous JsonMergePatchClient type.
 */
@ServiceClient(builder = JsonMergePatchClientBuilder.class, isAsync = true)
public final class JsonMergePatchAsyncClient {
    @Generated
    private final JsonMergePatchClientImpl serviceClient;

    /**
     * Initializes an instance of JsonMergePatchAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    JsonMergePatchAsyncClient(JsonMergePatchClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Test content-type: application/merge-patch+json with required body.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     *         }
     *     }
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     intValue: Integer (Optional)
     *     floatValue: Double (Optional)
     *     innerModel (Optional): (recursive schema, see innerModel above)
     *     intArray (Optional): [
     *         int (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     *         }
     *     }
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     intValue: Integer (Optional)
     *     floatValue: Double (Optional)
     *     innerModel (Optional): (recursive schema, see innerModel above)
     *     intArray (Optional): [
     *         int (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * @param body Details about a resource.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a resource along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createResourceWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.createResourceWithResponseAsync(body, requestOptions);
    }

    /**
     * Test content-type: application/merge-patch+json with required body.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     *         }
     *     }
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     intValue: Integer (Optional)
     *     floatValue: Double (Optional)
     *     innerModel (Optional): (recursive schema, see innerModel above)
     *     intArray (Optional): [
     *         int (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     *         }
     *     }
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     intValue: Integer (Optional)
     *     floatValue: Double (Optional)
     *     innerModel (Optional): (recursive schema, see innerModel above)
     *     intArray (Optional): [
     *         int (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * @param body Details about a resource for patch operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a resource along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> updateResourceWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.updateResourceWithResponseAsync(body, requestOptions);
    }

    /**
     * Test content-type: application/merge-patch+json with optional body.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     *         }
     *     }
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     intValue: Integer (Optional)
     *     floatValue: Double (Optional)
     *     innerModel (Optional): (recursive schema, see innerModel above)
     *     intArray (Optional): [
     *         int (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     *         }
     *     }
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     *     intValue: Integer (Optional)
     *     floatValue: Double (Optional)
     *     innerModel (Optional): (recursive schema, see innerModel above)
     *     intArray (Optional): [
     *         int (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a resource along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> updateOptionalResourceWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.updateOptionalResourceWithResponseAsync(requestOptions);
    }

    /**
     * Test content-type: application/merge-patch+json with required body.
     * 
     * @param body Details about a resource.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a resource on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> createResource(Resource body) {
        // Generated convenience method for createResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createResourceWithResponse(BinaryData.fromObject(body), requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * Test content-type: application/merge-patch+json with required body.
     * 
     * @param body Details about a resource for patch operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a resource on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> updateResource(ResourcePatch body) {
        // Generated convenience method for updateResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, true);
        BinaryData bodyInBinaryData = BinaryData.fromObject(body);
        // BinaryData.fromObject() will not fire serialization, use getLength() to fire serialization.
        bodyInBinaryData.getLength();
        JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, false);
        return updateResourceWithResponse(bodyInBinaryData, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * Test content-type: application/merge-patch+json with optional body.
     * 
     * @param body Details about a resource for patch operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a resource on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> updateOptionalResource(ResourcePatch body) {
        // Generated convenience method for updateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (body != null) {
            JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, true);
            BinaryData bodyInBinaryData = BinaryData.fromObject(body);
            // BinaryData.fromObject() will not fire serialization, use getLength() to fire serialization.
            bodyInBinaryData.getLength();
            JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, false);
            requestOptions.setBody(bodyInBinaryData);
        }
        return updateOptionalResourceWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * Test content-type: application/merge-patch+json with optional body.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a resource on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> updateOptionalResource() {
        // Generated convenience method for updateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return updateOptionalResourceWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }
}
