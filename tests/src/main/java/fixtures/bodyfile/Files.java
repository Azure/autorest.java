package fixtures.bodyfile;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.StreamResponse;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import fixtures.bodyfile.models.ErrorException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Files.
 */
public final class Files {
    /**
     * The proxy service used to perform REST calls.
     */
    private FilesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBatFileService client;

    /**
     * Initializes an instance of Files.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Files(AutoRestSwaggerBatFileService client) {
        this.service = RestProxy.create(FilesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBatFileServiceFiles to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBatFileServiceFiles")
    private interface FilesService {
        @Get("/files/stream/nonempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getFile(@HostParam("$host") String host);

        @Get("/files/stream/verylarge")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getFileLarge(@HostParam("$host") String host);

        @Get("/files/stream/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getEmptyFile(@HostParam("$host") String host);
    }

    /**
     * Get file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getFile(this.client.getHost());
    }

    /**
     * Get file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Flux<ByteBuffer> getFileAsync() {
        return getFileWithResponseAsync()
            .flatMapMany(StreamResponse::getValue);}

    /**
     * Get file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getFile() {
        return getFileAsync()
            .map(ByteBufferBackedInputStream::new)
            .collectList()
            .map(list -> new SequenceInputStream(Collections.enumeration(list)))
            .block();
    }

    /**
     * Get a large file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileLargeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getFileLarge(this.client.getHost());
    }

    /**
     * Get a large file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Flux<ByteBuffer> getFileLargeAsync() {
        return getFileLargeWithResponseAsync()
            .flatMapMany(StreamResponse::getValue);}

    /**
     * Get a large file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getFileLarge() {
        return getFileLargeAsync()
            .map(ByteBufferBackedInputStream::new)
            .collectList()
            .map(list -> new SequenceInputStream(Collections.enumeration(list)))
            .block();
    }

    /**
     * Get empty file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getEmptyFileWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmptyFile(this.client.getHost());
    }

    /**
     * Get empty file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Flux<ByteBuffer> getEmptyFileAsync() {
        return getEmptyFileWithResponseAsync()
            .flatMapMany(StreamResponse::getValue);}

    /**
     * Get empty file.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public InputStream getEmptyFile() {
        return getEmptyFileAsync()
            .map(ByteBufferBackedInputStream::new)
            .collectList()
            .map(list -> new SequenceInputStream(Collections.enumeration(list)))
            .block();
    }
}
