package fixtures.bodybyte;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.bodybyte.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Bytes.
 */
public final class Bytes {
    /**
     * The proxy service used to perform REST calls.
     */
    private BytesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATByteService client;

    /**
     * Initializes an instance of Bytes.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Bytes(AutoRestSwaggerBATByteService client) {
        this.service = RestProxy.create(BytesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATByteServiceBytes to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATByteServiceBytes")
    private interface BytesService {
        @Get("/byte/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getNull(@HostParam("$host") String host);

        @Get("/byte/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getEmpty(@HostParam("$host") String host);

        @Get("/byte/nonAscii")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getNonAscii(@HostParam("$host") String host);

        @Put("/byte/nonAscii")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putNonAscii(@HostParam("$host") String host, @BodyParam("application/json") byte[] byteBody);

        @Get("/byte/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getInvalid(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getEmptyWithResponseAsync() {
        return service.getEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getEmpty() {
        return getEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getNonAsciiWithResponseAsync() {
        return service.getNonAscii(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getNonAsciiAsync() {
        return getNonAsciiWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getNonAscii() {
        return getNonAsciiAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNonAsciiWithResponseAsync(byte[] byteBody) {
        return service.putNonAscii(this.client.getHost(), byteBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putNonAsciiAsync(byte[] byteBody) {
        return putNonAsciiWithResponseAsync(byteBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putNonAscii(byte[] byteBody) {
        putNonAsciiAsync(byteBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getInvalidWithResponseAsync() {
        return service.getInvalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getInvalid() {
        return getInvalidAsync().block();
    }
}
