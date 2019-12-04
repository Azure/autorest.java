package fixtures.bodyfile;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.StreamResponse;
import com.azure.core.implementation.RestProxy;
import fixtures.bodyfile.models.ErrorException;
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
    private AutoRestSwaggerBATFileService client;

    /**
     * Initializes an instance of Files.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Files(AutoRestSwaggerBATFileService client) {
        this.service = RestProxy.create(FilesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATFileServiceFiles to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestSwaggerBATFileServiceFiles")
    private interface FilesService {
        @Get("/files/stream/nonempty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getFile();

        @Get("/files/stream/verylarge")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getFileLarge();

        @Get("/files/stream/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<StreamResponse> getEmptyFile();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileWithResponseAsync() {
        return service.getFile();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileLargeWithResponseAsync() {
        return service.getFileLarge();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getEmptyFileWithResponseAsync() {
        return service.getEmptyFile();
    }
}
