// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.llcupdate1;

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
import fixtures.llcupdate1.implementation.ParamsImpl;
import fixtures.llcupdate1.implementation.models.contentType;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous DpgClient type. */
@ServiceClient(builder = DpgClientBuilder.class, isAsync = true)
public final class DpgAsyncClient {
    @Generated private final ParamsImpl serviceClient;

    /**
     * Initializes an instance of DpgAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    DpgAsyncClient(ParamsImpl serviceClient) {
        this.serviceClient = serviceClient;
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> headNoParamsWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.headNoParamsWithResponseAsync(requestOptions);
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
     * Object
     * }</pre>
     *
     * @param parameter I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return true Boolean value on path. Initially only has one required Query Parameter along with {@link Response}
     *     on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredWithResponse(String parameter, RequestOptions requestOptions) {
        return this.serviceClient.getRequiredWithResponseAsync(parameter, requestOptions);
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
     * Object
     * }</pre>
     *
     * @param requiredParam I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putRequiredOptionalWithResponse(
            String requiredParam, RequestOptions requestOptions) {
        return this.serviceClient.putRequiredOptionalWithResponseAsync(requiredParam, requestOptions);
    }

    /**
     * POST a JSON or a JPEG.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param contentType The content type. Allowed values: "application/json", "image/jpeg".
     * @param parameter I am a body parameter with a new content type. My only valid JSON entry is { url:
     *     "http://example.org/myimage.jpeg" }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postParametersWithResponse(
            String contentType, BinaryData parameter, RequestOptions requestOptions) {
        return this.serviceClient.postParametersWithResponseAsync(contentType, parameter, requestOptions);
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteParametersWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.deleteParametersWithResponseAsync(requestOptions);
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
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return true Boolean value on path. Initially has one optional query parameter along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getOptionalWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getOptionalWithResponseAsync(requestOptions);
    }

    /**
     * I'm a new operation. Initiallty neither path or method exist for this operation. After evolution, this is a new
     * method in a new path.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNewOperationWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNewOperationWithResponseAsync(requestOptions);
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
     * @return any object on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> headNoParams(String newParameter) {
        // Generated convenience method for headNoParamsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return headNoParamsWithResponse(requestOptions).map(Response::getValue);
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
     * @return true Boolean value on path. Initially only has one required Query Parameter on successful completion of
     *     {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> getRequired(String parameter, String newParameter) {
        // Generated convenience method for getRequiredWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return getRequiredWithResponse(parameter, requestOptions).map(Response::getValue);
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
     * @return any object on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> putRequiredOptional(String requiredParam, String optionalParam, String newParameter) {
        // Generated convenience method for putRequiredOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (optionalParam != null) {
            requestOptions.addQueryParam("optionalParam", optionalParam);
        }
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return putRequiredOptionalWithResponse(requiredParam, requestOptions).map(Response::getValue);
    }

    /**
     * POST a JSON or a JPEG.
     *
     * @param contentType The content type.
     * @param parameter I am a body parameter with a new content type. My only valid JSON entry is { url:
     *     "http://example.org/myimage.jpeg" }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return any object on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> postParameters(contentType contentType, BinaryData parameter) {
        // Generated convenience method for postParametersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return postParametersWithResponse(contentType.toString(), parameter, requestOptions).map(Response::getValue);
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
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteParameters() {
        // Generated convenience method for deleteParametersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return deleteParametersWithResponse(requestOptions).then();
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
     * @return true Boolean value on path. Initially has one optional query parameter on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> getOptional(String optionalParam, String newParameter) {
        // Generated convenience method for getOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (optionalParam != null) {
            requestOptions.addQueryParam("optionalParam", optionalParam);
        }
        if (newParameter != null) {
            requestOptions.addQueryParam("new_parameter", newParameter);
        }
        return getOptionalWithResponse(requestOptions).map(Response::getValue);
    }

    /**
     * I'm a new operation. Initiallty neither path or method exist for this operation. After evolution, this is a new
     * method in a new path.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return any object on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> getNewOperation() {
        // Generated convenience method for getNewOperationWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getNewOperationWithResponse(requestOptions).map(Response::getValue);
    }
}
