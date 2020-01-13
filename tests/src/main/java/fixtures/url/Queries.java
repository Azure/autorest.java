package fixtures.url;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Base64Util;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import fixtures.url.models.ErrorException;
import fixtures.url.models.UriColor;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Queries.
 */
public final class Queries {
    /**
     * The proxy service used to perform REST calls.
     */
    private QueriesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestUrlTestService client;

    /**
     * Initializes an instance of Queries.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Queries(AutoRestUrlTestService client) {
        this.service = RestProxy.create(QueriesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestUrlTestServiceQueries to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlTestServiceQueries")
    private interface QueriesService {
        @Get("/queries/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getBooleanTrue(@HostParam("$host") String host, @QueryParam("boolQuery") boolean boolQuery);

        @Get("/queries/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getBooleanFalse(@HostParam("$host") String host, @QueryParam("boolQuery") boolean boolQuery);

        @Get("/queries/bool/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getBooleanNull(@HostParam("$host") String host, @QueryParam("boolQuery") Boolean boolQuery);

        @Get("/queries/int/1000000")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getIntOneMillion(@HostParam("$host") String host, @QueryParam("intQuery") float intQuery);

        @Get("/queries/int/-1000000")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getIntNegativeOneMillion(@HostParam("$host") String host, @QueryParam("intQuery") float intQuery);

        @Get("/queries/int/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getIntNull(@HostParam("$host") String host, @QueryParam("intQuery") Integer intQuery);

        @Get("/queries/long/10000000000")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getTenBillion(@HostParam("$host") String host, @QueryParam("longQuery") float longQuery);

        @Get("/queries/long/-10000000000")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getNegativeTenBillion(@HostParam("$host") String host, @QueryParam("longQuery") float longQuery);

        @Get("/queries/long/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getLongNull(@HostParam("$host") String host, @QueryParam("longQuery") Long longQuery);

        @Get("/queries/float/1.034E+20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> floatScientificPositive(@HostParam("$host") String host, @QueryParam("floatQuery") float floatQuery);

        @Get("/queries/float/-1.034E-20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> floatScientificNegative(@HostParam("$host") String host, @QueryParam("floatQuery") float floatQuery);

        @Get("/queries/float/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> floatNull(@HostParam("$host") String host, @QueryParam("floatQuery") Float floatQuery);

        @Get("/queries/double/9999999.999")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> doubleDecimalPositive(@HostParam("$host") String host, @QueryParam("doubleQuery") double doubleQuery);

        @Get("/queries/double/-9999999.999")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> doubleDecimalNegative(@HostParam("$host") String host, @QueryParam("doubleQuery") double doubleQuery);

        @Get("/queries/double/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> doubleNull(@HostParam("$host") String host, @QueryParam("doubleQuery") Double doubleQuery);

        @Get("/queries/string/unicode/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringUnicode(@HostParam("$host") String host, @QueryParam("stringQuery") String stringQuery);

        @Get("/queries/string/begin%21%2A%27%28%29%3B%3A%40%20%26%3D%2B%24%2C%2F%3F%23%5B%5Dend")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringUrlEncoded(@HostParam("$host") String host, @QueryParam("stringQuery") String stringQuery);

        @Get("/queries/string/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringEmpty(@HostParam("$host") String host, @QueryParam("stringQuery") String stringQuery);

        @Get("/queries/string/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringNull(@HostParam("$host") String host, @QueryParam("stringQuery") String stringQuery);

        @Get("/queries/enum/green%20color")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> enumValid(@HostParam("$host") String host, @QueryParam("enumQuery") UriColor enumQuery);

        @Get("/queries/enum/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> enumNull(@HostParam("$host") String host, @QueryParam("enumQuery") UriColor enumQuery);

        @Get("/queries/byte/multibyte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> byteMultiByte(@HostParam("$host") String host, @QueryParam("byteQuery") String byteQuery);

        @Get("/queries/byte/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> byteEmpty(@HostParam("$host") String host, @QueryParam("byteQuery") String byteQuery);

        @Get("/queries/byte/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> byteNull(@HostParam("$host") String host, @QueryParam("byteQuery") String byteQuery);

        @Get("/queries/date/2012-01-01")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateValid(@HostParam("$host") String host, @QueryParam("dateQuery") LocalDate dateQuery);

        @Get("/queries/date/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateNull(@HostParam("$host") String host, @QueryParam("dateQuery") LocalDate dateQuery);

        @Get("/queries/datetime/2012-01-01T01%3A01%3A01Z")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateTimeValid(@HostParam("$host") String host, @QueryParam("dateTimeQuery") OffsetDateTime dateTimeQuery);

        @Get("/queries/datetime/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateTimeNull(@HostParam("$host") String host, @QueryParam("dateTimeQuery") OffsetDateTime dateTimeQuery);

        @Get("/queries/array/csv/string/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringCsvValid(@HostParam("$host") String host, @QueryParam("arrayQuery") String arrayQuery);

        @Get("/queries/array/csv/string/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringCsvNull(@HostParam("$host") String host, @QueryParam("arrayQuery") String arrayQuery);

        @Get("/queries/array/csv/string/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringCsvEmpty(@HostParam("$host") String host, @QueryParam("arrayQuery") String arrayQuery);

        @Get("/queries/array/ssv/string/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringSsvValid(@HostParam("$host") String host, @QueryParam("arrayQuery") String arrayQuery);

        @Get("/queries/array/tsv/string/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringTsvValid(@HostParam("$host") String host, @QueryParam("arrayQuery") String arrayQuery);

        @Get("/queries/array/pipes/string/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringPipesValid(@HostParam("$host") String host, @QueryParam("arrayQuery") String arrayQuery);
    }

    /**
     * Get true Boolean value on path.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final boolean boolQuery = true;
        return service.getBooleanTrue(this.client.getHost(), boolQuery);
    }

    /**
     * Get true Boolean value on path.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanTrueAsync() {
        return getBooleanTrueWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get true Boolean value on path.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanTrue() {
        getBooleanTrueAsync().block();
    }

    /**
     * Get false Boolean value on path.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final boolean boolQuery = false;
        return service.getBooleanFalse(this.client.getHost(), boolQuery);
    }

    /**
     * Get false Boolean value on path.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanFalseAsync() {
        return getBooleanFalseWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get false Boolean value on path.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanFalse() {
        getBooleanFalseAsync().block();
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     * 
     * @param boolQuery MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanNullWithResponseAsync(Boolean boolQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBooleanNull(this.client.getHost(), boolQuery);
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     * 
     * @param boolQuery MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanNullAsync(Boolean boolQuery) {
        return getBooleanNullWithResponseAsync(boolQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     * 
     * @param boolQuery MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanNull(Boolean boolQuery) {
        getBooleanNullAsync(boolQuery).block();
    }

    /**
     * Get '1000000' integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float intQuery = 1000000f;
        return service.getIntOneMillion(this.client.getHost(), intQuery);
    }

    /**
     * Get '1000000' integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntOneMillionAsync() {
        return getIntOneMillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1000000' integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntOneMillion() {
        getIntOneMillionAsync().block();
    }

    /**
     * Get '-1000000' integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float intQuery = -1000000f;
        return service.getIntNegativeOneMillion(this.client.getHost(), intQuery);
    }

    /**
     * Get '-1000000' integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNegativeOneMillionAsync() {
        return getIntNegativeOneMillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1000000' integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNegativeOneMillion() {
        getIntNegativeOneMillionAsync().block();
    }

    /**
     * Get null integer value (no query parameter).
     * 
     * @param intQuery MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNullWithResponseAsync(Integer intQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getIntNull(this.client.getHost(), intQuery);
    }

    /**
     * Get null integer value (no query parameter).
     * 
     * @param intQuery MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNullAsync(Integer intQuery) {
        return getIntNullWithResponseAsync(intQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null integer value (no query parameter).
     * 
     * @param intQuery MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNull(Integer intQuery) {
        getIntNullAsync(intQuery).block();
    }

    /**
     * Get '10000000000' 64 bit integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float longQuery = 10000000000f;
        return service.getTenBillion(this.client.getHost(), longQuery);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getTenBillionAsync() {
        return getTenBillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '10000000000' 64 bit integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getTenBillion() {
        getTenBillionAsync().block();
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float longQuery = -10000000000f;
        return service.getNegativeTenBillion(this.client.getHost(), longQuery);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getNegativeTenBillionAsync() {
        return getNegativeTenBillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getNegativeTenBillion() {
        getNegativeTenBillionAsync().block();
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     * 
     * @param longQuery MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLongNullWithResponseAsync(Long longQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLongNull(this.client.getHost(), longQuery);
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     * 
     * @param longQuery MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getLongNullAsync(Long longQuery) {
        return getLongNullWithResponseAsync(longQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     * 
     * @param longQuery MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getLongNull(Long longQuery) {
        getLongNullAsync(longQuery).block();
    }

    /**
     * Get '1.034E+20' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float floatQuery = 103400000000000000000f;
        return service.floatScientificPositive(this.client.getHost(), floatQuery);
    }

    /**
     * Get '1.034E+20' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificPositiveAsync() {
        return floatScientificPositiveWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '1.034E+20' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificPositive() {
        floatScientificPositiveAsync().block();
    }

    /**
     * Get '-1.034E-20' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float floatQuery = -1.034E-20f;
        return service.floatScientificNegative(this.client.getHost(), floatQuery);
    }

    /**
     * Get '-1.034E-20' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificNegativeAsync() {
        return floatScientificNegativeWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-1.034E-20' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificNegative() {
        floatScientificNegativeAsync().block();
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param floatQuery MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatNullWithResponseAsync(Float floatQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.floatNull(this.client.getHost(), floatQuery);
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param floatQuery MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatNullAsync(Float floatQuery) {
        return floatNullWithResponseAsync(floatQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param floatQuery MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatNull(Float floatQuery) {
        floatNullAsync(floatQuery).block();
    }

    /**
     * Get '9999999.999' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final double doubleQuery = 9999999.999;
        return service.doubleDecimalPositive(this.client.getHost(), doubleQuery);
    }

    /**
     * Get '9999999.999' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalPositiveAsync() {
        return doubleDecimalPositiveWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '9999999.999' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalPositive() {
        doubleDecimalPositiveAsync().block();
    }

    /**
     * Get '-9999999.999' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final double doubleQuery = -9999999.999;
        return service.doubleDecimalNegative(this.client.getHost(), doubleQuery);
    }

    /**
     * Get '-9999999.999' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalNegativeAsync() {
        return doubleDecimalNegativeWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '-9999999.999' numeric value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalNegative() {
        doubleDecimalNegativeAsync().block();
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param doubleQuery MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleNullWithResponseAsync(Double doubleQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.doubleNull(this.client.getHost(), doubleQuery);
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param doubleQuery MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleNullAsync(Double doubleQuery) {
        return doubleNullWithResponseAsync(doubleQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param doubleQuery MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleNull(Double doubleQuery) {
        doubleNullAsync(doubleQuery).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String stringQuery = "啊齄丂狛狜隣郎隣兀﨩";
        return service.stringUnicode(this.client.getHost(), stringQuery);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUnicodeAsync() {
        return stringUnicodeWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUnicode() {
        stringUnicodeAsync().block();
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String stringQuery = "begin!*'();:@ &=+$,/?#[]end";
        return service.stringUrlEncoded(this.client.getHost(), stringQuery);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlEncodedAsync() {
        return stringUrlEncodedWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlEncoded() {
        stringUrlEncodedAsync().block();
    }

    /**
     * Get ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String stringQuery = "";
        return service.stringEmpty(this.client.getHost(), stringQuery);
    }

    /**
     * Get ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringEmptyAsync() {
        return stringEmptyWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringEmpty() {
        stringEmptyAsync().block();
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param stringQuery MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponseAsync(String stringQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.stringNull(this.client.getHost(), stringQuery);
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param stringQuery MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringNullAsync(String stringQuery) {
        return stringNullWithResponseAsync(stringQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param stringQuery MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringNull(String stringQuery) {
        stringNullAsync(stringQuery).block();
    }

    /**
     * Get using uri with query parameter 'green color'.
     * 
     * @param enumQuery MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponseAsync(UriColor enumQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.enumValid(this.client.getHost(), enumQuery);
    }

    /**
     * Get using uri with query parameter 'green color'.
     * 
     * @param enumQuery MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumValidAsync(UriColor enumQuery) {
        return enumValidWithResponseAsync(enumQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get using uri with query parameter 'green color'.
     * 
     * @param enumQuery MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumValid(UriColor enumQuery) {
        enumValidAsync(enumQuery).block();
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param enumQuery MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponseAsync(UriColor enumQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.enumNull(this.client.getHost(), enumQuery);
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param enumQuery MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumNullAsync(UriColor enumQuery) {
        return enumNullWithResponseAsync(enumQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param enumQuery MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumNull(UriColor enumQuery) {
        enumNullAsync(enumQuery).block();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param byteQuery MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponseAsync(byte[] byteQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String byteQueryConverted = Base64Util.encodeToString(byteQuery);
        return service.byteMultiByte(this.client.getHost(), byteQueryConverted);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param byteQuery MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteMultiByteAsync(byte[] byteQuery) {
        return byteMultiByteWithResponseAsync(byteQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param byteQuery MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteMultiByte(byte[] byteQuery) {
        byteMultiByteAsync(byteQuery).block();
    }

    /**
     * Get '' as byte array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final byte[] byteQuery = "".getBytes();
        String byteQueryConverted = Base64Util.encodeToString(byteQuery);
        return service.byteEmpty(this.client.getHost(), byteQueryConverted);
    }

    /**
     * Get '' as byte array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteEmptyAsync() {
        return byteEmptyWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '' as byte array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteEmpty() {
        byteEmptyAsync().block();
    }

    /**
     * Get null as byte array (no query parameters in uri).
     * 
     * @param byteQuery MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponseAsync(byte[] byteQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String byteQueryConverted = Base64Util.encodeToString(byteQuery);
        return service.byteNull(this.client.getHost(), byteQueryConverted);
    }

    /**
     * Get null as byte array (no query parameters in uri).
     * 
     * @param byteQuery MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteNullAsync(byte[] byteQuery) {
        return byteNullWithResponseAsync(byteQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as byte array (no query parameters in uri).
     * 
     * @param byteQuery MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteNull(byte[] byteQuery) {
        byteNullAsync(byteQuery).block();
    }

    /**
     * Get '2012-01-01' as date.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final LocalDate dateQuery = LocalDate.parse("2012-01-01");
        return service.dateValid(this.client.getHost(), dateQuery);
    }

    /**
     * Get '2012-01-01' as date.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateValidAsync() {
        return dateValidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01' as date.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateValid() {
        dateValidAsync().block();
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     * 
     * @param dateQuery MISSING·SCHEMA-DESCRIPTION-DATE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponseAsync(LocalDate dateQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.dateNull(this.client.getHost(), dateQuery);
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     * 
     * @param dateQuery MISSING·SCHEMA-DESCRIPTION-DATE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateNullAsync(LocalDate dateQuery) {
        return dateNullWithResponseAsync(dateQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     * 
     * @param dateQuery MISSING·SCHEMA-DESCRIPTION-DATE.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateNull(LocalDate dateQuery) {
        dateNullAsync(dateQuery).block();
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final OffsetDateTime dateTimeQuery = OffsetDateTime.parse("2012-01-01T01:01:01Z");
        return service.dateTimeValid(this.client.getHost(), dateTimeQuery);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeValidAsync() {
        return dateTimeValidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeValid() {
        dateTimeValidAsync().block();
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     * 
     * @param dateTimeQuery MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponseAsync(OffsetDateTime dateTimeQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.dateTimeNull(this.client.getHost(), dateTimeQuery);
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     * 
     * @param dateTimeQuery MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeNullAsync(OffsetDateTime dateTimeQuery) {
        return dateTimeNullWithResponseAsync(dateTimeQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     * 
     * @param dateTimeQuery MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeNull(OffsetDateTime dateTimeQuery) {
        dateTimeNullAsync(dateTimeQuery).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvValidWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String arrayQueryConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayQuery, CollectionFormat.CSV);
        return service.arrayStringCsvValid(this.client.getHost(), arrayQueryConverted);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvValidAsync(List<String> arrayQuery) {
        return arrayStringCsvValidWithResponseAsync(arrayQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvValid(List<String> arrayQuery) {
        arrayStringCsvValidAsync(arrayQuery).block();
    }

    /**
     * Get a null array of string using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvNullWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String arrayQueryConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayQuery, CollectionFormat.CSV);
        return service.arrayStringCsvNull(this.client.getHost(), arrayQueryConverted);
    }

    /**
     * Get a null array of string using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvNullAsync(List<String> arrayQuery) {
        return arrayStringCsvNullWithResponseAsync(arrayQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a null array of string using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvNull(List<String> arrayQuery) {
        arrayStringCsvNullAsync(arrayQuery).block();
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvEmptyWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String arrayQueryConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayQuery, CollectionFormat.CSV);
        return service.arrayStringCsvEmpty(this.client.getHost(), arrayQueryConverted);
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringCsvEmptyAsync(List<String> arrayQuery) {
        return arrayStringCsvEmptyWithResponseAsync(arrayQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvEmpty(List<String> arrayQuery) {
        arrayStringCsvEmptyAsync(arrayQuery).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringSsvValidWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String arrayQueryConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayQuery, CollectionFormat.SSV);
        return service.arrayStringSsvValid(this.client.getHost(), arrayQueryConverted);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringSsvValidAsync(List<String> arrayQuery) {
        return arrayStringSsvValidWithResponseAsync(arrayQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringSsvValid(List<String> arrayQuery) {
        arrayStringSsvValidAsync(arrayQuery).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringTsvValidWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String arrayQueryConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayQuery, CollectionFormat.TSV);
        return service.arrayStringTsvValid(this.client.getHost(), arrayQueryConverted);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringTsvValidAsync(List<String> arrayQuery) {
        return arrayStringTsvValidWithResponseAsync(arrayQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringTsvValid(List<String> arrayQuery) {
        arrayStringTsvValidAsync(arrayQuery).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringPipesValidWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String arrayQueryConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayQuery, CollectionFormat.PIPES);
        return service.arrayStringPipesValid(this.client.getHost(), arrayQueryConverted);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringPipesValidAsync(List<String> arrayQuery) {
        return arrayStringPipesValidWithResponseAsync(arrayQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format.
     * 
     * @param arrayQuery MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringPipesValid(List<String> arrayQuery) {
        arrayStringPipesValidAsync(arrayQuery).block();
    }
}
