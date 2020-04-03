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
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Base64Url;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
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
    private final StringsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestSwaggerBATService client;

    /**
     * Initializes an instance of Strings.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Strings(AutoRestSwaggerBATService client) {
        this.service = RestProxy.create(StringsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATServiceStrings to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATSe")
    private interface StringsService {
        @Get("/string/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getNull(@HostParam("$host") String host, Context context);

        @Put("/string/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putNull(@HostParam("$host") String host, @BodyParam("application/json") String stringBody, Context context);

        @Get("/string/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getEmpty(@HostParam("$host") String host, Context context);

        @Put("/string/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String host, @BodyParam("application/json") String stringBody, Context context);

        @Get("/string/mbcs")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getMbcs(@HostParam("$host") String host, Context context);

        @Put("/string/mbcs")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMbcs(@HostParam("$host") String host, @BodyParam("application/json") String stringBody, Context context);

        @Get("/string/whitespace")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getWhitespace(@HostParam("$host") String host, Context context);

        @Put("/string/whitespace")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWhitespace(@HostParam("$host") String host, @BodyParam("application/json") String stringBody, Context context);

        @Get("/string/notProvided")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<String>> getNotProvided(@HostParam("$host") String host, Context context);

        @Get("/string/base64Encoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getBase64Encoded(@HostParam("$host") String host, Context context);

        @Get("/string/base64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getBase64UrlEncoded(@HostParam("$host") String host, Context context);

        @Put("/string/base64UrlEncoding")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBase64UrlEncoded(@HostParam("$host") String host, @BodyParam("application/json") Base64Url stringBody, Context context);

        @Get("/string/nullBase64UrlEncoding")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<byte[]>> getNullBase64UrlEncoded(@HostParam("$host") String host, Context context);
    }

    /**
     * Get null string value value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get null string value value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
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

    /**
     * Get null string value value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getNull() {
        return getNullAsync().block();
    }

    /**
     * Set string value null.
     * 
     * @param stringBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNullWithResponseAsync(String stringBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putNull(this.client.getHost(), stringBody, context));
    }

    /**
     * Set string value null.
     * 
     * @param stringBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putNullAsync(String stringBody) {
        return putNullWithResponseAsync(stringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value null.
     * 
     * @param stringBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putNull(String stringBody) {
        putNullAsync(stringBody).block();
    }

    /**
     * Get empty string value value ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getEmpty(this.client.getHost(), context));
    }

    /**
     * Get empty string value value ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
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

    /**
     * Get empty string value value ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getEmpty() {
        return getEmptyAsync().block();
    }

    /**
     * Set string value empty ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String stringBody = "";
        return FluxUtil.withContext(context -> service.putEmpty(this.client.getHost(), stringBody, context));
    }

    /**
     * Set string value empty ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync() {
        return putEmptyWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value empty ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty() {
        putEmptyAsync().block();
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getMbcsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMbcs(this.client.getHost(), context));
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
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

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getMbcs() {
        return getMbcsAsync().block();
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMbcsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String stringBody = "啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€";
        return FluxUtil.withContext(context -> service.putMbcs(this.client.getHost(), stringBody, context));
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMbcsAsync() {
        return putMbcsWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMbcs() {
        putMbcsAsync().block();
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getWhitespaceWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getWhitespace(this.client.getHost(), context));
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
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

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getWhitespace() {
        return getWhitespaceAsync().block();
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWhitespaceWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String stringBody = "    Now is the time for all good men to come to the aid of their country    ";
        return FluxUtil.withContext(context -> service.putWhitespace(this.client.getHost(), stringBody, context));
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWhitespaceAsync() {
        return putWhitespaceWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWhitespace() {
        putWhitespaceAsync().block();
    }

    /**
     * Get String value when no string value is sent in response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> getNotProvidedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNotProvided(this.client.getHost(), context));
    }

    /**
     * Get String value when no string value is sent in response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
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

    /**
     * Get String value when no string value is sent in response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String getNotProvided() {
        return getNotProvidedAsync().block();
    }

    /**
     * Get value that is base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getBase64EncodedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getBase64Encoded(this.client.getHost(), context));
    }

    /**
     * Get value that is base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
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

    /**
     * Get value that is base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getBase64Encoded() {
        return getBase64EncodedAsync().block();
    }

    /**
     * Get value that is base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getBase64UrlEncodedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getBase64UrlEncoded(this.client.getHost(), context));
    }

    /**
     * Get value that is base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
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

    /**
     * Get value that is base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getBase64UrlEncoded() {
        return getBase64UrlEncodedAsync().block();
    }

    /**
     * Put value that is base64url encoded.
     * 
     * @param stringBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBase64UrlEncodedWithResponseAsync(byte[] stringBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (stringBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter stringBody is required and cannot be null."));
        }
        Base64Url stringBodyConverted = Base64Url.encode(stringBody);
        return FluxUtil.withContext(context -> service.putBase64UrlEncoded(this.client.getHost(), stringBodyConverted, context));
    }

    /**
     * Put value that is base64url encoded.
     * 
     * @param stringBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBase64UrlEncodedAsync(byte[] stringBody) {
        return putBase64UrlEncodedWithResponseAsync(stringBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put value that is base64url encoded.
     * 
     * @param stringBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBase64UrlEncoded(byte[] stringBody) {
        putBase64UrlEncodedAsync(stringBody).block();
    }

    /**
     * Get null value that is expected to be base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<byte[]>> getNullBase64UrlEncodedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNullBase64UrlEncoded(this.client.getHost(), context));
    }

    /**
     * Get null value that is expected to be base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
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

    /**
     * Get null value that is expected to be base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getNullBase64UrlEncoded() {
        return getNullBase64UrlEncodedAsync().block();
    }
}
