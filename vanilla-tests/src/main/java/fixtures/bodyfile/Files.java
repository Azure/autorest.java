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
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.StreamResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import fixtures.bodyfile.models.ErrorException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Iterator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        Mono<StreamResponse> getFile(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/files/stream/verylarge")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getFileLarge(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/files/stream/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getEmptyFile(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileWithResponseAsync() {
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
     * @return file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileWithResponseAsync(Context context) {
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
     * @return file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Flux<ByteBuffer> getFileAsync() {
        return getFileWithResponseAsync().flatMapMany(StreamResponse::getValue);
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
    public Flux<ByteBuffer> getFileAsync(Context context) {
        return getFileWithResponseAsync(context).flatMapMany(StreamResponse::getValue);
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
        Iterator<ByteBufferBackedInputStream> iterator =
                getFileAsync().map(ByteBufferBackedInputStream::new).toStream().iterator();
        Enumeration<InputStream> enumeration =
                new Enumeration<InputStream>() {
                    @Override
                    public boolean hasMoreElements() {
                        return iterator.hasNext();
                    }

                    @Override
                    public InputStream nextElement() {
                        return iterator.next();
                    }
                };
        return new SequenceInputStream(enumeration);
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
    public StreamResponse getFileWithResponse(Context context) {
        return getFileWithResponseAsync(context).block();
    }

    /**
     * Get a large file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a large file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileLargeWithResponseAsync() {
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
     * @return a large file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileLargeWithResponseAsync(Context context) {
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
     * @return a large file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Flux<ByteBuffer> getFileLargeAsync() {
        return getFileLargeWithResponseAsync().flatMapMany(StreamResponse::getValue);
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
    public Flux<ByteBuffer> getFileLargeAsync(Context context) {
        return getFileLargeWithResponseAsync(context).flatMapMany(StreamResponse::getValue);
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
        Iterator<ByteBufferBackedInputStream> iterator =
                getFileLargeAsync().map(ByteBufferBackedInputStream::new).toStream().iterator();
        Enumeration<InputStream> enumeration =
                new Enumeration<InputStream>() {
                    @Override
                    public boolean hasMoreElements() {
                        return iterator.hasNext();
                    }

                    @Override
                    public InputStream nextElement() {
                        return iterator.next();
                    }
                };
        return new SequenceInputStream(enumeration);
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
    public StreamResponse getFileLargeWithResponse(Context context) {
        return getFileLargeWithResponseAsync(context).block();
    }

    /**
     * Get empty file.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getEmptyFileWithResponseAsync() {
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
     * @return empty file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getEmptyFileWithResponseAsync(Context context) {
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
     * @return empty file.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Flux<ByteBuffer> getEmptyFileAsync() {
        return getEmptyFileWithResponseAsync().flatMapMany(StreamResponse::getValue);
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
    public Flux<ByteBuffer> getEmptyFileAsync(Context context) {
        return getEmptyFileWithResponseAsync(context).flatMapMany(StreamResponse::getValue);
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
        Iterator<ByteBufferBackedInputStream> iterator =
                getEmptyFileAsync().map(ByteBufferBackedInputStream::new).toStream().iterator();
        Enumeration<InputStream> enumeration =
                new Enumeration<InputStream>() {
                    @Override
                    public boolean hasMoreElements() {
                        return iterator.hasNext();
                    }

                    @Override
                    public InputStream nextElement() {
                        return iterator.next();
                    }
                };
        return new SequenceInputStream(enumeration);
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
    public StreamResponse getEmptyFileWithResponse(Context context) {
        return getEmptyFileWithResponseAsync(context).block();
    }
}
