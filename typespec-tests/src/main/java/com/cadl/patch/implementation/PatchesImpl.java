// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.patch.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Patches.
 */
public final class PatchesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PatchesService service;

    /**
     * The service client containing this operation class.
     */
    private final PatchClientImpl client;

    /**
     * Initializes an instance of PatchesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PatchesImpl(PatchClientImpl client) {
        this.service = RestProxy.create(PatchesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for PatchClientPatches to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "PatchClientPatches")
    public interface PatchesService {
        @Patch("/patch/resource")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> createOrUpdateResource(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/merge-patch+json") BinaryData resource, RequestOptions requestOptions,
            Context context);

        @Patch("/patch/resource")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> createOrUpdateResourceSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/merge-patch+json") BinaryData resource, RequestOptions requestOptions,
            Context context);

        @Patch("/patch/resource/optional")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> createOrUpdateOptionalResource(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Patch("/patch/resource/optional")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> createOrUpdateOptionalResourceSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Patch("/patch/fish")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> createOrUpdateFish(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/merge-patch+json") BinaryData fish, RequestOptions requestOptions, Context context);

        @Patch("/patch/fish")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> createOrUpdateFishSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/merge-patch+json") BinaryData fish, RequestOptions requestOptions, Context context);
    }

    /**
     * The createOrUpdateResource operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * 
     * @param resource The resource parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateResourceWithResponseAsync(BinaryData resource,
        RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.createOrUpdateResource(this.client.getEndpoint(), contentType,
            accept, resource, requestOptions, context));
    }

    /**
     * The createOrUpdateResource operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * 
     * @param resource The resource parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateResourceWithResponse(BinaryData resource, RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        return service.createOrUpdateResourceSync(this.client.getEndpoint(), contentType, accept, resource,
            requestOptions, Context.NONE);
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateOptionalResourceWithResponseAsync(RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getBody() != null && requestLocal.getHeaders().get(HttpHeaderName.CONTENT_TYPE) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.CONTENT_TYPE, "application/merge-patch+json");
            }
        });
        return FluxUtil.withContext(context -> service.createOrUpdateOptionalResource(this.client.getEndpoint(),
            contentType, accept, requestOptionsLocal, context));
    }

    /**
     * The createOrUpdateOptionalResource operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     map (Optional, Required on create): {
     *         String (Required): {
     *             name: String (Optional, Required on create)
     *             description: String (Optional)
     *         }
     *     }
     *     longValue: Long (Optional)
     *     intValue: Integer (Optional)
     *     enumValue: String(a/b/c) (Optional)
     *     wireNameForInnerModelProperty (Optional): (recursive schema, see wireNameForInnerModelProperty above)
     *     array (Optional): [
     *         (recursive schema, see above)
     *     ]
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateOptionalResourceWithResponse(RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getBody() != null && requestLocal.getHeaders().get(HttpHeaderName.CONTENT_TYPE) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.CONTENT_TYPE, "application/merge-patch+json");
            }
        });
        return service.createOrUpdateOptionalResourceSync(this.client.getEndpoint(), contentType, accept,
            requestOptionsLocal, Context.NONE);
    }

    /**
     * The createOrUpdateFish operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Optional, Required on create)
     *     color: String (Optional)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Optional, Required on create)
     *     color: String (Optional)
     * }
     * }</pre>
     * 
     * @param fish This is base model for polymorphic multiple levels inheritance with a discriminator.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateFishWithResponseAsync(BinaryData fish,
        RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.createOrUpdateFish(this.client.getEndpoint(), contentType,
            accept, fish, requestOptions, context));
    }

    /**
     * The createOrUpdateFish operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Optional, Required on create)
     *     color: String (Optional)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     id: String (Required)
     *     name: String (Required)
     *     age: int (Optional, Required on create)
     *     color: String (Optional)
     * }
     * }</pre>
     * 
     * @param fish This is base model for polymorphic multiple levels inheritance with a discriminator.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with
     * {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateFishWithResponse(BinaryData fish, RequestOptions requestOptions) {
        final String contentType = "application/merge-patch+json";
        final String accept = "application/json";
        return service.createOrUpdateFishSync(this.client.getEndpoint(), contentType, accept, fish, requestOptions,
            Context.NONE);
    }
}
