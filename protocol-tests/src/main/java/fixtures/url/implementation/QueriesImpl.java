package fixtures.url.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Queries. */
public final class QueriesImpl {
    /** The proxy service used to perform REST calls. */
    private final QueriesService service;

    /** The service client containing this operation class. */
    private final AutoRestUrlTestServiceImpl client;

    /**
     * Initializes an instance of QueriesImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    QueriesImpl(AutoRestUrlTestServiceImpl client) {
        this.service = RestProxy.create(QueriesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestUrlTestServiceQueries to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlTestServi")
    private interface QueriesService {
        @Get("/queries/bool/true")
        Mono<Response<Void>> getBooleanTrue(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/bool/false")
        Mono<Response<Void>> getBooleanFalse(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/bool/null")
        Mono<Response<Void>> getBooleanNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/int/1000000")
        Mono<Response<Void>> getIntOneMillion(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/int/-1000000")
        Mono<Response<Void>> getIntNegativeOneMillion(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/int/null")
        Mono<Response<Void>> getIntNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/long/10000000000")
        Mono<Response<Void>> getTenBillion(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/long/-10000000000")
        Mono<Response<Void>> getNegativeTenBillion(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/long/null")
        Mono<Response<Void>> getLongNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/float/1.034E+20")
        Mono<Response<Void>> floatScientificPositive(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/float/-1.034E-20")
        Mono<Response<Void>> floatScientificNegative(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/float/null")
        Mono<Response<Void>> floatNull(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/double/9999999.999")
        Mono<Response<Void>> doubleDecimalPositive(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/double/-9999999.999")
        Mono<Response<Void>> doubleDecimalNegative(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/double/null")
        Mono<Response<Void>> doubleNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/string/unicode/")
        Mono<Response<Void>> stringUnicode(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/string/begin%21%2A%27%28%29%3B%3A%40%20%26%3D%2B%24%2C%2F%3F%23%5B%5Dend")
        Mono<Response<Void>> stringUrlEncoded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/string/empty")
        Mono<Response<Void>> stringEmpty(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/string/null")
        Mono<Response<Void>> stringNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/enum/green%20color")
        Mono<Response<Void>> enumValid(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/enum/null")
        Mono<Response<Void>> enumNull(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/byte/multibyte")
        Mono<Response<Void>> byteMultiByte(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/byte/empty")
        Mono<Response<Void>> byteEmpty(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/byte/null")
        Mono<Response<Void>> byteNull(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/date/2012-01-01")
        Mono<Response<Void>> dateValid(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/date/null")
        Mono<Response<Void>> dateNull(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/datetime/2012-01-01T01%3A01%3A01Z")
        Mono<Response<Void>> dateTimeValid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/datetime/null")
        Mono<Response<Void>> dateTimeNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/csv/string/valid")
        Mono<Response<Void>> arrayStringCsvValid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/csv/string/null")
        Mono<Response<Void>> arrayStringCsvNull(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/csv/string/empty")
        Mono<Response<Void>> arrayStringCsvEmpty(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/none/string/empty")
        Mono<Response<Void>> arrayStringNoCollectionFormatEmpty(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/ssv/string/valid")
        Mono<Response<Void>> arrayStringSsvValid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/tsv/string/valid")
        Mono<Response<Void>> arrayStringTsvValid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/queries/array/pipes/string/valid")
        Mono<Response<Void>> arrayStringPipesValid(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getBooleanTrue(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getBooleanTrue(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanTrueAsync(RequestOptions requestOptions) {
        return getBooleanTrueWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanTrueAsync(RequestOptions requestOptions, Context context) {
        return getBooleanTrueWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanTrue(RequestOptions requestOptions) {
        getBooleanTrueAsync(requestOptions).block();
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanTrueWithResponse(RequestOptions requestOptions, Context context) {
        return getBooleanTrueWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getBooleanFalse(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getBooleanFalse(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanFalseAsync(RequestOptions requestOptions) {
        return getBooleanFalseWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanFalseAsync(RequestOptions requestOptions, Context context) {
        return getBooleanFalseWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanFalse(RequestOptions requestOptions) {
        getBooleanFalseAsync(requestOptions).block();
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanFalseWithResponse(RequestOptions requestOptions, Context context) {
        return getBooleanFalseWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getBooleanNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getBooleanNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanNullAsync(RequestOptions requestOptions) {
        return getBooleanNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanNullAsync(RequestOptions requestOptions, Context context) {
        return getBooleanNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanNull(RequestOptions requestOptions) {
        getBooleanNullAsync(requestOptions).block();
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanNullWithResponse(RequestOptions requestOptions, Context context) {
        return getBooleanNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getIntOneMillion(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getIntOneMillion(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntOneMillionAsync(RequestOptions requestOptions) {
        return getIntOneMillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntOneMillionAsync(RequestOptions requestOptions, Context context) {
        return getIntOneMillionWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntOneMillion(RequestOptions requestOptions) {
        getIntOneMillionAsync(requestOptions).block();
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return getIntOneMillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getIntNegativeOneMillion(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.getIntNegativeOneMillion(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNegativeOneMillionAsync(RequestOptions requestOptions) {
        return getIntNegativeOneMillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNegativeOneMillionAsync(RequestOptions requestOptions, Context context) {
        return getIntNegativeOneMillionWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNegativeOneMillion(RequestOptions requestOptions) {
        getIntNegativeOneMillionAsync(requestOptions).block();
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNegativeOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return getIntNegativeOneMillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getIntNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getIntNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNullAsync(RequestOptions requestOptions) {
        return getIntNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNullAsync(RequestOptions requestOptions, Context context) {
        return getIntNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNull(RequestOptions requestOptions) {
        getIntNullAsync(requestOptions).block();
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNullWithResponse(RequestOptions requestOptions, Context context) {
        return getIntNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getTenBillion(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getTenBillion(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getTenBillionAsync(RequestOptions requestOptions) {
        return getTenBillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getTenBillionAsync(RequestOptions requestOptions, Context context) {
        return getTenBillionWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getTenBillion(RequestOptions requestOptions) {
        getTenBillionAsync(requestOptions).block();
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return getTenBillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getNegativeTenBillion(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNegativeTenBillion(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getNegativeTenBillionAsync(RequestOptions requestOptions) {
        return getNegativeTenBillionWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getNegativeTenBillionAsync(RequestOptions requestOptions, Context context) {
        return getNegativeTenBillionWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getNegativeTenBillion(RequestOptions requestOptions) {
        getNegativeTenBillionAsync(requestOptions).block();
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getNegativeTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return getNegativeTenBillionWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLongNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getLongNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLongNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getLongNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getLongNullAsync(RequestOptions requestOptions) {
        return getLongNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getLongNullAsync(RequestOptions requestOptions, Context context) {
        return getLongNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getLongNull(RequestOptions requestOptions) {
        getLongNullAsync(requestOptions).block();
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getLongNullWithResponse(RequestOptions requestOptions, Context context) {
        return getLongNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.floatScientificPositive(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.floatScientificPositive(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificPositiveAsync(RequestOptions requestOptions) {
        return floatScientificPositiveWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificPositiveAsync(RequestOptions requestOptions, Context context) {
        return floatScientificPositiveWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificPositive(RequestOptions requestOptions) {
        floatScientificPositiveAsync(requestOptions).block();
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return floatScientificPositiveWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.floatScientificNegative(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.floatScientificNegative(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificNegativeAsync(RequestOptions requestOptions) {
        return floatScientificNegativeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificNegativeAsync(RequestOptions requestOptions, Context context) {
        return floatScientificNegativeWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificNegative(RequestOptions requestOptions) {
        floatScientificNegativeAsync(requestOptions).block();
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return floatScientificNegativeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.floatNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.floatNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatNullAsync(RequestOptions requestOptions) {
        return floatNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatNullAsync(RequestOptions requestOptions, Context context) {
        return floatNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatNull(RequestOptions requestOptions) {
        floatNullAsync(requestOptions).block();
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatNullWithResponse(RequestOptions requestOptions, Context context) {
        return floatNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.doubleDecimalPositive(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.doubleDecimalPositive(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalPositiveAsync(RequestOptions requestOptions) {
        return doubleDecimalPositiveWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalPositiveAsync(RequestOptions requestOptions, Context context) {
        return doubleDecimalPositiveWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalPositive(RequestOptions requestOptions) {
        doubleDecimalPositiveAsync(requestOptions).block();
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return doubleDecimalPositiveWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.doubleDecimalNegative(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.doubleDecimalNegative(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalNegativeAsync(RequestOptions requestOptions) {
        return doubleDecimalNegativeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalNegativeAsync(RequestOptions requestOptions, Context context) {
        return doubleDecimalNegativeWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalNegative(RequestOptions requestOptions) {
        doubleDecimalNegativeAsync(requestOptions).block();
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return doubleDecimalNegativeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.doubleNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.doubleNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleNullAsync(RequestOptions requestOptions) {
        return doubleNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleNullAsync(RequestOptions requestOptions, Context context) {
        return doubleNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleNull(RequestOptions requestOptions) {
        doubleNullAsync(requestOptions).block();
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleNullWithResponse(RequestOptions requestOptions, Context context) {
        return doubleNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.stringUnicode(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.stringUnicode(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUnicodeAsync(RequestOptions requestOptions) {
        return stringUnicodeWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUnicodeAsync(RequestOptions requestOptions, Context context) {
        return stringUnicodeWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUnicode(RequestOptions requestOptions) {
        stringUnicodeAsync(requestOptions).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUnicodeWithResponse(RequestOptions requestOptions, Context context) {
        return stringUnicodeWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.stringUrlEncoded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.stringUrlEncoded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlEncodedAsync(RequestOptions requestOptions) {
        return stringUrlEncodedWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlEncodedAsync(RequestOptions requestOptions, Context context) {
        return stringUrlEncodedWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlEncoded(RequestOptions requestOptions) {
        stringUrlEncodedAsync(requestOptions).block();
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return stringUrlEncodedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.stringEmpty(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.stringEmpty(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringEmptyAsync(RequestOptions requestOptions) {
        return stringEmptyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringEmptyAsync(RequestOptions requestOptions, Context context) {
        return stringEmptyWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringEmpty(RequestOptions requestOptions) {
        stringEmptyAsync(requestOptions).block();
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return stringEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.stringNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.stringNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringNullAsync(RequestOptions requestOptions) {
        return stringNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringNullAsync(RequestOptions requestOptions, Context context) {
        return stringNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringNull(RequestOptions requestOptions) {
        stringNullAsync(requestOptions).block();
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringNullWithResponse(RequestOptions requestOptions, Context context) {
        return stringNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.enumValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.enumValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumValidAsync(RequestOptions requestOptions) {
        return enumValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumValidAsync(RequestOptions requestOptions, Context context) {
        return enumValidWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumValid(RequestOptions requestOptions) {
        enumValidAsync(requestOptions).block();
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumValidWithResponse(RequestOptions requestOptions, Context context) {
        return enumValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.enumNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.enumNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumNullAsync(RequestOptions requestOptions) {
        return enumNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumNullAsync(RequestOptions requestOptions, Context context) {
        return enumNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumNull(RequestOptions requestOptions) {
        enumNullAsync(requestOptions).block();
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumNullWithResponse(RequestOptions requestOptions, Context context) {
        return enumNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.byteMultiByte(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.byteMultiByte(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteMultiByteAsync(RequestOptions requestOptions) {
        return byteMultiByteWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteMultiByteAsync(RequestOptions requestOptions, Context context) {
        return byteMultiByteWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteMultiByte(RequestOptions requestOptions) {
        byteMultiByteAsync(requestOptions).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteMultiByteWithResponse(RequestOptions requestOptions, Context context) {
        return byteMultiByteWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.byteEmpty(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.byteEmpty(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteEmptyAsync(RequestOptions requestOptions) {
        return byteEmptyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteEmptyAsync(RequestOptions requestOptions, Context context) {
        return byteEmptyWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteEmpty(RequestOptions requestOptions) {
        byteEmptyAsync(requestOptions).block();
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return byteEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.byteNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.byteNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteNullAsync(RequestOptions requestOptions) {
        return byteNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteNullAsync(RequestOptions requestOptions, Context context) {
        return byteNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteNull(RequestOptions requestOptions) {
        byteNullAsync(requestOptions).block();
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteNullWithResponse(RequestOptions requestOptions, Context context) {
        return byteNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.dateValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.dateValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateValidAsync(RequestOptions requestOptions) {
        return dateValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateValidAsync(RequestOptions requestOptions, Context context) {
        return dateValidWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateValid(RequestOptions requestOptions) {
        dateValidAsync(requestOptions).block();
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateValidWithResponse(RequestOptions requestOptions, Context context) {
        return dateValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.dateNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.dateNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateNullAsync(RequestOptions requestOptions) {
        return dateNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateNullAsync(RequestOptions requestOptions, Context context) {
        return dateNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateNull(RequestOptions requestOptions) {
        dateNullAsync(requestOptions).block();
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateNullWithResponse(RequestOptions requestOptions, Context context) {
        return dateNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.dateTimeValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.dateTimeValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeValidAsync(RequestOptions requestOptions) {
        return dateTimeValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeValidAsync(RequestOptions requestOptions, Context context) {
        return dateTimeValidWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeValid(RequestOptions requestOptions) {
        dateTimeValidAsync(requestOptions).block();
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeValidWithResponse(RequestOptions requestOptions, Context context) {
        return dateTimeValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.dateTimeNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.dateTimeNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeNullAsync(RequestOptions requestOptions) {
        return dateTimeNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeNullAsync(RequestOptions requestOptions, Context context) {
        return dateTimeNullWithResponseAsync(requestOptions, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeNull(RequestOptions requestOptions) {
        dateTimeNullAsync(requestOptions).block();
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeNullWithResponse(RequestOptions requestOptions, Context context) {
        return dateTimeNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringCsvValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.arrayStringCsvValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvValidAsync(RequestOptions requestOptions) {
        return arrayStringCsvValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvValidAsync(RequestOptions requestOptions, Context context) {
        return arrayStringCsvValidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvValid(RequestOptions requestOptions) {
        arrayStringCsvValidAsync(requestOptions).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringCsvValidWithResponse(RequestOptions requestOptions, Context context) {
        return arrayStringCsvValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvNullWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringCsvNull(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvNullWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.arrayStringCsvNull(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvNullAsync(RequestOptions requestOptions) {
        return arrayStringCsvNullWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvNullAsync(RequestOptions requestOptions, Context context) {
        return arrayStringCsvNullWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvNull(RequestOptions requestOptions) {
        arrayStringCsvNullAsync(requestOptions).block();
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringCsvNullWithResponse(RequestOptions requestOptions, Context context) {
        return arrayStringCsvNullWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvEmptyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringCsvEmpty(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvEmptyWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.arrayStringCsvEmpty(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvEmptyAsync(RequestOptions requestOptions) {
        return arrayStringCsvEmptyWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvEmptyAsync(RequestOptions requestOptions, Context context) {
        return arrayStringCsvEmptyWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvEmpty(RequestOptions requestOptions) {
        arrayStringCsvEmptyAsync(requestOptions).block();
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringCsvEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return arrayStringCsvEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringNoCollectionFormatEmptyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringNoCollectionFormatEmpty(this.client.getHost(), requestOptions, context));
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringNoCollectionFormatEmptyWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.arrayStringNoCollectionFormatEmpty(this.client.getHost(), requestOptions, context);
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringNoCollectionFormatEmptyAsync(RequestOptions requestOptions) {
        return arrayStringNoCollectionFormatEmptyWithResponseAsync(requestOptions)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringNoCollectionFormatEmptyAsync(RequestOptions requestOptions, Context context) {
        return arrayStringNoCollectionFormatEmptyWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringNoCollectionFormatEmpty(RequestOptions requestOptions) {
        arrayStringNoCollectionFormatEmptyAsync(requestOptions).block();
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringNoCollectionFormatEmptyWithResponse(
            RequestOptions requestOptions, Context context) {
        return arrayStringNoCollectionFormatEmptyWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringSsvValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringSsvValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringSsvValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.arrayStringSsvValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringSsvValidAsync(RequestOptions requestOptions) {
        return arrayStringSsvValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringSsvValidAsync(RequestOptions requestOptions, Context context) {
        return arrayStringSsvValidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringSsvValid(RequestOptions requestOptions) {
        arrayStringSsvValidAsync(requestOptions).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringSsvValidWithResponse(RequestOptions requestOptions, Context context) {
        return arrayStringSsvValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringTsvValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringTsvValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringTsvValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.arrayStringTsvValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringTsvValidAsync(RequestOptions requestOptions) {
        return arrayStringTsvValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringTsvValidAsync(RequestOptions requestOptions, Context context) {
        return arrayStringTsvValidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringTsvValid(RequestOptions requestOptions) {
        arrayStringTsvValidAsync(requestOptions).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringTsvValidWithResponse(RequestOptions requestOptions, Context context) {
        return arrayStringTsvValidWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringPipesValidWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.arrayStringPipesValid(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringPipesValidWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.arrayStringPipesValid(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringPipesValidAsync(RequestOptions requestOptions) {
        return arrayStringPipesValidWithResponseAsync(requestOptions).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringPipesValidAsync(RequestOptions requestOptions, Context context) {
        return arrayStringPipesValidWithResponseAsync(requestOptions, context)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringPipesValid(RequestOptions requestOptions) {
        arrayStringPipesValidAsync(requestOptions).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringPipesValidWithResponse(RequestOptions requestOptions, Context context) {
        return arrayStringPipesValidWithResponseAsync(requestOptions, context).block();
    }
}
