package fixtures.bodystring;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.Base64Url;
import com.azure.core.implementation.RestProxy;
import fixtures.bodystring.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Strings.
 */
public final class Strings {
    /**
     * The proxy service used to perform REST calls.
     */
    private StringsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATService client;

    /**
     * Initializes an instance of Strings.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Strings(AutoRestSwaggerBATService client) {
        this.service = RestProxy.create(StringsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATServiceStrings to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATServiceStrings")
    private interface StringsService {
        @Get("/string/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getNull(@HostParam("$host") String Host);

        @Put("/string/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putNull(@HostParam("$host") String Host, @BodyParam("application/json") String StringBody);

        @Get("/string/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getEmpty(@HostParam("$host") String Host);

        @Put("/string/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String Host, @BodyParam("application/json") String StringBody);

        @Get("/string/mbcs")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getMbcs(@HostParam("$host") String Host);

        @Put("/string/mbcs")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMbcs(@HostParam("$host") String Host, @BodyParam("application/json") String StringBody);

        @Get("/string/whitespace")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getWhitespace(@HostParam("$host") String Host);

        @Put("/string/whitespace")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWhitespace(@HostParam("$host") String Host, @BodyParam("application/json") String StringBody);

        @Get("/string/notProvided")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getNotProvided(@HostParam("$host") String Host);

        @Get("/string/base64Encoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getBase64Encoded(@HostParam("$host") String Host);

        @Get("/string/base64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getBase64UrlEncoded(@HostParam("$host") String Host);

        @Put("/string/base64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBase64UrlEncoded(@HostParam("$host") String Host, @BodyParam("application/json") Base64Url StringBody);

        @Get("/string/nullBase64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getNullBase64UrlEncoded(@HostParam("$host") String Host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<String> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNullWithResponseAsync(String StringBody) {
        return service.putNull(this.client.getHost(), StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putNullAsync(String StringBody) {
        return putNullWithResponseAsync(StringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putNull(String StringBody) {
        putNullAsync(StringBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getEmptyWithResponseAsync() {
        return service.getEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((SimpleResponse<String> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getEmpty() {
        return getEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(String StringBody) {
        return service.putEmpty(this.client.getHost(), StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(String StringBody) {
        return putEmptyWithResponseAsync(StringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(String StringBody) {
        putEmptyAsync(StringBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getMbcsWithResponseAsync() {
        return service.getMbcs(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getMbcsAsync() {
        return getMbcsWithResponseAsync()
            .flatMap((SimpleResponse<String> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getMbcs() {
        return getMbcsAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMbcsWithResponseAsync(String StringBody) {
        return service.putMbcs(this.client.getHost(), StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMbcsAsync(String StringBody) {
        return putMbcsWithResponseAsync(StringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMbcs(String StringBody) {
        putMbcsAsync(StringBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getWhitespaceWithResponseAsync() {
        return service.getWhitespace(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getWhitespaceAsync() {
        return getWhitespaceWithResponseAsync()
            .flatMap((SimpleResponse<String> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getWhitespace() {
        return getWhitespaceAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWhitespaceWithResponseAsync(String StringBody) {
        return service.putWhitespace(this.client.getHost(), StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWhitespaceAsync(String StringBody) {
        return putWhitespaceWithResponseAsync(StringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWhitespace(String StringBody) {
        putWhitespaceAsync(StringBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getNotProvidedWithResponseAsync() {
        return service.getNotProvided(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync()
            .flatMap((SimpleResponse<String> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getNotProvided() {
        return getNotProvidedAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getBase64EncodedWithResponseAsync() {
        return service.getBase64Encoded(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getBase64EncodedAsync() {
        return getBase64EncodedWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getBase64Encoded() {
        return getBase64EncodedAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getBase64UrlEncodedWithResponseAsync() {
        return service.getBase64UrlEncoded(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getBase64UrlEncodedAsync() {
        return getBase64UrlEncodedWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getBase64UrlEncoded() {
        return getBase64UrlEncodedAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBase64UrlEncodedWithResponseAsync(byte[] StringBody) {
        Base64Url stringBodyConverted = Base64Url.encode(StringBody);
        return service.putBase64UrlEncoded(this.client.getHost(), stringBodyConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBase64UrlEncodedAsync(byte[] StringBody) {
        return putBase64UrlEncodedWithResponseAsync(StringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBase64UrlEncoded(byte[] StringBody) {
        putBase64UrlEncodedAsync(StringBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getNullBase64UrlEncodedWithResponseAsync() {
        return service.getNullBase64UrlEncoded(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getNullBase64UrlEncodedAsync() {
        return getNullBase64UrlEncodedWithResponseAsync()
            .flatMap((SimpleResponse<byte[]> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getNullBase64UrlEncoded() {
        return getNullBase64UrlEncodedAsync().block();
    }
}
