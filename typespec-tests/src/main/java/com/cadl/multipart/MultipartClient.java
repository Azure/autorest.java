// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multipart;

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
import com.cadl.multipart.implementation.MultipartClientImpl;
import com.cadl.multipart.implementation.MultipartFormDataHelper;
import com.cadl.multipart.implementation.models.UploadFileRequest;
import com.cadl.multipart.models.FileDataFileDetails;
import com.cadl.multipart.models.FileDetails;
import com.cadl.multipart.models.FormData;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Initializes a new instance of the synchronous MultipartClient type.
 */
@ServiceClient(builder = MultipartClientBuilder.class)
public final class MultipartClient {
    @Generated
    private final MultipartClientImpl serviceClient;

    /**
     * Initializes an instance of MultipartClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultipartClient(MultipartClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The upload operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>compress</td><td>Boolean</td><td>No</td><td>Boolean with `true` and `false` values.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * 
     * @param name A sequence of textual characters.
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> uploadWithResponse(String name, BinaryData data, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'upload' is 'multipart/form-data'
        return this.serviceClient.uploadWithResponse(name, data, requestOptions);
    }

    /**
     * The uploadFile operation.
     * 
     * @param name A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<Void> uploadFileWithResponse(String name, BinaryData request, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'uploadFile' is 'multipart/form-data'
        return this.serviceClient.uploadFileWithResponse(name, request, requestOptions);
    }

    /**
     * The upload operation.
     * 
     * @param name A sequence of textual characters.
     * @param data The data parameter.
     * @param compress Boolean with `true` and `false` values.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void upload(String name, FormData data, Boolean compress) {
        // Generated convenience method for uploadWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (compress != null) {
            requestOptions.addQueryParam("compress", String.valueOf(compress), false);
        }
        uploadWithResponse(name,
            new MultipartFormDataHelper(requestOptions).serializeTextField("name", data.getName())
                .serializeTextField("resolution", String.valueOf(data.getResolution()))
                .serializeTextField("type", Objects.toString(data.getType()))
                .serializeJsonField("size", data.getSize())
                .serializeFileField("image", data.getImage().getContent(), data.getImage().getContentType(),
                    data.getImage().getFilename())
                .serializeFileFields("file",
                    data.getFile() == null
                        ? null
                        : data.getFile().stream().map(FileDetails::getContent).collect(Collectors.toList()),
                    data.getFile() == null
                        ? null
                        : data.getFile().stream().map(FileDetails::getContentType).collect(Collectors.toList()),
                    data.getFile() == null
                        ? null
                        : data.getFile().stream().map(FileDetails::getFilename).collect(Collectors.toList()))
                .end()
                .getRequestBody(),
            requestOptions).getValue();
    }

    /**
     * The upload operation.
     * 
     * @param name A sequence of textual characters.
     * @param data The data parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void upload(String name, FormData data) {
        // Generated convenience method for uploadWithResponse
        RequestOptions requestOptions = new RequestOptions();
        uploadWithResponse(name,
            new MultipartFormDataHelper(requestOptions).serializeTextField("name", data.getName())
                .serializeTextField("resolution", String.valueOf(data.getResolution()))
                .serializeTextField("type", Objects.toString(data.getType()))
                .serializeJsonField("size", data.getSize())
                .serializeFileField("image", data.getImage().getContent(), data.getImage().getContentType(),
                    data.getImage().getFilename())
                .serializeFileFields("file",
                    data.getFile() == null
                        ? null
                        : data.getFile().stream().map(FileDetails::getContent).collect(Collectors.toList()),
                    data.getFile() == null
                        ? null
                        : data.getFile().stream().map(FileDetails::getContentType).collect(Collectors.toList()),
                    data.getFile() == null
                        ? null
                        : data.getFile().stream().map(FileDetails::getFilename).collect(Collectors.toList()))
                .end()
                .getRequestBody(),
            requestOptions).getValue();
    }

    /**
     * The uploadFile operation.
     * 
     * @param name A sequence of textual characters.
     * @param fileData Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void uploadFile(String name, FileDataFileDetails fileData) {
        // Generated convenience method for uploadFileWithResponse
        RequestOptions requestOptions = new RequestOptions();
        UploadFileRequest requestObj = new UploadFileRequest(fileData);
        BinaryData request = new MultipartFormDataHelper(requestOptions)
            .serializeFileField("file_data", requestObj.getFileData().getContent(),
                requestObj.getFileData().getContentType(), requestObj.getFileData().getFilename())
            .end()
            .getRequestBody();
        uploadFileWithResponse(name, request, requestOptions).getValue();
    }
}
