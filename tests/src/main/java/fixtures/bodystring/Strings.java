package fixtures.bodystring;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
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
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestSwaggerBATServiceStrings")
    private interface StringsService {
        @Get("/string/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getNull();

        @Put("/string/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putNull(@BodyParam("application/json") String StringBody);

        @Get("/string/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getEmpty();

        @Put("/string/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@BodyParam("application/json") String StringBody);

        @Get("/string/mbcs")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getMbcs();

        @Put("/string/mbcs")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMbcs(@BodyParam("application/json") String StringBody);

        @Get("/string/whitespace")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getWhitespace();

        @Put("/string/whitespace")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWhitespace(@BodyParam("application/json") String StringBody);

        @Get("/string/notProvided")
        @ExpectedResponses({200})
        @ReturnValueWireType(String.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getNotProvided();

        @Get("/string/base64Encoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getBase64Encoded();

        @Get("/string/base64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getBase64UrlEncoded();

        @Put("/string/base64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBase64UrlEncoded(@BodyParam("application/json") Base64Url StringBody);

        @Get("/string/nullBase64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getNullBase64UrlEncoded();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getNullWithResponseAsync() {
        return service.getNull();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNullWithResponseAsync(String StringBody) {
        return service.putNull(StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getEmptyWithResponseAsync() {
        return service.getEmpty();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(String StringBody) {
        return service.putEmpty(StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getMbcsWithResponseAsync() {
        return service.getMbcs();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMbcsWithResponseAsync(String StringBody) {
        return service.putMbcs(StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getWhitespaceWithResponseAsync() {
        return service.getWhitespace();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWhitespaceWithResponseAsync(String StringBody) {
        return service.putWhitespace(StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getNotProvidedWithResponseAsync() {
        return service.getNotProvided();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getBase64EncodedWithResponseAsync() {
        return service.getBase64Encoded();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getBase64UrlEncodedWithResponseAsync() {
        return service.getBase64UrlEncoded();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBase64UrlEncodedWithResponseAsync(byte[] StringBody) {
        Base64Url stringBodyConverted = Base64Url.encode(StringBody);
        return service.putBase64UrlEncoded(stringBodyConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getNullBase64UrlEncodedWithResponseAsync() {
        return service.getNullBase64UrlEncoded();
    }
}
