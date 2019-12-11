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
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATFileServiceFiles")
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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileWithResponseAsync() {
        return service.getFile(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getFileAsync() {
        return getFileWithResponseAsync()
            .flatMap((StreamResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getFile() {
        getFileAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getFileLargeWithResponseAsync() {
        return service.getFileLarge(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getFileLargeAsync() {
        return getFileLargeWithResponseAsync()
            .flatMap((StreamResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getFileLarge() {
        getFileLargeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StreamResponse> getEmptyFileWithResponseAsync() {
        return service.getEmptyFile(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getEmptyFileAsync() {
        return getEmptyFileWithResponseAsync()
            .flatMap((StreamResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmptyFile() {
        getEmptyFileAsync().block();
    }
}
