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
import com.azure.core.annotation.QueryParam;
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
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Basics.
 */
public final class BasicsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BasicsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestServiceClientImpl client;

    /**
     * Initializes an instance of BasicsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    BasicsImpl(AutoRestComplexTestServiceClientImpl client) {
        this.service = RestProxy.create(BasicsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceBasics to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    public interface BasicsService {
        @Get("/complex/basic/valid")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getValid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/valid")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getValidSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/complex/basic/valid")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host, @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") BinaryData complexBody, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/complex/basic/valid")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> putValidSync(@HostParam("$host") String host, @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") BinaryData complexBody, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/invalid")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getInvalid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/invalid")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getInvalidSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/empty")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getEmpty(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/empty")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getEmptySync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/null")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getNull(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/null")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getNullSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/notprovided")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getNotProvided(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/complex/basic/notprovided")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getNotProvidedSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getValid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getValidSync(this.client.getHost(), accept, requestOptions, Context.NONE);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
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
        return FluxUtil.withContext(context -> service.putValid(this.client.getHost(),
            this.client.getServiceVersion().getVersion(), complexBody, accept, requestOptions, context));
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
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
        return service.putValidSync(this.client.getHost(), this.client.getServiceVersion().getVersion(), complexBody,
            accept, requestOptions, Context.NONE);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getInvalidWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getInvalid(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getInvalidWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getInvalidSync(this.client.getHost(), accept, requestOptions, Context.NONE);
    }

    /**
     * Get a basic complex type that is empty.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type that is empty along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getEmpty(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type that is empty.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type that is empty along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getEmptyWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getEmptySync(this.client.getHost(), accept, requestOptions, Context.NONE);
    }

    /**
     * Get a basic complex type whose properties are null.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type whose properties are null along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type whose properties are null.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type whose properties are null along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNullWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getNullSync(this.client.getHost(), accept, requestOptions, Context.NONE);
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getNotProvided(this.client.getHost(), accept, requestOptions, context));
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNotProvidedWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getNotProvidedSync(this.client.getHost(), accept, requestOptions, Context.NONE);
    }
}
