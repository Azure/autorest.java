// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure;

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

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestServiceClient type. */
@ServiceClient(builder = MultipleResponsesClientBuilder.class)
public final class MultipleResponsesClient {
    @Generated private final MultipleResponsesAsyncClient asyncClient;

    /**
     * Initializes an instance of MultipleResponsesClient class.
     *
     * @param asyncClient the async client.
     */
    @Generated
    MultipleResponsesClient(MultipleResponsesAsyncClient asyncClient) {
        this.asyncClient = asyncClient;
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model204NoModelDefaultError200ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model204NoModelDefaultError200ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model204NoModelDefaultError204ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model204NoModelDefaultError204ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model204NoModelDefaultError201InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model204NoModelDefaultError201InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model204NoModelDefaultError202NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model204NoModelDefaultError202NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model204NoModelDefaultError400ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model204NoModelDefaultError400ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model201ModelDefaultError200ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model201ModelDefaultError200ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model201ModelDefaultError201ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model201ModelDefaultError201ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200Model201ModelDefaultError400ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200Model201ModelDefaultError400ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
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
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponse(
            RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA201ModelC404ModelDDefaultError200ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
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
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponse(
            RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA201ModelC404ModelDDefaultError201ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
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
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponse(
            RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA201ModelC404ModelDDefaultError404ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
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
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponse(
            RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA201ModelC404ModelDDefaultError400ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 202 response with no payload.
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
    public Response<Void> get202None204NoneDefaultError202NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultError202NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 204 response with no payload.
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
    public Response<Void> get202None204NoneDefaultError204NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultError204NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
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
    public Response<Void> get202None204NoneDefaultError400ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultError400ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 202 response with an unexpected payload {'property': 'value'}.
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
    public Response<Void> get202None204NoneDefaultNone202InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultNone202InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 204 response with no payload.
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
    public Response<Void> get202None204NoneDefaultNone204NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultNone204NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with no payload.
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
    public Response<Void> get202None204NoneDefaultNone400NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultNone400NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with an unexpected payload {'property': 'value'}.
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
    public Response<Void> get202None204NoneDefaultNone400InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get202None204NoneDefaultNone400InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> getDefaultModelA200ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultModelA200ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> getDefaultModelA200NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultModelA200NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
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
    public Response<Void> getDefaultModelA400ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultModelA400ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with no payload.
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
    public Response<Void> getDefaultModelA400NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultModelA400NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with invalid payload: {'statusCode': '200'}.
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
    public Response<Void> getDefaultNone200InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultNone200InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with no payload.
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
    public Response<Void> getDefaultNone200NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultNone200NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
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
    public Response<Void> getDefaultNone400InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultNone400InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with no payload.
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
    public Response<Void> getDefaultNone400NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.getDefaultNone400NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA200NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA200NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA200ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA200ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA200InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA200InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA400NoneWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA400NoneWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA400ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA400ValidWithResponse(requestOptions).block();
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA400InvalidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA400InvalidWithResponse(requestOptions).block();
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
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
    public Response<BinaryData> get200ModelA202ValidWithResponse(RequestOptions requestOptions) {
        return this.asyncClient.get200ModelA202ValidWithResponse(requestOptions).block();
    }
}
