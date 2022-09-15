// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodyfile;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodyfile.models.ErrorException;
import reactor.core.publisher.Mono;

import java.io.InputStream;

/** An instance of this class provides access to all the operations defined in Files. */
public final class Files {
    /** The proxy service used to perform REST calls. */
    private final FilesService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATFileService client;

    /**
     * Initializes an instance of Files.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Files(AutoRestSwaggerBATFileService client) {
        this.service = RestProxy.create(FilesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATFileServiceFiles to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATFi")
    private interface FilesService {
        @Get("/files/stream/nonempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<InputStream>> getFile(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/files/stream/verylarge")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<InputStream>> getFileLarge(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/files/stream/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<InputStream>> getEmptyFile(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<InputStream>> getFileWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "image/png, application/json";
        return FluxUtil.withContext(context -> service.getFile(this.client.getHost(), accept, context));
    }

    /**
     * Get file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<InputStream>> getFileWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "image/png, application/json";
        return service.getFile(this.client.getHost(), accept, context);
    }

    /**
     * Get file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<InputStream> getFileAsync() {
        return getFileWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<InputStream> getFileAsync(Context context) {
        return getFileWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<InputStream> getFileWithResponse() {
        return getFileWithResponseAsync().block();
    }

    /**
     * Get file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<InputStream> getFileWithResponse(Context context) {
        return getFileWithResponseAsync(context).block();
    }

    /**
     * Get file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getFile() {
        return getFileWithResponse(Context.NONE).getValue();
    }

    /**
     * Get file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getFile(Context context) {
        return getFileWithResponse(context).getValue();
    }

    /**
     * Get a large file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<InputStream>> getFileLargeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "image/png, application/json";
        return FluxUtil.withContext(context -> service.getFileLarge(this.client.getHost(), accept, context));
    }

    /**
     * Get a large file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<InputStream>> getFileLargeWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "image/png, application/json";
        return service.getFileLarge(this.client.getHost(), accept, context);
    }

    /**
     * Get a large file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<InputStream> getFileLargeAsync() {
        return getFileLargeWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a large file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<InputStream> getFileLargeAsync(Context context) {
        return getFileLargeWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a large file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<InputStream> getFileLargeWithResponse() {
        return getFileLargeWithResponseAsync().block();
    }

    /**
     * Get a large file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<InputStream> getFileLargeWithResponse(Context context) {
        return getFileLargeWithResponseAsync(context).block();
    }

    /**
     * Get a large file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getFileLarge() {
        return getFileLargeWithResponse(Context.NONE).getValue();
    }

    /**
     * Get a large file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getFileLarge(Context context) {
        return getFileLargeWithResponse(context).getValue();
    }

    /**
     * Get empty file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<InputStream>> getEmptyFileWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "image/png, application/json";
        return FluxUtil.withContext(context -> service.getEmptyFile(this.client.getHost(), accept, context));
    }

    /**
     * Get empty file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<InputStream>> getEmptyFileWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "image/png, application/json";
        return service.getEmptyFile(this.client.getHost(), accept, context);
    }

    /**
     * Get empty file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<InputStream> getEmptyFileAsync() {
        return getEmptyFileWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get empty file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<InputStream> getEmptyFileAsync(Context context) {
        return getEmptyFileWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get empty file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<InputStream> getEmptyFileWithResponse() {
        return getEmptyFileWithResponseAsync().block();
    }

    /**
     * Get empty file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<InputStream> getEmptyFileWithResponse(Context context) {
        return getEmptyFileWithResponseAsync(context).block();
    }

    /**
     * Get empty file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getEmptyFile() {
        return getEmptyFileWithResponse(Context.NONE).getValue();
    }

    /**
     * Get empty file.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getEmptyFile(Context context) {
        return getEmptyFileWithResponse(context).getValue();
    }
}
