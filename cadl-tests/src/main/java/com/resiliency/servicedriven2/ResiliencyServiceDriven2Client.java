// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.resiliency.servicedriven2;

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
import com.resiliency.servicedriven2.models.ContentTypePath;
import com.resiliency.servicedriven2.models.Message;
import com.resiliency.servicedriven2.models.PostInput;

/** Initializes a new instance of the synchronous ResiliencyServiceDriven2Client type. */
@ServiceClient(builder = ResiliencyServiceDriven2ClientBuilder.class)
public final class ResiliencyServiceDriven2Client {
    @Generated private final ResiliencyServiceDriven2AsyncClient client;

    /**
     * Initializes an instance of ResiliencyServiceDriven2Client class.
     *
     * @param client the async client.
     */
    @Generated
    ResiliencyServiceDriven2Client(ResiliencyServiceDriven2AsyncClient client) {
        this.client = client;
    }

    /**
     * Head request, no params. Initially has no query parameters. After evolution, a new optional query parameter is
     * added.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>new_parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> headNoParamsWithResponse(RequestOptions requestOptions) {
        return this.client.headNoParamsWithResponse(requestOptions).block();
    }

    /**
     * Get true Boolean value on path. Initially only has one required Query Parameter. After evolution, a new optional
     * query parameter is added.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>new_parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
     * }
     * }</pre>
     *
     * @param parameter I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return true Boolean value on path. Initially only has one required Query Parameter along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredWithResponse(String parameter, RequestOptions requestOptions) {
        return this.client.getRequiredWithResponse(parameter, requestOptions).block();
    }

    /**
     * Initially has one required query parameter and one optional query parameter. After evolution, a new optional
     * query parameter is added.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>optionalParam</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     *     <tr><td>new_parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
     * }
     * }</pre>
     *
     * @param requiredParam I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putRequiredOptionalWithResponse(String requiredParam, RequestOptions requestOptions) {
        return this.client.putRequiredOptionalWithResponse(requiredParam, requestOptions).block();
    }

    /**
     * POST a JSON or a JPEG.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     url: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
     * }
     * }</pre>
     *
     * @param contentTypePath The contentTypePath parameter. Allowed values: "json", "jpeg".
     * @param parameter I am a body parameter. My only valid JSON entry is { url: "http://example.org/myimage.jpeg" }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postParametersWithResponse(
            String contentTypePath, BinaryData parameter, RequestOptions requestOptions) {
        return this.client.postParametersWithResponse(contentTypePath, parameter, requestOptions).block();
    }

    /**
     * Delete something. Initially the path exists but there is no delete method. After evolution this is a new method
     * in a known path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteParametersWithResponse(RequestOptions requestOptions) {
        return this.client.deleteParametersWithResponse(requestOptions).block();
    }

    /**
     * Get true Boolean value on path. Initially has one optional query parameter. After evolution, a new optional query
     * parameter is added.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>optionalParam</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     *     <tr><td>new_parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return true Boolean value on path. Initially has one optional query parameter along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getOptionalWithResponse(RequestOptions requestOptions) {
        return this.client.getOptionalWithResponse(requestOptions).block();
    }

    /**
     * I'm a new operation. Initially neither path or method exist for this operation. After evolution, this is a new
     * method in a new path.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
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
    public Response<BinaryData> getNewOperationWithResponse(RequestOptions requestOptions) {
        return this.client.getNewOperationWithResponse(requestOptions).block();
    }

    /**
     * Head request, no params. Initially has no query parameters. After evolution, a new optional query parameter is
     * added.
     *
     * @param newParameter I'm a new input optional parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void headNoParams(String newParameter) {
        // Generated convenience method for headNoParamsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        headNoParamsWithResponse(requestOptions).getValue();
    }

    /**
     * Head request, no params. Initially has no query parameters. After evolution, a new optional query parameter is
     * added.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void headNoParams() {
        // Generated convenience method for headNoParamsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        headNoParamsWithResponse(requestOptions).getValue();
    }

    /**
     * Get true Boolean value on path. Initially only has one required Query Parameter. After evolution, a new optional
     * query parameter is added.
     *
     * @param parameter I am a required parameter.
     * @param newParameter I'm a new input optional parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value on path. Initially only has one required Query Parameter.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message getRequired(String parameter, String newParameter) {
        // Generated convenience method for getRequiredWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return getRequiredWithResponse(parameter, requestOptions).getValue().toObject(Message.class);
    }

    /**
     * Get true Boolean value on path. Initially only has one required Query Parameter. After evolution, a new optional
     * query parameter is added.
     *
     * @param parameter I am a required parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value on path. Initially only has one required Query Parameter.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message getRequired(String parameter) {
        // Generated convenience method for getRequiredWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getRequiredWithResponse(parameter, requestOptions).getValue().toObject(Message.class);
    }

    /**
     * Initially has one required query parameter and one optional query parameter. After evolution, a new optional
     * query parameter is added.
     *
     * @param requiredParam I am a required parameter.
     * @param optionalParam I am an optional parameter.
     * @param newParameter I'm a new input optional parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message putRequiredOptional(String requiredParam, String optionalParam, String newParameter) {
        // Generated convenience method for putRequiredOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (optionalParam != null) {
            requestOptions.addQueryParam("optionalParam", optionalParam);
        }
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return putRequiredOptionalWithResponse(requiredParam, requestOptions).getValue().toObject(Message.class);
    }

    /**
     * Initially has one required query parameter and one optional query parameter. After evolution, a new optional
     * query parameter is added.
     *
     * @param requiredParam I am a required parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message putRequiredOptional(String requiredParam) {
        // Generated convenience method for putRequiredOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putRequiredOptionalWithResponse(requiredParam, requestOptions).getValue().toObject(Message.class);
    }

    /**
     * POST a JSON or a JPEG.
     *
     * @param contentTypePath The contentTypePath parameter.
     * @param parameter I am a body parameter. My only valid JSON entry is { url: "http://example.org/myimage.jpeg" }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message postParameters(ContentTypePath contentTypePath, PostInput parameter) {
        // Generated convenience method for postParametersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return postParametersWithResponse(contentTypePath.toString(), BinaryData.fromObject(parameter), requestOptions)
                .getValue()
                .toObject(Message.class);
    }

    /**
     * Delete something. Initially the path exists but there is no delete method. After evolution this is a new method
     * in a known path.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteParameters() {
        // Generated convenience method for deleteParametersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        deleteParametersWithResponse(requestOptions).getValue();
    }

    /**
     * Get true Boolean value on path. Initially has one optional query parameter. After evolution, a new optional query
     * parameter is added.
     *
     * @param optionalParam I am an optional parameter.
     * @param newParameter I'm a new input optional parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value on path. Initially has one optional query parameter.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message getOptional(String optionalParam, String newParameter) {
        // Generated convenience method for getOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (optionalParam != null) {
            requestOptions.addQueryParam("optionalParam", optionalParam);
        }
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return getOptionalWithResponse(requestOptions).getValue().toObject(Message.class);
    }

    /**
     * Get true Boolean value on path. Initially has one optional query parameter. After evolution, a new optional query
     * parameter is added.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value on path. Initially has one optional query parameter.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message getOptional() {
        // Generated convenience method for getOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getOptionalWithResponse(requestOptions).getValue().toObject(Message.class);
    }

    /**
     * I'm a new operation. Initially neither path or method exist for this operation. After evolution, this is a new
     * method in a new path.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Message getNewOperation() {
        // Generated convenience method for getNewOperationWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getNewOperationWithResponse(requestOptions).getValue().toObject(Message.class);
    }
}
