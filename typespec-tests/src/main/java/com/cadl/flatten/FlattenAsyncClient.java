// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten;

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
import com.cadl.flatten.implementation.FlattenClientImpl;
import com.cadl.flatten.implementation.JsonMergePatchHelper;
import com.cadl.flatten.implementation.MultipartFormDataHelper;
import com.cadl.flatten.implementation.models.SendLongRequest;
import com.cadl.flatten.implementation.models.SendProjectedNameRequest;
import com.cadl.flatten.implementation.models.SendRequest;
import com.cadl.flatten.implementation.models.UploadFileRequest;
import com.cadl.flatten.implementation.models.UploadTodoRequest;
import com.cadl.flatten.models.FileDataFileDetails;
import com.cadl.flatten.models.SendLongOptions;
import com.cadl.flatten.models.TodoItem;
import com.cadl.flatten.models.UpdatePatchRequest;
import com.cadl.flatten.models.UploadTodoOptions;
import com.cadl.flatten.models.User;
import java.util.Objects;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous FlattenClient type.
 */
@ServiceClient(builder = FlattenClientBuilder.class, isAsync = true)
public final class FlattenAsyncClient {
    @Generated
    private final FlattenClientImpl serviceClient;

    /**
     * Initializes an instance of FlattenAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    FlattenAsyncClient(FlattenClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The send operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     constant: String (Required)
     * }
     * }</pre>
     * 
     * @param id A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendWithResponse(String id, BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.sendWithResponseAsync(id, request, requestOptions);
    }

    /**
     * The sendProjectedName operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     file_id: String (Required)
     * }
     * }</pre>
     * 
     * @param id A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendProjectedNameWithResponse(String id, BinaryData request,
        RequestOptions requestOptions) {
        return this.serviceClient.sendProjectedNameWithResponseAsync(id, request, requestOptions);
    }

    /**
     * The sendLong operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     dataInt: int (Required)
     *     dataIntOptional: Integer (Optional)
     *     dataLong: Long (Optional)
     *     data_float: Double (Optional)
     *     title: String (Required)
     *     description: String (Optional)
     *     status: String(NotStarted/InProgress/Completed) (Required)
     *     _dummy: String (Optional)
     *     constant: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendLongWithResponse(String name, BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.sendLongWithResponseAsync(name, request, requestOptions);
    }

    /**
     * The update operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     patch (Optional, Required on create): {
     *         title: String (Optional)
     *         description: String (Optional)
     *         status: String(NotStarted/InProgress/Completed) (Optional)
     *     }
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: long (Required)
     *     title: String (Required)
     *     description: String (Optional)
     *     status: String(NotStarted/InProgress/Completed) (Required)
     *     createdAt: OffsetDateTime (Required)
     *     updatedAt: OffsetDateTime (Required)
     *     completedAt: OffsetDateTime (Optional)
     *     _dummy: String (Optional)
     * }
     * }</pre>
     * 
     * @param id An integer that can be serialized to JSON (`−9007199254740991 (−(2^53 − 1))` to `9007199254740991 (2^53
     * − 1)` ).
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> updateWithResponse(long id, BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.updateWithResponseAsync(id, request, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> uploadFileWithResponse(String name, BinaryData request, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'uploadFile' is 'multipart/form-data'
        return this.serviceClient.uploadFileWithResponseAsync(name, request, requestOptions);
    }

    /**
     * The uploadTodo operation.
     * 
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> uploadTodoWithResponse(BinaryData request, RequestOptions requestOptions) {
        // Protocol API requires serialization of parts with content-disposition and data, as operation 'uploadTodo' is 'multipart/form-data'
        return this.serviceClient.uploadTodoWithResponseAsync(request, requestOptions);
    }

    /**
     * The send operation.
     * 
     * @param id A sequence of textual characters.
     * @param input A sequence of textual characters.
     * @param user The user parameter.
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
    public Mono<Void> send(String id, String input, User user) {
        // Generated convenience method for sendWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SendRequest requestObj = new SendRequest(input).setUser(user);
        BinaryData request = BinaryData.fromObject(requestObj);
        return sendWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The send operation.
     * 
     * @param id A sequence of textual characters.
     * @param input A sequence of textual characters.
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
    public Mono<Void> send(String id, String input) {
        // Generated convenience method for sendWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SendRequest requestObj = new SendRequest(input);
        BinaryData request = BinaryData.fromObject(requestObj);
        return sendWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The sendProjectedName operation.
     * 
     * @param id A sequence of textual characters.
     * @param fileIdentifier A sequence of textual characters.
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
    public Mono<Void> sendProjectedName(String id, String fileIdentifier) {
        // Generated convenience method for sendProjectedNameWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SendProjectedNameRequest requestObj = new SendProjectedNameRequest(fileIdentifier);
        BinaryData request = BinaryData.fromObject(requestObj);
        return sendProjectedNameWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The sendLong operation.
     * 
     * @param options Options for sendLong API.
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
    public Mono<Void> sendLong(SendLongOptions options) {
        // Generated convenience method for sendLongWithResponse
        RequestOptions requestOptions = new RequestOptions();
        String name = options.getName();
        String filter = options.getFilter();
        SendLongRequest requestObj
            = new SendLongRequest(options.getInput(), options.getDataInt(), options.getTitle(), options.getStatus())
                .setUser(options.getUser())
                .setDataIntOptional(options.getDataIntOptional())
                .setDataLong(options.getDataLong())
                .setDataFloat(options.getDataFloat())
                .setDescription(options.getDescription())
                .setDummy(options.getDummy());
        BinaryData request = BinaryData.fromObject(requestObj);
        if (filter != null) {
            requestOptions.addQueryParam("filter", filter, false);
        }
        return sendLongWithResponse(name, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The update operation.
     * 
     * @param id An integer that can be serialized to JSON (`−9007199254740991 (−(2^53 − 1))` to `9007199254740991 (2^53
     * − 1)` ).
     * @param request The request parameter.
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
    public Mono<TodoItem> update(long id, UpdatePatchRequest request) {
        // Generated convenience method for updateWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getUpdatePatchRequestAccessor().prepareModelForJsonMergePatch(request, true);
        BinaryData requestInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(request).toBytes());
        JsonMergePatchHelper.getUpdatePatchRequestAccessor().prepareModelForJsonMergePatch(request, false);
        return updateWithResponse(id, requestInBinaryData, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(TodoItem.class));
    }

    /**
     * The uploadFile operation.
     * 
     * @param name A sequence of textual characters.
     * @param fileData The file details for the "file_data" field.
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
    public Mono<Void> uploadFile(String name, FileDataFileDetails fileData) {
        // Generated convenience method for uploadFileWithResponse
        RequestOptions requestOptions = new RequestOptions();
        UploadFileRequest requestObj = new UploadFileRequest(fileData);
        BinaryData request = new MultipartFormDataHelper(requestOptions)
            .serializeFileField("file_data", requestObj.getFileData().getContent(),
                requestObj.getFileData().getContentType(), requestObj.getFileData().getFilename())
            .serializeTextField("constant", requestObj.getConstant())
            .end()
            .getRequestBody();
        return uploadFileWithResponse(name, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The uploadTodo operation.
     * 
     * @param options Options for uploadTodo API.
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
    public Mono<Void> uploadTodo(UploadTodoOptions options) {
        // Generated convenience method for uploadTodoWithResponse
        RequestOptions requestOptions = new RequestOptions();
        UploadTodoRequest requestObj
            = new UploadTodoRequest(options.getTitle(), options.getStatus()).setDescription(options.getDescription())
                .setDummy(options.getDummy())
                .setProp1(options.getProp1())
                .setProp2(options.getProp2())
                .setProp3(options.getProp3());
        BinaryData request
            = new MultipartFormDataHelper(requestOptions).serializeTextField("title", requestObj.getTitle())
                .serializeTextField("description", requestObj.getDescription())
                .serializeTextField("status", Objects.toString(requestObj.getStatus()))
                .serializeTextField("_dummy", requestObj.getDummy())
                .serializeTextField("prop1", requestObj.getProp1())
                .serializeTextField("prop2", requestObj.getProp2())
                .serializeTextField("prop3", requestObj.getProp3())
                .end()
                .getRequestBody();
        return uploadTodoWithResponse(request, requestOptions).flatMap(FluxUtil::toMono);
    }
}
