// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredfieldsascotrargstransformation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import fixtures.requiredfieldsascotrargstransformation.models.TransformationAsParentRequiredFields;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the RequiredFieldsAsCtorArgsTransformation type.
 */
public final class RequiredFieldsAsCtorArgsTransformation {
    /**
     * The proxy service used to perform REST calls.
     */
    private final RequiredFieldsAsCtorArgsTransformationService service;

    /**
     * server parameter.
     */
    private final String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The serializer to serialize an object into a string.
     */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     * 
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * Initializes an instance of RequiredFieldsAsCtorArgsTransformation client.
     * 
     * @param host server parameter.
     */
    RequiredFieldsAsCtorArgsTransformation(String host) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of RequiredFieldsAsCtorArgsTransformation client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    RequiredFieldsAsCtorArgsTransformation(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of RequiredFieldsAsCtorArgsTransformation client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    RequiredFieldsAsCtorArgsTransformation(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter,
        String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.service = RestProxy.create(RequiredFieldsAsCtorArgsTransformationService.class, this.httpPipeline,
            this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for RequiredFieldsAsCtorArgsTransformation to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "RequiredFieldsAsCtorArgsTransformation")
    public interface RequiredFieldsAsCtorArgsTransformationService {
        @Get("/argstransformation")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<TransformationAsParentRequiredFields>> get(@HostParam("$host") String host,
            @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * get methods.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return methods along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TransformationAsParentRequiredFields>> getWithResponseAsync() {
        return FluxUtil.withContext(context -> getWithResponseAsync(context));
    }

    /**
     * get methods.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return methods along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TransformationAsParentRequiredFields>> getWithResponseAsync(Context context) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.get(this.getHost(), accept, context);
    }

    /**
     * get methods.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return methods on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TransformationAsParentRequiredFields> getAsync() {
        return getWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * get methods.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return methods on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TransformationAsParentRequiredFields> getAsync(Context context) {
        return getWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * get methods.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return methods along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TransformationAsParentRequiredFields> getWithResponse(Context context) {
        return getWithResponseAsync(context).block();
    }

    /**
     * get methods.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return methods.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TransformationAsParentRequiredFields get() {
        return getWithResponse(Context.NONE).getValue();
    }
}
