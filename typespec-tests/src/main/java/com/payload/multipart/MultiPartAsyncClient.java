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
import com.azure.core.util.FluxUtil;
import com.payload.multipart.implementation.FormDatasImpl;
import com.payload.multipart.implementation.MultipartFormDataHelper;
import com.payload.multipart.models.BinaryArrayPartsRequest;
import com.payload.multipart.models.ComplexPartsRequest;
import com.payload.multipart.models.JsonArrayPartsRequest;
import com.payload.multipart.models.JsonPartRequest;
import com.payload.multipart.models.MultiBinaryPartsRequest;
import com.payload.multipart.models.MultiPartRequest;
import com.payload.multipart.models.PicturesFileDetails;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous MultiPartClient type.
 */
@ServiceClient(builder = MultiPartClientBuilder.class, isAsync = true)
public final class MultiPartAsyncClient {
    @Generated
    private final FormDatasImpl serviceClient;

    /**
     * Initializes an instance of MultiPartAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultiPartAsyncClient(FormDatasImpl serviceClient) {
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> basicWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'basic' is
        // 'multipart/form-data'
        return this.serviceClient.basicWithResponseAsync(body, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> complexWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'complex' is
        // 'multipart/form-data'
        return this.serviceClient.complexWithResponseAsync(body, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> jsonPartWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'jsonPart' is
        // 'multipart/form-data'
        return this.serviceClient.jsonPartWithResponseAsync(body, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> binaryArrayPartsWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation
        // 'binaryArrayParts' is 'multipart/form-data'
        return this.serviceClient.binaryArrayPartsWithResponseAsync(body, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> jsonArrayPartsWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'jsonArrayParts'
        // is 'multipart/form-data'
        return this.serviceClient.jsonArrayPartsWithResponseAsync(body, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> multiBinaryPartsWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation
        // 'multiBinaryParts' is 'multipart/form-data'
        return this.serviceClient.multiBinaryPartsWithResponseAsync(body, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> checkFileNameAndContentTypeWithResponse(BinaryData body, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation
        // 'checkFileNameAndContentType' is 'multipart/form-data'
        return this.serviceClient.checkFileNameAndContentTypeWithResponseAsync(body, requestOptions);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> basic(MultiPartRequest body) {
        // Generated convenience method for basicWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return basicWithResponse(new MultipartFormDataHelper(requestOptions).serializeTextField("id", body.getId())
            .serializeFileField("profileImage", body.getProfileImage().getContent(),
                body.getProfileImage().getContentType(), body.getProfileImage().getFilename())
            .end().getRequestBody(), requestOptions).flatMap(FluxUtil::toMono);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> complex(ComplexPartsRequest body) {
        // Generated convenience method for complexWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return complexWithResponse(new MultipartFormDataHelper(requestOptions).serializeTextField("id", body.getId())
            .serializeJsonField("address", body.getAddress())
            .serializeFileField("profileImage", body.getProfileImage().getContent(),
                body.getProfileImage().getContentType(), body.getProfileImage().getFilename())
            .serializeJsonField("previousAddresses", body.getPreviousAddresses())
            .serializeFileFields("pictures",
                body.getPictures().stream().map(PicturesFileDetails::getContent).collect(Collectors.toList()),
                body.getPictures().stream().map(PicturesFileDetails::getContentType).collect(Collectors.toList()),
                body.getPictures().stream().map(PicturesFileDetails::getFilename).collect(Collectors.toList()))
            .end().getRequestBody(), requestOptions).flatMap(FluxUtil::toMono);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> jsonPart(JsonPartRequest body) {
        // Generated convenience method for jsonPartWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return jsonPartWithResponse(new MultipartFormDataHelper(requestOptions)
            .serializeJsonField("address", body.getAddress())
            .serializeFileField("profileImage", body.getProfileImage().getContent(),
                body.getProfileImage().getContentType(), body.getProfileImage().getFilename())
            .end().getRequestBody(), requestOptions).flatMap(FluxUtil::toMono);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> binaryArrayParts(BinaryArrayPartsRequest body) {
        // Generated convenience method for binaryArrayPartsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return binaryArrayPartsWithResponse(
            new MultipartFormDataHelper(requestOptions).serializeTextField("id", body.getId())
                .serializeFileFields("pictures",
                    body.getPictures().stream().map(PicturesFileDetails::getContent).collect(Collectors.toList()),
                    body.getPictures().stream().map(PicturesFileDetails::getContentType).collect(Collectors.toList()),
                    body.getPictures().stream().map(PicturesFileDetails::getFilename).collect(Collectors.toList()))
                .end().getRequestBody(),
            requestOptions).flatMap(FluxUtil::toMono);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> jsonArrayParts(JsonArrayPartsRequest body) {
        // Generated convenience method for jsonArrayPartsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return jsonArrayPartsWithResponse(
            new MultipartFormDataHelper(requestOptions)
                .serializeFileField("profileImage", body.getProfileImage().getContent(),
                    body.getProfileImage().getContentType(), body.getProfileImage().getFilename())
                .serializeJsonField("previousAddresses", body.getPreviousAddresses()).end().getRequestBody(),
            requestOptions).flatMap(FluxUtil::toMono);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> multiBinaryParts(MultiBinaryPartsRequest body) {
        // Generated convenience method for multiBinaryPartsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return multiBinaryPartsWithResponse(
            new MultipartFormDataHelper(requestOptions)
                .serializeFileField("profileImage", body.getProfileImage().getContent(),
                    body.getProfileImage().getContentType(), body.getProfileImage().getFilename())
                .serializeFileField("picture", body.getPicture() == null ? null : body.getPicture().getContent(),
                    body.getPicture() == null ? null : body.getPicture().getContentType(),
                    body.getPicture() == null ? null : body.getPicture().getFilename())
                .end().getRequestBody(),
            requestOptions).flatMap(FluxUtil::toMono);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> checkFileNameAndContentType(MultiPartRequest body) {
        // Generated convenience method for checkFileNameAndContentTypeWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return checkFileNameAndContentTypeWithResponse(new MultipartFormDataHelper(requestOptions)
            .serializeTextField("id", body.getId())
            .serializeFileField("profileImage", body.getProfileImage().getContent(),
                body.getProfileImage().getContentType(), body.getProfileImage().getFilename())
            .end().getRequestBody(), requestOptions).flatMap(FluxUtil::toMono);
    }
}
