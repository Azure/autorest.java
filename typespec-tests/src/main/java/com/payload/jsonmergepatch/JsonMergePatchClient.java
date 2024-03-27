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
import com.payload.jsonmergepatch.implementation.JsonMergePatchClientImpl;
import com.payload.jsonmergepatch.implementation.JsonMergePatchHelper;
import com.payload.jsonmergepatch.models.Resource;
import com.payload.jsonmergepatch.models.ResourcePatch;

/**
 * Initializes a new instance of the synchronous JsonMergePatchClient type.
 */
@ServiceClient(builder = JsonMergePatchClientBuilder.class)
public final class JsonMergePatchClient {
    @Generated
    private final JsonMergePatchClientImpl serviceClient;

    /**
     * Initializes an instance of JsonMergePatchClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    JsonMergePatchClient(JsonMergePatchClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Test content-type: application/merge-patch+json with required body.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     * }
     * }
     * array (Optional): [
     * (recursive schema, see above)
     * ]
     * intValue: Integer (Optional)
     * floatValue: Double (Optional)
     * innerModel (Optional): (recursive schema, see innerModel above)
     * intArray (Optional): [
     * int (Optional)
     * ]
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     * }
     * }
     * array (Optional): [
     * (recursive schema, see above)
     * ]
     * intValue: Integer (Optional)
     * floatValue: Double (Optional)
     * innerModel (Optional): (recursive schema, see innerModel above)
     * intArray (Optional): [
     * int (Optional)
     * ]
     * }
     * }</pre>
     * 
     * @param body Details about a resource.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a resource along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createResourceWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.createResourceWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: application/merge-patch+json with required body.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     * }
     * }
     * array (Optional): [
     * (recursive schema, see above)
     * ]
     * intValue: Integer (Optional)
     * floatValue: Double (Optional)
     * innerModel (Optional): (recursive schema, see innerModel above)
     * intArray (Optional): [
     * int (Optional)
     * ]
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     * }
     * }
     * array (Optional): [
     * (recursive schema, see above)
     * ]
     * intValue: Integer (Optional)
     * floatValue: Double (Optional)
     * innerModel (Optional): (recursive schema, see innerModel above)
     * intArray (Optional): [
     * int (Optional)
     * ]
     * }
     * }</pre>
     * 
     * @param body Details about a resource for patch operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a resource along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> updateResourceWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.updateResourceWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: application/merge-patch+json with optional body.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     * }
     * }
     * array (Optional): [
     * (recursive schema, see above)
     * ]
     * intValue: Integer (Optional)
     * floatValue: Double (Optional)
     * innerModel (Optional): (recursive schema, see innerModel above)
     * intArray (Optional): [
     * int (Optional)
     * ]
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional): {
     *         String (Required): {
     *             name: String (Optional)
     *             description: String (Optional)
     * }
     * }
     * array (Optional): [
     * (recursive schema, see above)
     * ]
     * intValue: Integer (Optional)
     * floatValue: Double (Optional)
     * innerModel (Optional): (recursive schema, see innerModel above)
     * intArray (Optional): [
     * int (Optional)
     * ]
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a resource along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> updateOptionalResourceWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.updateOptionalResourceWithResponse(requestOptions);
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
     * @return details about a resource.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource createResource(Resource body) {
        // Generated convenience method for createResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createResourceWithResponse(BinaryData.fromObject(body), requestOptions).getValue()
            .toObject(Resource.class);
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
     * @return details about a resource.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource updateResource(ResourcePatch body) {
        // Generated convenience method for updateResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, true);
        BinaryData bodyInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(body).toBytes());
        JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, false);
        return updateResourceWithResponse(bodyInBinaryData, requestOptions).getValue().toObject(Resource.class);
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
     * @return details about a resource.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource updateOptionalResource(ResourcePatch body) {
        // Generated convenience method for updateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (body != null) {
            JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, true);
            BinaryData bodyInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(body).toBytes());
            JsonMergePatchHelper.getResourcePatchAccessor().prepareModelForJsonMergePatch(body, false);
            requestOptions.setBody(bodyInBinaryData);
        }
        return updateOptionalResourceWithResponse(requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * Test content-type: application/merge-patch+json with optional body.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a resource.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource updateOptionalResource() {
        // Generated convenience method for updateOptionalResourceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return updateOptionalResourceWithResponse(requestOptions).getValue().toObject(Resource.class);
    }
}
