// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.llcresi;

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
import fixtures.llcresi.implementation.ParamsImpl;

/**
 * Initializes a new instance of the synchronous DpgClient type.
 */
@ServiceClient(builder = DpgClientBuilder.class)
public final class DpgClient {
    @Generated
    private final ParamsImpl serviceClient;

    /**
     * Initializes an instance of DpgClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DpgClient(ParamsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Head request, no params.
     * Initially has no query parameters. After evolution, a new optional query parameter is added.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * BinaryData
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> headNoParamsWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.headNoParamsWithResponse(requestOptions);
    }

    /**
     * Get true Boolean value on path.
     * Initially only has one required Query Parameter. After evolution, a new optional query parameter is added.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * BinaryData
     * }
     * </pre>
     * 
     * @param parameter I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return true Boolean value on path.
     * Initially only has one required Query Parameter along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredWithResponse(String parameter, RequestOptions requestOptions) {
        return this.serviceClient.getRequiredWithResponse(parameter, requestOptions);
    }

    /**
     * Initially has one required query parameter and one optional query parameter. After evolution, a new optional
     * query parameter is added.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>optionalParam</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * BinaryData
     * }
     * </pre>
     * 
     * @param requiredParam I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putRequiredOptionalWithResponse(String requiredParam, RequestOptions requestOptions) {
        return this.serviceClient.putRequiredOptionalWithResponse(requiredParam, requestOptions);
    }

    /**
     * POST a JSON.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     url: String (Required)
     * }
     * }
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * BinaryData
     * }
     * </pre>
     * 
     * @param parameter I am a body parameter. My only valid JSON entry is { url: "http://example.org/myimage.jpeg" }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return any object along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postParametersWithResponse(BinaryData parameter, RequestOptions requestOptions) {
        return this.serviceClient.postParametersWithResponse(parameter, requestOptions);
    }

    /**
     * Get true Boolean value on path.
     * Initially has one optional query parameter. After evolution, a new optional query parameter is added.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>optionalParam</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * BinaryData
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return true Boolean value on path.
     * Initially has one optional query parameter along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getOptionalWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getOptionalWithResponse(requestOptions);
    }
}
