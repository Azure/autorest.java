package fixtures.bodystring.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in StringOperations. */
public final class StringOperationsImpl {
    /** The proxy service used to perform REST calls. */
    private final StringOperationsService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATServiceImpl client;

    /**
     * Initializes an instance of StringOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    StringOperationsImpl(AutoRestSwaggerBATServiceImpl client) {
        this.service =
                RestProxy.create(
                        StringOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATServiceStringOperations to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATSe")
    private interface StringOperationsService {
        @Get("/string/null")
        Mono<Response<BinaryData>> getNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/null")
        Mono<Response<Void>> putNull(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/string/empty")
        Mono<Response<BinaryData>> getEmpty(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/empty")
        Mono<Response<Void>> putEmpty(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData stringBody,
                RequestOptions requestOptions,
                Context context);

        @Get("/string/mbcs")
        Mono<Response<BinaryData>> getMbcs(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/mbcs")
        Mono<Response<Void>> putMbcs(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData stringBody,
                RequestOptions requestOptions,
                Context context);

        @Get("/string/whitespace")
        Mono<Response<BinaryData>> getWhitespace(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/whitespace")
        Mono<Response<Void>> putWhitespace(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData stringBody,
                RequestOptions requestOptions,
                Context context);

        @Get("/string/notProvided")
        Mono<Response<BinaryData>> getNotProvided(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/string/base64Encoding")
        Mono<Response<byte[]>> getBase64Encoded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/string/base64UrlEncoding")
        Mono<Response<BinaryData>> getBase64UrlEncoded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/base64UrlEncoding")
        Mono<Response<Void>> putBase64UrlEncoded(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData stringBody,
                RequestOptions requestOptions,
                Context context);

        @Get("/string/nullBase64UrlEncoding")
        Mono<Response<BinaryData>> getNullBase64UrlEncoded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Get null string value value.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null string value value.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null string value value.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNullAsync(RequestOptions requestOptions) {
        return getNullWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNullAsync(RequestOptions requestOptions, Context context) {
        return getNullWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNull(RequestOptions requestOptions) {
        return getNullAsync(requestOptions).block();
    }

    /**
     * Get null string value value.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNullWithResponse(RequestOptions requestOptions, Context context) {
        return getNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Set string value null.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.putNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Set string value null.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.putNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Set string value null.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putNullAsync(RequestOptions requestOptions) {
        return putNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value null.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putNullAsync(RequestOptions requestOptions, Context context) {
        return putNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value null.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putNull(RequestOptions requestOptions) {
        putNullAsync(requestOptions).block();
    }

    /**
     * Set string value null.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putNullWithResponse(RequestOptions requestOptions, Context context) {
        return putNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get empty string value value ''.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getEmpty(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get empty string value value ''.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getEmpty(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get empty string value value ''.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getEmptyAsync(RequestOptions requestOptions) {
        return getEmptyWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getEmptyAsync(RequestOptions requestOptions, Context context) {
        return getEmptyWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getEmpty(RequestOptions requestOptions) {
        return getEmptyAsync(requestOptions).block();
    }

    /**
     * Get empty string value value ''.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return getEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Set string value empty ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(RequestOptions requestOptions) {
        final BinaryData stringBody = BinaryData.fromObject("");
        return FluxUtil.withContext(
                context -> service.putEmpty(this.client.getHost(), stringBody, requestOptions, context));
    }

    /**
     * Set string value empty ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData stringBody = BinaryData.fromObject("");
        return service.putEmpty(this.client.getHost(), stringBody, requestOptions, context);
    }

    /**
     * Set string value empty ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(RequestOptions requestOptions) {
        return putEmptyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value empty ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(RequestOptions requestOptions, Context context) {
        return putEmptyWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value empty ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(RequestOptions requestOptions) {
        putEmptyAsync(requestOptions).block();
    }

    /**
     * Set string value empty ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return putEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getMbcsWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getMbcs(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getMbcsWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getMbcs(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getMbcsAsync(RequestOptions requestOptions) {
        return getMbcsWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getMbcsAsync(RequestOptions requestOptions, Context context) {
        return getMbcsWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getMbcs(RequestOptions requestOptions) {
        return getMbcsAsync(requestOptions).block();
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getMbcsWithResponse(RequestOptions requestOptions, Context context) {
        return getMbcsWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMbcsWithResponseAsync(RequestOptions requestOptions) {
        final BinaryData stringBody =
                BinaryData.fromObject("啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€");
        return FluxUtil.withContext(
                context -> service.putMbcs(this.client.getHost(), stringBody, requestOptions, context));
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMbcsWithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData stringBody =
                BinaryData.fromObject("啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€");
        return service.putMbcs(this.client.getHost(), stringBody, requestOptions, context);
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMbcsAsync(RequestOptions requestOptions) {
        return putMbcsWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMbcsAsync(RequestOptions requestOptions, Context context) {
        return putMbcsWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMbcs(RequestOptions requestOptions) {
        putMbcsAsync(requestOptions).block();
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putMbcsWithResponse(RequestOptions requestOptions, Context context) {
        return putMbcsWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWhitespaceWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getWhitespace(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWhitespaceWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getWhitespace(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getWhitespaceAsync(RequestOptions requestOptions) {
        return getWhitespaceWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getWhitespaceAsync(RequestOptions requestOptions, Context context) {
        return getWhitespaceWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getWhitespace(RequestOptions requestOptions) {
        return getWhitespaceAsync(requestOptions).block();
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getWhitespaceWithResponse(RequestOptions requestOptions, Context context) {
        return getWhitespaceWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWhitespaceWithResponseAsync(RequestOptions requestOptions) {
        final BinaryData stringBody =
                BinaryData.fromObject("    Now is the time for all good men to come to the aid of their country    ");
        return FluxUtil.withContext(
                context -> service.putWhitespace(this.client.getHost(), stringBody, requestOptions, context));
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWhitespaceWithResponseAsync(RequestOptions requestOptions, Context context) {
        final BinaryData stringBody =
                BinaryData.fromObject("    Now is the time for all good men to come to the aid of their country    ");
        return service.putWhitespace(this.client.getHost(), stringBody, requestOptions, context);
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWhitespaceAsync(RequestOptions requestOptions) {
        return putWhitespaceWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWhitespaceAsync(RequestOptions requestOptions, Context context) {
        return putWhitespaceWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWhitespace(RequestOptions requestOptions) {
        putWhitespaceAsync(requestOptions).block();
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putWhitespaceWithResponse(RequestOptions requestOptions, Context context) {
        return putWhitespaceWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get String value when no string value is sent in response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getNotProvided(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get String value when no string value is sent in response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNotProvided(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get String value when no string value is sent in response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNotProvidedAsync(RequestOptions requestOptions) {
        return getNotProvidedWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNotProvidedAsync(RequestOptions requestOptions, Context context) {
        return getNotProvidedWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNotProvided(RequestOptions requestOptions) {
        return getNotProvidedAsync(requestOptions).block();
    }

    /**
     * Get String value when no string value is sent in response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNotProvidedWithResponse(RequestOptions requestOptions, Context context) {
        return getNotProvidedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get value that is base64 encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * byte[]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<byte[]>> getBase64EncodedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getBase64Encoded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get value that is base64 encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * byte[]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<byte[]>> getBase64EncodedWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getBase64Encoded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get value that is base64 encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * byte[]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getBase64EncodedAsync(RequestOptions requestOptions) {
        return getBase64EncodedWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<byte[]> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * byte[]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<byte[]> getBase64EncodedAsync(RequestOptions requestOptions, Context context) {
        return getBase64EncodedWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<byte[]> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * byte[]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public byte[] getBase64Encoded(RequestOptions requestOptions) {
        return getBase64EncodedAsync(requestOptions).block();
    }

    /**
     * Get value that is base64 encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * byte[]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<byte[]> getBase64EncodedWithResponse(RequestOptions requestOptions, Context context) {
        return getBase64EncodedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get value that is base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getBase64UrlEncodedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getBase64UrlEncoded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get value that is base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getBase64UrlEncodedWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.getBase64UrlEncoded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get value that is base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getBase64UrlEncodedAsync(RequestOptions requestOptions) {
        return getBase64UrlEncodedWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getBase64UrlEncodedAsync(RequestOptions requestOptions, Context context) {
        return getBase64UrlEncodedWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getBase64UrlEncoded(RequestOptions requestOptions) {
        return getBase64UrlEncodedAsync(requestOptions).block();
    }

    /**
     * Get value that is base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getBase64UrlEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return getBase64UrlEncodedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Put value that is base64url encoded.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBase64UrlEncodedWithResponseAsync(
            BinaryData stringBody, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putBase64UrlEncoded(this.client.getHost(), stringBody, requestOptions, context));
    }

    /**
     * Put value that is base64url encoded.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBase64UrlEncodedWithResponseAsync(
            BinaryData stringBody, RequestOptions requestOptions, Context context) {
        return service.putBase64UrlEncoded(this.client.getHost(), stringBody, requestOptions, context);
    }

    /**
     * Put value that is base64url encoded.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBase64UrlEncodedAsync(BinaryData stringBody, RequestOptions requestOptions) {
        return putBase64UrlEncodedWithResponseAsync(stringBody, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put value that is base64url encoded.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBase64UrlEncodedAsync(BinaryData stringBody, RequestOptions requestOptions, Context context) {
        return putBase64UrlEncodedWithResponseAsync(stringBody, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put value that is base64url encoded.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBase64UrlEncoded(BinaryData stringBody, RequestOptions requestOptions) {
        putBase64UrlEncodedAsync(stringBody, requestOptions).block();
    }

    /**
     * Put value that is base64url encoded.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putBase64UrlEncodedWithResponse(
            BinaryData stringBody, RequestOptions requestOptions, Context context) {
        return putBase64UrlEncodedWithResponseAsync(stringBody, requestOptions, context).block();
    }

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullBase64UrlEncodedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getNullBase64UrlEncoded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullBase64UrlEncodedWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.getNullBase64UrlEncoded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNullBase64UrlEncodedAsync(RequestOptions requestOptions) {
        return getNullBase64UrlEncodedWithResponseAsync(requestOptions)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getNullBase64UrlEncodedAsync(RequestOptions requestOptions, Context context) {
        return getNullBase64UrlEncodedWithResponseAsync(requestOptions, context)
                .flatMap(
                        (Response<BinaryData> res) -> {
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
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNullBase64UrlEncoded(RequestOptions requestOptions) {
        return getNullBase64UrlEncodedAsync(requestOptions).block();
    }

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Base64Url
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNullBase64UrlEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return getNullBase64UrlEncodedWithResponseAsync(requestOptions, context).block();
    }
}
