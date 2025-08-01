// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization;

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
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import fixtures.streamstyleserialization.models.DictionaryWrapper;
import fixtures.streamstyleserialization.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Dictionaries.
 */
public final class Dictionaries {
    /**
     * The proxy service used to perform REST calls.
     */
    private final DictionariesService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Dictionaries.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Dictionaries(AutoRestComplexTestService client) {
        this.service
            = RestProxy.create(DictionariesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceDictionaries to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServiceDictionaries")
    public interface DictionariesService {
        @Get("/complex/dictionary/typed/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getValid(@HostParam("$host") String host,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/dictionary/typed/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Response<DictionaryWrapper> getValidSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/complex/dictionary/typed/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host,
            @BodyParam("application/json") DictionaryWrapper complexBody, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/complex/dictionary/typed/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Response<Void> putValidSync(@HostParam("$host") String host,
            @BodyParam("application/json") DictionaryWrapper complexBody, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/dictionary/typed/empty")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getEmpty(@HostParam("$host") String host,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/dictionary/typed/empty")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Response<DictionaryWrapper> getEmptySync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/complex/dictionary/typed/empty")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String host,
            @BodyParam("application/json") DictionaryWrapper complexBody, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/complex/dictionary/typed/empty")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Response<Void> putEmptySync(@HostParam("$host") String host,
            @BodyParam("application/json") DictionaryWrapper complexBody, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/dictionary/typed/null")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getNull(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/dictionary/typed/null")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Response<DictionaryWrapper> getNullSync(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/dictionary/typed/notprovided")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getNotProvided(@HostParam("$host") String host,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/dictionary/typed/notprovided")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Response<DictionaryWrapper> getNotProvidedSync(@HostParam("$host") String host,
            @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getValidWithResponseAsync() {
        return FluxUtil.withContext(context -> getValidWithResponseAsync(context));
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getValid(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getValidAsync() {
        return getValidWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getValidAsync(Context context) {
        return getValidWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getValidWithResponse(Context context) {
        if (this.client.getHost() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getValidSync(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getValid() {
        return getValidWithResponse(Context.NONE).getValue();
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(DictionaryWrapper complexBody) {
        return FluxUtil.withContext(context -> putValidWithResponseAsync(complexBody, context));
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(DictionaryWrapper complexBody, Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return service.putValid(this.client.getHost(), complexBody, accept, context);
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(DictionaryWrapper complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(DictionaryWrapper complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(DictionaryWrapper complexBody, Context context) {
        if (this.client.getHost() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return service.putValidSync(this.client.getHost(), complexBody, accept, context);
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(DictionaryWrapper complexBody) {
        putValidWithResponse(complexBody, Context.NONE);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getEmptyWithResponseAsync() {
        return FluxUtil.withContext(context -> getEmptyWithResponseAsync(context));
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getEmptyWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getEmpty(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getEmptyAsync() {
        return getEmptyWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getEmptyAsync(Context context) {
        return getEmptyWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getEmptyWithResponse(Context context) {
        if (this.client.getHost() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getEmptySync(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getEmpty() {
        return getEmptyWithResponse(Context.NONE).getValue();
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(DictionaryWrapper complexBody) {
        return FluxUtil.withContext(context -> putEmptyWithResponseAsync(complexBody, context));
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(DictionaryWrapper complexBody, Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return service.putEmpty(this.client.getHost(), complexBody, accept, context);
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(DictionaryWrapper complexBody) {
        return putEmptyWithResponseAsync(complexBody).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(DictionaryWrapper complexBody, Context context) {
        return putEmptyWithResponseAsync(complexBody, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(DictionaryWrapper complexBody, Context context) {
        if (this.client.getHost() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return service.putEmptySync(this.client.getHost(), complexBody, accept, context);
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(DictionaryWrapper complexBody) {
        putEmptyWithResponse(complexBody, Context.NONE);
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNullWithResponseAsync() {
        return FluxUtil.withContext(context -> getNullWithResponseAsync(context));
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNullWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNull(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNullAsync() {
        return getNullWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNullAsync(Context context) {
        return getNullWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getNullWithResponse(Context context) {
        if (this.client.getHost() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNullSync(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNull() {
        return getNullWithResponse(Context.NONE).getValue();
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNotProvidedWithResponseAsync() {
        return FluxUtil.withContext(context -> getNotProvidedWithResponseAsync(context));
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNotProvidedWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNotProvided(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNotProvidedAsync(Context context) {
        return getNotProvidedWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload along with
     * {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DictionaryWrapper> getNotProvidedWithResponse(Context context) {
        if (this.client.getHost() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNotProvidedSync(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNotProvided() {
        return getNotProvidedWithResponse(Context.NONE).getValue();
    }

    private static final ClientLogger LOGGER = new ClientLogger(Dictionaries.class);
}
