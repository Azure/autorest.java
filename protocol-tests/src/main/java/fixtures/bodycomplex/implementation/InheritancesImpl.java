// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;

import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;

import fixtures.bodycomplex.AutoRestComplexTestServiceVersion;

import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Inheritances.
 */
public final class InheritancesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final InheritancesService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestServiceClientImpl client;

    /**
     * Initializes an instance of InheritancesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    InheritancesImpl(AutoRestComplexTestServiceClientImpl client) {
        this.service
            = RestProxy.create(InheritancesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public AutoRestComplexTestServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceInheritances to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    public interface InheritancesService {
        @Get("/complex/inheritance/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getValid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/inheritance/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getValidSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/complex/inheritance/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host,
            @BodyParam("application/json") BinaryData complexBody, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/complex/inheritance/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> putValidSync(@HostParam("$host") String host,
            @BodyParam("application/json") BinaryData complexBody, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);
    }

    /**
     * Get complex types that extend others.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String (Optional)
     *     hates (Optional): [
     *          (Optional){
     *             id: Integer (Optional)
     *             name: String (Optional)
     *             food: String (Optional)
     *         }
     *     ]
     *     breed: String (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types that extend others along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getValid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get complex types that extend others.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String (Optional)
     *     hates (Optional): [
     *          (Optional){
     *             id: Integer (Optional)
     *             name: String (Optional)
     *             food: String (Optional)
     *         }
     *     ]
     *     breed: String (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types that extend others along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getValidSync(this.client.getHost(), accept, requestOptions, Context.NONE);
    }

    /**
     * Put complex types that extend others.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String (Optional)
     *     hates (Optional): [
     *          (Optional){
     *             id: Integer (Optional)
     *             name: String (Optional)
     *             food: String (Optional)
     *         }
     *     ]
     *     breed: String (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put a siamese with id=2, name="Siameee", color=green, breed=persion, which hates 2
     * dogs, the 1st one named "Potato" with id=1 and food="tomato", and the 2nd one named "Tomato" with id=-1 and
     * food="french fries".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(BinaryData complexBody, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
            context -> service.putValid(this.client.getHost(), complexBody, accept, requestOptions, context));
    }

    /**
     * Put complex types that extend others.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String (Optional)
     *     hates (Optional): [
     *          (Optional){
     *             id: Integer (Optional)
     *             name: String (Optional)
     *             food: String (Optional)
     *         }
     *     ]
     *     breed: String (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put a siamese with id=2, name="Siameee", color=green, breed=persion, which hates 2
     * dogs, the 1st one named "Potato" with id=1 and food="tomato", and the 2nd one named "Tomato" with id=-1 and
     * food="french fries".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.putValidSync(this.client.getHost(), complexBody, accept, requestOptions, Context.NONE);
    }
}
