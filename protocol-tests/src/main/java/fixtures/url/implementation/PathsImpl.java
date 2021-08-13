package fixtures.url.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Paths. */
public final class PathsImpl {
    /** The proxy service used to perform REST calls. */
    private final PathsService service;

    /** The service client containing this operation class. */
    private final AutoRestUrlTestServiceImpl client;

    /**
     * Initializes an instance of PathsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PathsImpl(AutoRestUrlTestServiceImpl client) {
        this.service = RestProxy.create(PathsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestUrlTestServicePaths to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlTestServi")
    private interface PathsService {
        @Get("/paths/bool/true/{boolPath}")
        Mono<Response<Void>> getBooleanTrue(
                @HostParam("$host") String host,
                @PathParam("boolPath") boolean boolPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/bool/false/{boolPath}")
        Mono<Response<Void>> getBooleanFalse(
                @HostParam("$host") String host,
                @PathParam("boolPath") boolean boolPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/int/1000000/{intPath}")
        Mono<Response<Void>> getIntOneMillion(
                @HostParam("$host") String host,
                @PathParam("intPath") int intPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/int/-1000000/{intPath}")
        Mono<Response<Void>> getIntNegativeOneMillion(
                @HostParam("$host") String host,
                @PathParam("intPath") int intPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/long/10000000000/{longPath}")
        Mono<Response<Void>> getTenBillion(
                @HostParam("$host") String host,
                @PathParam("longPath") long longPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/long/-10000000000/{longPath}")
        Mono<Response<Void>> getNegativeTenBillion(
                @HostParam("$host") String host,
                @PathParam("longPath") long longPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/float/1.034E+20/{floatPath}")
        Mono<Response<Void>> floatScientificPositive(
                @HostParam("$host") String host,
                @PathParam("floatPath") float floatPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/float/-1.034E-20/{floatPath}")
        Mono<Response<Void>> floatScientificNegative(
                @HostParam("$host") String host,
                @PathParam("floatPath") float floatPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/double/9999999.999/{doublePath}")
        Mono<Response<Void>> doubleDecimalPositive(
                @HostParam("$host") String host,
                @PathParam("doublePath") double doublePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/double/-9999999.999/{doublePath}")
        Mono<Response<Void>> doubleDecimalNegative(
                @HostParam("$host") String host,
                @PathParam("doublePath") double doublePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/unicode/{stringPath}")
        Mono<Response<Void>> stringUnicode(
                @HostParam("$host") String host,
                @PathParam("stringPath") String stringPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/begin%21%2A%27%28%29%3B%3A%40%20%26%3D%2B%24%2C%2F%3F%23%5B%5Dend/{stringPath}")
        Mono<Response<Void>> stringUrlEncoded(
                @HostParam("$host") String host,
                @PathParam("stringPath") String stringPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/begin!*'();:@&=+$,end/{stringPath}")
        Mono<Response<Void>> stringUrlNonEncoded(
                @HostParam("$host") String host,
                @PathParam(value = "stringPath", encoded = true) String stringPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/empty/{stringPath}")
        Mono<Response<Void>> stringEmpty(
                @HostParam("$host") String host,
                @PathParam("stringPath") String stringPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/null/{stringPath}")
        Mono<Response<Void>> stringNull(
                @HostParam("$host") String host,
                @PathParam("stringPath") String stringPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/enum/green%20color/{enumPath}")
        Mono<Response<Void>> enumValid(
                @HostParam("$host") String host,
                @PathParam("enumPath") String enumPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/null/{enumPath}")
        Mono<Response<Void>> enumNull(
                @HostParam("$host") String host,
                @PathParam("enumPath") String enumPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/byte/multibyte/{bytePath}")
        Mono<Response<Void>> byteMultiByte(
                @HostParam("$host") String host,
                @PathParam("bytePath") String bytePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/byte/empty/{bytePath}")
        Mono<Response<Void>> byteEmpty(
                @HostParam("$host") String host,
                @PathParam("bytePath") String bytePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/byte/null/{bytePath}")
        Mono<Response<Void>> byteNull(
                @HostParam("$host") String host,
                @PathParam("bytePath") String bytePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/date/2012-01-01/{datePath}")
        Mono<Response<Void>> dateValid(
                @HostParam("$host") String host,
                @PathParam("datePath") String datePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/date/null/{datePath}")
        Mono<Response<Void>> dateNull(
                @HostParam("$host") String host,
                @PathParam("datePath") String datePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/datetime/2012-01-01T01%3A01%3A01Z/{dateTimePath}")
        Mono<Response<Void>> dateTimeValid(
                @HostParam("$host") String host,
                @PathParam("dateTimePath") String dateTimePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/datetime/null/{dateTimePath}")
        Mono<Response<Void>> dateTimeNull(
                @HostParam("$host") String host,
                @PathParam("dateTimePath") String dateTimePath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/string/bG9yZW0/{base64UrlPath}")
        Mono<Response<Void>> base64Url(
                @HostParam("$host") String host,
                @PathParam("base64UrlPath") String base64UrlPath,
                RequestOptions requestOptions,
                Context context);

        @Get(
                "/paths/array/ArrayPath1%2cbegin%21%2A%27%28%29%3B%3A%40%20%26%3D%2B%24%2C%2F%3F%23%5B%5Dend%2c%2c/{arrayPath}")
        Mono<Response<Void>> arrayCsvInPath(
                @HostParam("$host") String host,
                @PathParam("arrayPath") String arrayPath,
                RequestOptions requestOptions,
                Context context);

        @Get("/paths/int/1460505600/{unixTimeUrlPath}")
        Mono<Response<Void>> unixTimeUrl(
                @HostParam("$host") String host,
                @PathParam("unixTimeUrlPath") long unixTimeUrlPath,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponseAsync(RequestOptions requestOptions) {
        final boolean boolPath = true;
        return FluxUtil.withContext(
                context -> service.getBooleanTrue(this.client.getHost(), boolPath, requestOptions, context));
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponseAsync(RequestOptions requestOptions, Context context) {
        final boolean boolPath = true;
        return service.getBooleanTrue(this.client.getHost(), boolPath, requestOptions, context);
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanTrueAsync(RequestOptions requestOptions) {
        return getBooleanTrueWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanTrueAsync(RequestOptions requestOptions, Context context) {
        return getBooleanTrueWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanTrue(RequestOptions requestOptions) {
        getBooleanTrueAsync(requestOptions).block();
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanTrueWithResponse(RequestOptions requestOptions, Context context) {
        return getBooleanTrueWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponseAsync(RequestOptions requestOptions) {
        final boolean boolPath = false;
        return FluxUtil.withContext(
                context -> service.getBooleanFalse(this.client.getHost(), boolPath, requestOptions, context));
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponseAsync(RequestOptions requestOptions, Context context) {
        final boolean boolPath = false;
        return service.getBooleanFalse(this.client.getHost(), boolPath, requestOptions, context);
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanFalseAsync(RequestOptions requestOptions) {
        return getBooleanFalseWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanFalseAsync(RequestOptions requestOptions, Context context) {
        return getBooleanFalseWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanFalse(RequestOptions requestOptions) {
        getBooleanFalseAsync(requestOptions).block();
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanFalseWithResponse(RequestOptions requestOptions, Context context) {
        return getBooleanFalseWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponseAsync(RequestOptions requestOptions) {
        final int intPath = 1000000;
        return FluxUtil.withContext(
                context -> service.getIntOneMillion(this.client.getHost(), intPath, requestOptions, context));
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponseAsync(RequestOptions requestOptions, Context context) {
        final int intPath = 1000000;
        return service.getIntOneMillion(this.client.getHost(), intPath, requestOptions, context);
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntOneMillionAsync(RequestOptions requestOptions) {
        return getIntOneMillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntOneMillionAsync(RequestOptions requestOptions, Context context) {
        return getIntOneMillionWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntOneMillion(RequestOptions requestOptions) {
        getIntOneMillionAsync(requestOptions).block();
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return getIntOneMillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponseAsync(RequestOptions requestOptions) {
        final int intPath = -1000000;
        return FluxUtil.withContext(
                context -> service.getIntNegativeOneMillion(this.client.getHost(), intPath, requestOptions, context));
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final int intPath = -1000000;
        return service.getIntNegativeOneMillion(this.client.getHost(), intPath, requestOptions, context);
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNegativeOneMillionAsync(RequestOptions requestOptions) {
        return getIntNegativeOneMillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNegativeOneMillionAsync(RequestOptions requestOptions, Context context) {
        return getIntNegativeOneMillionWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNegativeOneMillion(RequestOptions requestOptions) {
        getIntNegativeOneMillionAsync(requestOptions).block();
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNegativeOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return getIntNegativeOneMillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponseAsync(RequestOptions requestOptions) {
        final long longPath = 10000000000L;
        return FluxUtil.withContext(
                context -> service.getTenBillion(this.client.getHost(), longPath, requestOptions, context));
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponseAsync(RequestOptions requestOptions, Context context) {
        final long longPath = 10000000000L;
        return service.getTenBillion(this.client.getHost(), longPath, requestOptions, context);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getTenBillionAsync(RequestOptions requestOptions) {
        return getTenBillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getTenBillionAsync(RequestOptions requestOptions, Context context) {
        return getTenBillionWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getTenBillion(RequestOptions requestOptions) {
        getTenBillionAsync(requestOptions).block();
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return getTenBillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponseAsync(RequestOptions requestOptions) {
        final long longPath = -10000000000L;
        return FluxUtil.withContext(
                context -> service.getNegativeTenBillion(this.client.getHost(), longPath, requestOptions, context));
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponseAsync(RequestOptions requestOptions, Context context) {
        final long longPath = -10000000000L;
        return service.getNegativeTenBillion(this.client.getHost(), longPath, requestOptions, context);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getNegativeTenBillionAsync(RequestOptions requestOptions) {
        return getNegativeTenBillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getNegativeTenBillionAsync(RequestOptions requestOptions, Context context) {
        return getNegativeTenBillionWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getNegativeTenBillion(RequestOptions requestOptions) {
        getNegativeTenBillionAsync(requestOptions).block();
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getNegativeTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return getNegativeTenBillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '1.034E+20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponseAsync(RequestOptions requestOptions) {
        final float floatPath = 103400000000000000000f;
        return FluxUtil.withContext(
                context -> service.floatScientificPositive(this.client.getHost(), floatPath, requestOptions, context));
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1.034E+20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final float floatPath = 103400000000000000000f;
        return service.floatScientificPositive(this.client.getHost(), floatPath, requestOptions, context);
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '1.034E+20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificPositiveAsync(RequestOptions requestOptions) {
        return floatScientificPositiveWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1.034E+20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificPositiveAsync(RequestOptions requestOptions, Context context) {
        return floatScientificPositiveWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificPositive(RequestOptions requestOptions) {
        floatScientificPositiveAsync(requestOptions).block();
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1.034E+20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return floatScientificPositiveWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-1.034E-20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponseAsync(RequestOptions requestOptions) {
        final float floatPath = -1.034E-20f;
        return FluxUtil.withContext(
                context -> service.floatScientificNegative(this.client.getHost(), floatPath, requestOptions, context));
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1.034E-20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        final float floatPath = -1.034E-20f;
        return service.floatScientificNegative(this.client.getHost(), floatPath, requestOptions, context);
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-1.034E-20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificNegativeAsync(RequestOptions requestOptions) {
        return floatScientificNegativeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1.034E-20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificNegativeAsync(RequestOptions requestOptions, Context context) {
        return floatScientificNegativeWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificNegative(RequestOptions requestOptions) {
        floatScientificNegativeAsync(requestOptions).block();
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1.034E-20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return floatScientificNegativeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponseAsync(RequestOptions requestOptions) {
        final double doublePath = 9999999.999;
        return FluxUtil.withContext(
                context -> service.doubleDecimalPositive(this.client.getHost(), doublePath, requestOptions, context));
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponseAsync(RequestOptions requestOptions, Context context) {
        final double doublePath = 9999999.999;
        return service.doubleDecimalPositive(this.client.getHost(), doublePath, requestOptions, context);
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalPositiveAsync(RequestOptions requestOptions) {
        return doubleDecimalPositiveWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalPositiveAsync(RequestOptions requestOptions, Context context) {
        return doubleDecimalPositiveWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalPositive(RequestOptions requestOptions) {
        doubleDecimalPositiveAsync(requestOptions).block();
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return doubleDecimalPositiveWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponseAsync(RequestOptions requestOptions) {
        final double doublePath = -9999999.999;
        return FluxUtil.withContext(
                context -> service.doubleDecimalNegative(this.client.getHost(), doublePath, requestOptions, context));
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponseAsync(RequestOptions requestOptions, Context context) {
        final double doublePath = -9999999.999;
        return service.doubleDecimalNegative(this.client.getHost(), doublePath, requestOptions, context);
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '-9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalNegativeAsync(RequestOptions requestOptions) {
        return doubleDecimalNegativeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalNegativeAsync(RequestOptions requestOptions, Context context) {
        return doubleDecimalNegativeWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalNegative(RequestOptions requestOptions) {
        doubleDecimalNegativeAsync(requestOptions).block();
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return doubleDecimalNegativeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponseAsync(RequestOptions requestOptions) {
        final String stringPath = "啊齄丂狛狜隣郎隣兀﨩";
        return FluxUtil.withContext(
                context -> service.stringUnicode(this.client.getHost(), stringPath, requestOptions, context));
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String stringPath = "啊齄丂狛狜隣郎隣兀﨩";
        return service.stringUnicode(this.client.getHost(), stringPath, requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUnicodeAsync(RequestOptions requestOptions) {
        return stringUnicodeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUnicodeAsync(RequestOptions requestOptions, Context context) {
        return stringUnicodeWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUnicode(RequestOptions requestOptions) {
        stringUnicodeAsync(requestOptions).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUnicodeWithResponse(RequestOptions requestOptions, Context context) {
        return stringUnicodeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponseAsync(RequestOptions requestOptions) {
        final String stringPath = "begin!*'();:@ &=+$,/?#[]end";
        return FluxUtil.withContext(
                context -> service.stringUrlEncoded(this.client.getHost(), stringPath, requestOptions, context));
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String stringPath = "begin!*'();:@ &=+$,/?#[]end";
        return service.stringUrlEncoded(this.client.getHost(), stringPath, requestOptions, context);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlEncodedAsync(RequestOptions requestOptions) {
        return stringUrlEncodedWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlEncodedAsync(RequestOptions requestOptions, Context context) {
        return stringUrlEncodedWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlEncoded(RequestOptions requestOptions) {
        stringUrlEncodedAsync(requestOptions).block();
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return stringUrlEncodedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlNonEncodedWithResponseAsync(RequestOptions requestOptions) {
        final String stringPath = "begin!*'();:@&=+$,end";
        return FluxUtil.withContext(
                context -> service.stringUrlNonEncoded(this.client.getHost(), stringPath, requestOptions, context));
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlNonEncodedWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String stringPath = "begin!*'();:@&=+$,end";
        return service.stringUrlNonEncoded(this.client.getHost(), stringPath, requestOptions, context);
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlNonEncodedAsync(RequestOptions requestOptions) {
        return stringUrlNonEncodedWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlNonEncodedAsync(RequestOptions requestOptions, Context context) {
        return stringUrlNonEncodedWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlNonEncoded(RequestOptions requestOptions) {
        stringUrlNonEncodedAsync(requestOptions).block();
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlNonEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return stringUrlNonEncodedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponseAsync(RequestOptions requestOptions) {
        final String stringPath = "";
        return FluxUtil.withContext(
                context -> service.stringEmpty(this.client.getHost(), stringPath, requestOptions, context));
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String stringPath = "";
        return service.stringEmpty(this.client.getHost(), stringPath, requestOptions, context);
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringEmptyAsync(RequestOptions requestOptions) {
        return stringEmptyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringEmptyAsync(RequestOptions requestOptions, Context context) {
        return stringEmptyWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringEmpty(RequestOptions requestOptions) {
        stringEmptyAsync(requestOptions).block();
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return stringEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponseAsync(String stringPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.stringNull(this.client.getHost(), stringPath, requestOptions, context));
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponseAsync(
            String stringPath, RequestOptions requestOptions, Context context) {
        return service.stringNull(this.client.getHost(), stringPath, requestOptions, context);
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringNullAsync(String stringPath, RequestOptions requestOptions) {
        return stringNullWithResponseAsync(stringPath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringNullAsync(String stringPath, RequestOptions requestOptions, Context context) {
        return stringNullWithResponseAsync(stringPath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringNull(String stringPath, RequestOptions requestOptions) {
        stringNullAsync(stringPath, requestOptions).block();
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringNullWithResponse(String stringPath, RequestOptions requestOptions, Context context) {
        return stringNullWithResponseAsync(stringPath, requestOptions, context).block();
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return using uri with 'green color' in path parameter.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponseAsync(String enumPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.enumValid(this.client.getHost(), enumPath, requestOptions, context));
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return using uri with 'green color' in path parameter.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponseAsync(
            String enumPath, RequestOptions requestOptions, Context context) {
        return service.enumValid(this.client.getHost(), enumPath, requestOptions, context);
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return using uri with 'green color' in path parameter.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumValidAsync(String enumPath, RequestOptions requestOptions) {
        return enumValidWithResponseAsync(enumPath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return using uri with 'green color' in path parameter.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumValidAsync(String enumPath, RequestOptions requestOptions, Context context) {
        return enumValidWithResponseAsync(enumPath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumValid(String enumPath, RequestOptions requestOptions) {
        enumValidAsync(enumPath, requestOptions).block();
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return using uri with 'green color' in path parameter.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumValidWithResponse(String enumPath, RequestOptions requestOptions, Context context) {
        return enumValidWithResponseAsync(enumPath, requestOptions, context).block();
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null (should throw on the client before the request is sent on wire).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponseAsync(String enumPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.enumNull(this.client.getHost(), enumPath, requestOptions, context));
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (should throw on the client before the request is sent on wire).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponseAsync(
            String enumPath, RequestOptions requestOptions, Context context) {
        return service.enumNull(this.client.getHost(), enumPath, requestOptions, context);
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null (should throw on the client before the request is sent on wire).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumNullAsync(String enumPath, RequestOptions requestOptions) {
        return enumNullWithResponseAsync(enumPath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (should throw on the client before the request is sent on wire).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumNullAsync(String enumPath, RequestOptions requestOptions, Context context) {
        return enumNullWithResponseAsync(enumPath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumNull(String enumPath, RequestOptions requestOptions) {
        enumNullAsync(enumPath, requestOptions).block();
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (should throw on the client before the request is sent on wire).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumNullWithResponse(String enumPath, RequestOptions requestOptions, Context context) {
        return enumNullWithResponseAsync(enumPath, requestOptions, context).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponseAsync(String bytePath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.byteMultiByte(this.client.getHost(), bytePath, requestOptions, context));
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponseAsync(
            String bytePath, RequestOptions requestOptions, Context context) {
        return service.byteMultiByte(this.client.getHost(), bytePath, requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteMultiByteAsync(String bytePath, RequestOptions requestOptions) {
        return byteMultiByteWithResponseAsync(bytePath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteMultiByteAsync(String bytePath, RequestOptions requestOptions, Context context) {
        return byteMultiByteWithResponseAsync(bytePath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteMultiByte(String bytePath, RequestOptions requestOptions) {
        byteMultiByteAsync(bytePath, requestOptions).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteMultiByteWithResponse(String bytePath, RequestOptions requestOptions, Context context) {
        return byteMultiByteWithResponseAsync(bytePath, requestOptions, context).block();
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponseAsync(RequestOptions requestOptions) {
        final String bytePath = "";
        return FluxUtil.withContext(
                context -> service.byteEmpty(this.client.getHost(), bytePath, requestOptions, context));
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String bytePath = "";
        return service.byteEmpty(this.client.getHost(), bytePath, requestOptions, context);
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteEmptyAsync(RequestOptions requestOptions) {
        return byteEmptyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteEmptyAsync(RequestOptions requestOptions, Context context) {
        return byteEmptyWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteEmpty(RequestOptions requestOptions) {
        byteEmptyAsync(requestOptions).block();
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return byteEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponseAsync(String bytePath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.byteNull(this.client.getHost(), bytePath, requestOptions, context));
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponseAsync(
            String bytePath, RequestOptions requestOptions, Context context) {
        return service.byteNull(this.client.getHost(), bytePath, requestOptions, context);
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteNullAsync(String bytePath, RequestOptions requestOptions) {
        return byteNullWithResponseAsync(bytePath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteNullAsync(String bytePath, RequestOptions requestOptions, Context context) {
        return byteNullWithResponseAsync(bytePath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteNull(String bytePath, RequestOptions requestOptions) {
        byteNullAsync(bytePath, requestOptions).block();
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteNullWithResponse(String bytePath, RequestOptions requestOptions, Context context) {
        return byteNullWithResponseAsync(bytePath, requestOptions, context).block();
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponseAsync(RequestOptions requestOptions) {
        final String datePath = "2012-01-01";
        return FluxUtil.withContext(
                context -> service.dateValid(this.client.getHost(), datePath, requestOptions, context));
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String datePath = "2012-01-01";
        return service.dateValid(this.client.getHost(), datePath, requestOptions, context);
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateValidAsync(RequestOptions requestOptions) {
        return dateValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateValidAsync(RequestOptions requestOptions, Context context) {
        return dateValidWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateValid(RequestOptions requestOptions) {
        dateValidAsync(requestOptions).block();
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateValidWithResponse(RequestOptions requestOptions, Context context) {
        return dateValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null as date - this should throw or be unusable on the client side, depending on date representation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponseAsync(String datePath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.dateNull(this.client.getHost(), datePath, requestOptions, context));
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date - this should throw or be unusable on the client side, depending on date representation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponseAsync(
            String datePath, RequestOptions requestOptions, Context context) {
        return service.dateNull(this.client.getHost(), datePath, requestOptions, context);
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null as date - this should throw or be unusable on the client side, depending on date representation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateNullAsync(String datePath, RequestOptions requestOptions) {
        return dateNullWithResponseAsync(datePath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date - this should throw or be unusable on the client side, depending on date representation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateNullAsync(String datePath, RequestOptions requestOptions, Context context) {
        return dateNullWithResponseAsync(datePath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateNull(String datePath, RequestOptions requestOptions) {
        dateNullAsync(datePath, requestOptions).block();
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date - this should throw or be unusable on the client side, depending on date representation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateNullWithResponse(String datePath, RequestOptions requestOptions, Context context) {
        return dateNullWithResponseAsync(datePath, requestOptions, context).block();
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponseAsync(RequestOptions requestOptions) {
        final String dateTimePath = "2012-01-01T01:01:01Z";
        return FluxUtil.withContext(
                context -> service.dateTimeValid(this.client.getHost(), dateTimePath, requestOptions, context));
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        final String dateTimePath = "2012-01-01T01:01:01Z";
        return service.dateTimeValid(this.client.getHost(), dateTimePath, requestOptions, context);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeValidAsync(RequestOptions requestOptions) {
        return dateTimeValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeValidAsync(RequestOptions requestOptions, Context context) {
        return dateTimeValidWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeValid(RequestOptions requestOptions) {
        dateTimeValidAsync(requestOptions).block();
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeValidWithResponse(RequestOptions requestOptions, Context context) {
        return dateTimeValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null as date-time, should be disallowed or throw depending on representation of date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponseAsync(String dateTimePath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.dateTimeNull(this.client.getHost(), dateTimePath, requestOptions, context));
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date-time, should be disallowed or throw depending on representation of date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponseAsync(
            String dateTimePath, RequestOptions requestOptions, Context context) {
        return service.dateTimeNull(this.client.getHost(), dateTimePath, requestOptions, context);
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return null as date-time, should be disallowed or throw depending on representation of date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeNullAsync(String dateTimePath, RequestOptions requestOptions) {
        return dateTimeNullWithResponseAsync(dateTimePath, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date-time, should be disallowed or throw depending on representation of date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeNullAsync(String dateTimePath, RequestOptions requestOptions, Context context) {
        return dateTimeNullWithResponseAsync(dateTimePath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeNull(String dateTimePath, RequestOptions requestOptions) {
        dateTimeNullAsync(dateTimePath, requestOptions).block();
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date-time, should be disallowed or throw depending on representation of date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeNullWithResponse(
            String dateTimePath, RequestOptions requestOptions, Context context) {
        return dateTimeNullWithResponseAsync(dateTimePath, requestOptions, context).block();
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> base64UrlWithResponseAsync(String base64UrlPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.base64Url(this.client.getHost(), base64UrlPath, requestOptions, context));
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> base64UrlWithResponseAsync(
            String base64UrlPath, RequestOptions requestOptions, Context context) {
        return service.base64Url(this.client.getHost(), base64UrlPath, requestOptions, context);
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> base64UrlAsync(String base64UrlPath, RequestOptions requestOptions) {
        return base64UrlWithResponseAsync(base64UrlPath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> base64UrlAsync(String base64UrlPath, RequestOptions requestOptions, Context context) {
        return base64UrlWithResponseAsync(base64UrlPath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void base64Url(String base64UrlPath, RequestOptions requestOptions) {
        base64UrlAsync(base64UrlPath, requestOptions).block();
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> base64UrlWithResponse(String base64UrlPath, RequestOptions requestOptions, Context context) {
        return base64UrlWithResponseAsync(base64UrlPath, requestOptions, context).block();
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayCsvInPathWithResponseAsync(String arrayPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayCsvInPath(this.client.getHost(), arrayPath, requestOptions, context));
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayCsvInPathWithResponseAsync(
            String arrayPath, RequestOptions requestOptions, Context context) {
        return service.arrayCsvInPath(this.client.getHost(), arrayPath, requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayCsvInPathAsync(String arrayPath, RequestOptions requestOptions) {
        return arrayCsvInPathWithResponseAsync(arrayPath, requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayCsvInPathAsync(String arrayPath, RequestOptions requestOptions, Context context) {
        return arrayCsvInPathWithResponseAsync(arrayPath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayCsvInPath(String arrayPath, RequestOptions requestOptions) {
        arrayCsvInPathAsync(arrayPath, requestOptions).block();
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayCsvInPathWithResponse(String arrayPath, RequestOptions requestOptions, Context context) {
        return arrayCsvInPathWithResponseAsync(arrayPath, requestOptions, context).block();
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> unixTimeUrlWithResponseAsync(long unixTimeUrlPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.unixTimeUrl(this.client.getHost(), unixTimeUrlPath, requestOptions, context));
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> unixTimeUrlWithResponseAsync(
            long unixTimeUrlPath, RequestOptions requestOptions, Context context) {
        return service.unixTimeUrl(this.client.getHost(), unixTimeUrlPath, requestOptions, context);
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> unixTimeUrlAsync(long unixTimeUrlPath, RequestOptions requestOptions) {
        return unixTimeUrlWithResponseAsync(unixTimeUrlPath, requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> unixTimeUrlAsync(long unixTimeUrlPath, RequestOptions requestOptions, Context context) {
        return unixTimeUrlWithResponseAsync(unixTimeUrlPath, requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void unixTimeUrl(long unixTimeUrlPath, RequestOptions requestOptions) {
        unixTimeUrlAsync(unixTimeUrlPath, requestOptions).block();
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> unixTimeUrlWithResponse(
            long unixTimeUrlPath, RequestOptions requestOptions, Context context) {
        return unixTimeUrlWithResponseAsync(unixTimeUrlPath, requestOptions, context).block();
    }
}
