// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.multipart;

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
import com.payload.multipart.implementation.FormDatasImpl;
import com.payload.multipart.implementation.MultipartFormDataHelper;
import com.payload.multipart.models.BinaryArrayPartsRequest;
import com.payload.multipart.models.ComplexPartsRequest;
import com.payload.multipart.models.JsonArrayPartsRequest;
import com.payload.multipart.models.JsonPartRequest;
import com.payload.multipart.models.MultiBinaryPartsRequest;
import com.payload.multipart.models.MultiPartRequest;

/**
 * Initializes a new instance of the synchronous MultiPartClient type.
 */
@ServiceClient(builder = MultiPartClientBuilder.class)
public final class MultiPartClient {
    @Generated
    private final FormDatasImpl serviceClient;

    /**
     * Initializes an instance of MultiPartClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultiPartClient(FormDatasImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Test content-type: multipart/form-data.
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> basicWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'basic' is
        // 'multipart/form-data'
        return this.serviceClient.basicWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: multipart/form-data for mixed scenarios.
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> complexWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'complex' is
        // 'multipart/form-data'
        return this.serviceClient.complexWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: multipart/form-data for scenario contains json part and binary part.
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> jsonPartWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'jsonPart' is
        // 'multipart/form-data'
        return this.serviceClient.jsonPartWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: multipart/form-data for scenario contains multi binary parts.
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> binaryArrayPartsWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation
        // 'binaryArrayParts' is 'multipart/form-data'
        return this.serviceClient.binaryArrayPartsWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: multipart/form-data for scenario contains multi json parts.
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> jsonArrayPartsWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'jsonArrayParts'
        // is 'multipart/form-data'
        return this.serviceClient.jsonArrayPartsWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: multipart/form-data for scenario contains multi binary parts.
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> multiBinaryPartsWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation
        // 'multiBinaryParts' is 'multipart/form-data'
        return this.serviceClient.multiBinaryPartsWithResponse(body, requestOptions);
    }

    /**
     * Test content-type: multipart/form-data.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void basic(MultiPartRequest body) {
        // Generated convenience method for basicWithResponse
        RequestOptions requestOptions = new RequestOptions();
        basicWithResponse(new MultipartFormDataHelper(requestOptions).serializeTextField("id", body.getId())
            .serializeFileField("profileImage", body.getProfileImage(), body.getProfileImageFilename()).end()
            .getRequestBody(), requestOptions).getValue();
    }

    /**
     * Test content-type: multipart/form-data for mixed scenarios.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void complex(ComplexPartsRequest body) {
        // Generated convenience method for complexWithResponse
        RequestOptions requestOptions = new RequestOptions();
        complexWithResponse(new MultipartFormDataHelper(requestOptions).serializeTextField("id", body.getId())
            .serializeJsonField("address", body.getAddress())
            .serializeFileField("profileImage", body.getProfileImage(), body.getProfileImageFilename())
            .serializeJsonField("previousAddresses", body.getPreviousAddresses())
            .serializeFileFields("pictures", body.getPictures(), body.getPicturesFilenames()).end().getRequestBody(),
            requestOptions).getValue();
    }

    /**
     * Test content-type: multipart/form-data for scenario contains json part and binary part.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void jsonPart(JsonPartRequest body) {
        // Generated convenience method for jsonPartWithResponse
        RequestOptions requestOptions = new RequestOptions();
        jsonPartWithResponse(
            new MultipartFormDataHelper(requestOptions).serializeJsonField("address", body.getAddress())
                .serializeFileField("profileImage", body.getProfileImage(), body.getProfileImageFilename()).end()
                .getRequestBody(),
            requestOptions).getValue();
    }

    /**
     * Test content-type: multipart/form-data for scenario contains multi binary parts.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void binaryArrayParts(BinaryArrayPartsRequest body) {
        // Generated convenience method for binaryArrayPartsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        binaryArrayPartsWithResponse(new MultipartFormDataHelper(requestOptions).serializeTextField("id", body.getId())
            .serializeFileFields("pictures", body.getPictures(), body.getPicturesFilenames()).end().getRequestBody(),
            requestOptions).getValue();
    }

    /**
     * Test content-type: multipart/form-data for scenario contains multi json parts.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void jsonArrayParts(JsonArrayPartsRequest body) {
        // Generated convenience method for jsonArrayPartsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        jsonArrayPartsWithResponse(
            new MultipartFormDataHelper(requestOptions)
                .serializeFileField("profileImage", body.getProfileImage(), body.getProfileImageFilename())
                .serializeJsonField("previousAddresses", body.getPreviousAddresses()).end().getRequestBody(),
            requestOptions).getValue();
    }

    /**
     * Test content-type: multipart/form-data for scenario contains multi binary parts.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void multiBinaryParts(MultiBinaryPartsRequest body) {
        // Generated convenience method for multiBinaryPartsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        multiBinaryPartsWithResponse(
            new MultipartFormDataHelper(requestOptions)
                .serializeFileField("profileImage", body.getProfileImage(), body.getProfileImageFilename())
                .serializeFileField("picture", body.getPicture(), body.getPictureFilename()).end().getRequestBody(),
            requestOptions).getValue();
    }
}
