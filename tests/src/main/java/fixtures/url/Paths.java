package fixtures.url;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Base64Url;
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
 * Paths.
 */
public final class Paths {
    /**
     * The proxy service used to perform REST calls.
     */
    private PathsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestUrlTestService client;

    /**
     * Initializes an instance of Paths.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Paths(AutoRestUrlTestService client) {
        this.service = RestProxy.create(PathsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestUrlTestServicePaths
     * to be used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlTestServicePaths")
    private interface PathsService {
        @Get("/paths/bool/true/{boolPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getBooleanTrue(@HostParam("$host") String host, @PathParam("boolPath") boolean boolPath);

        @Get("/paths/bool/false/{boolPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getBooleanFalse(@HostParam("$host") String host, @PathParam("boolPath") boolean boolPath);

        @Get("/paths/int/1000000/{intPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getIntOneMillion(@HostParam("$host") String host, @PathParam("intPath") float intPath);

        @Get("/paths/int/-1000000/{intPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getIntNegativeOneMillion(@HostParam("$host") String host, @PathParam("intPath") float intPath);

        @Get("/paths/long/10000000000/{longPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getTenBillion(@HostParam("$host") String host, @PathParam("longPath") float longPath);

        @Get("/paths/long/-10000000000/{longPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getNegativeTenBillion(@HostParam("$host") String host, @PathParam("longPath") float longPath);

        @Get("/paths/float/1.034E+20/{floatPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> floatScientificPositive(@HostParam("$host") String host, @PathParam("floatPath") float floatPath);

        @Get("/paths/float/-1.034E-20/{floatPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> floatScientificNegative(@HostParam("$host") String host, @PathParam("floatPath") float floatPath);

        @Get("/paths/double/9999999.999/{doublePath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> doubleDecimalPositive(@HostParam("$host") String host, @PathParam("doublePath") double doublePath);

        @Get("/paths/double/-9999999.999/{doublePath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> doubleDecimalNegative(@HostParam("$host") String host, @PathParam("doublePath") double doublePath);

        @Get("/paths/string/unicode/{stringPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringUnicode(@HostParam("$host") String host, @PathParam("stringPath") String stringPath);

        @Get("/paths/string/begin%21%2A%27%28%29%3B%3A%40%20%26%3D%2B%24%2C%2F%3F%23%5B%5Dend/{stringPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringUrlEncoded(@HostParam("$host") String host, @PathParam("stringPath") String stringPath);

        @Get("/paths/string/empty/{stringPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringEmpty(@HostParam("$host") String host, @PathParam("stringPath") String stringPath);

        @Get("/paths/string/null/{stringPath}")
        @ExpectedResponses({400})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> stringNull(@HostParam("$host") String host, @PathParam("stringPath") String stringPath);

        @Get("/paths/enum/green%20color/{enumPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> enumValid(@HostParam("$host") String host, @PathParam("enumPath") UriColor enumPath);

        @Get("/paths/string/null/{enumPath}")
        @ExpectedResponses({400})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> enumNull(@HostParam("$host") String host, @PathParam("enumPath") UriColor enumPath);

        @Get("/paths/byte/multibyte/{bytePath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> byteMultiByte(@HostParam("$host") String host, @PathParam("bytePath") String bytePath);

        @Get("/paths/byte/empty/{bytePath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> byteEmpty(@HostParam("$host") String host, @PathParam("bytePath") String bytePath);

        @Get("/paths/byte/null/{bytePath}")
        @ExpectedResponses({400})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> byteNull(@HostParam("$host") String host, @PathParam("bytePath") String bytePath);

        @Get("/paths/date/2012-01-01/{datePath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateValid(@HostParam("$host") String host, @PathParam("datePath") LocalDate datePath);

        @Get("/paths/date/null/{datePath}")
        @ExpectedResponses({400})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateNull(@HostParam("$host") String host, @PathParam("datePath") LocalDate datePath);

        @Get("/paths/datetime/2012-01-01T01%3A01%3A01Z/{dateTimePath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateTimeValid(@HostParam("$host") String host, @PathParam("dateTimePath") OffsetDateTime dateTimePath);

        @Get("/paths/datetime/null/{dateTimePath}")
        @ExpectedResponses({400})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> dateTimeNull(@HostParam("$host") String host, @PathParam("dateTimePath") OffsetDateTime dateTimePath);

        @Get("/paths/string/bG9yZW0/{base64UrlPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> base64Url(@HostParam("$host") String host, @PathParam("base64UrlPath") Base64Url base64UrlPath);

        @Get("/paths/array/ArrayPath1%2cbegin%21%2A%27%28%29%3B%3A%40%20%26%3D%2B%24%2C%2F%3F%23%5B%5Dend%2c%2c/{arrayPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayCsvInPath(@HostParam("$host") String host, @PathParam("arrayPath") String arrayPath);

        @Get("/paths/int/1460505600/{unixTimeUrlPath}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> unixTimeUrl(@HostParam("$host") String host, @PathParam("unixTimeUrlPath") long unixTimeUrlPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final boolean boolPath = true;
        return service.getBooleanTrue(this.client.getHost(), boolPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanTrueAsync() {
        return getBooleanTrueWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanTrue() {
        getBooleanTrueAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final boolean boolPath = false;
        return service.getBooleanFalse(this.client.getHost(), boolPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getBooleanFalseAsync() {
        return getBooleanFalseWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanFalse() {
        getBooleanFalseAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float intPath = 1000000f;
        return service.getIntOneMillion(this.client.getHost(), intPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntOneMillionAsync() {
        return getIntOneMillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntOneMillion() {
        getIntOneMillionAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float intPath = -1000000f;
        return service.getIntNegativeOneMillion(this.client.getHost(), intPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getIntNegativeOneMillionAsync() {
        return getIntNegativeOneMillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNegativeOneMillion() {
        getIntNegativeOneMillionAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float longPath = 10000000000f;
        return service.getTenBillion(this.client.getHost(), longPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getTenBillionAsync() {
        return getTenBillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getTenBillion() {
        getTenBillionAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float longPath = -10000000000f;
        return service.getNegativeTenBillion(this.client.getHost(), longPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getNegativeTenBillionAsync() {
        return getNegativeTenBillionWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getNegativeTenBillion() {
        getNegativeTenBillionAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float floatPath = 103400000000000000000f;
        return service.floatScientificPositive(this.client.getHost(), floatPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificPositiveAsync() {
        return floatScientificPositiveWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificPositive() {
        floatScientificPositiveAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final float floatPath = -1.034E-20f;
        return service.floatScientificNegative(this.client.getHost(), floatPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> floatScientificNegativeAsync() {
        return floatScientificNegativeWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificNegative() {
        floatScientificNegativeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final double doublePath = 9999999.999;
        return service.doubleDecimalPositive(this.client.getHost(), doublePath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalPositiveAsync() {
        return doubleDecimalPositiveWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalPositive() {
        doubleDecimalPositiveAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final double doublePath = -9999999.999;
        return service.doubleDecimalNegative(this.client.getHost(), doublePath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> doubleDecimalNegativeAsync() {
        return doubleDecimalNegativeWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalNegative() {
        doubleDecimalNegativeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String stringPath = "啊齄丂狛狜隣郎隣兀﨩";
        return service.stringUnicode(this.client.getHost(), stringPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUnicodeAsync() {
        return stringUnicodeWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUnicode() {
        stringUnicodeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String stringPath = "begin!*'();:@ &=+$,/?#[]end";
        return service.stringUrlEncoded(this.client.getHost(), stringPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringUrlEncodedAsync() {
        return stringUrlEncodedWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlEncoded() {
        stringUrlEncodedAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String stringPath = "";
        return service.stringEmpty(this.client.getHost(), stringPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringEmptyAsync() {
        return stringEmptyWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringEmpty() {
        stringEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponseAsync(String stringPath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (stringPath == null) {
            throw new IllegalArgumentException("Parameter stringPath is required and cannot be null.");
        }
        return service.stringNull(this.client.getHost(), stringPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> stringNullAsync(String stringPath) {
        return stringNullWithResponseAsync(stringPath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringNull(String stringPath) {
        stringNullAsync(stringPath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponseAsync(UriColor enumPath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (enumPath == null) {
            throw new IllegalArgumentException("Parameter enumPath is required and cannot be null.");
        }
        return service.enumValid(this.client.getHost(), enumPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumValidAsync(UriColor enumPath) {
        return enumValidWithResponseAsync(enumPath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumValid(UriColor enumPath) {
        enumValidAsync(enumPath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponseAsync(UriColor enumPath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (enumPath == null) {
            throw new IllegalArgumentException("Parameter enumPath is required and cannot be null.");
        }
        return service.enumNull(this.client.getHost(), enumPath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> enumNullAsync(UriColor enumPath) {
        return enumNullWithResponseAsync(enumPath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumNull(UriColor enumPath) {
        enumNullAsync(enumPath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponseAsync(byte[] bytePath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bytePath == null) {
            throw new IllegalArgumentException("Parameter bytePath is required and cannot be null.");
        }
        String bytePathConverted = Base64Util.encodeToString(bytePath);
        return service.byteMultiByte(this.client.getHost(), bytePathConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteMultiByteAsync(byte[] bytePath) {
        return byteMultiByteWithResponseAsync(bytePath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteMultiByte(byte[] bytePath) {
        byteMultiByteAsync(bytePath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final byte[] bytePath = "".getBytes();
        String bytePathConverted = Base64Util.encodeToString(bytePath);
        return service.byteEmpty(this.client.getHost(), bytePathConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteEmptyAsync() {
        return byteEmptyWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteEmpty() {
        byteEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponseAsync(byte[] bytePath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bytePath == null) {
            throw new IllegalArgumentException("Parameter bytePath is required and cannot be null.");
        }
        String bytePathConverted = Base64Util.encodeToString(bytePath);
        return service.byteNull(this.client.getHost(), bytePathConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> byteNullAsync(byte[] bytePath) {
        return byteNullWithResponseAsync(bytePath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteNull(byte[] bytePath) {
        byteNullAsync(bytePath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final LocalDate datePath = LocalDate.parse("2012-01-01");
        return service.dateValid(this.client.getHost(), datePath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateValidAsync() {
        return dateValidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateValid() {
        dateValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponseAsync(LocalDate datePath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datePath == null) {
            throw new IllegalArgumentException("Parameter datePath is required and cannot be null.");
        }
        return service.dateNull(this.client.getHost(), datePath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateNullAsync(LocalDate datePath) {
        return dateNullWithResponseAsync(datePath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateNull(LocalDate datePath) {
        dateNullAsync(datePath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final OffsetDateTime dateTimePath = OffsetDateTime.parse("2012-01-01T01:01:01Z");
        return service.dateTimeValid(this.client.getHost(), dateTimePath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeValidAsync() {
        return dateTimeValidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeValid() {
        dateTimeValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponseAsync(OffsetDateTime dateTimePath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (dateTimePath == null) {
            throw new IllegalArgumentException("Parameter dateTimePath is required and cannot be null.");
        }
        return service.dateTimeNull(this.client.getHost(), dateTimePath);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> dateTimeNullAsync(OffsetDateTime dateTimePath) {
        return dateTimeNullWithResponseAsync(dateTimePath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeNull(OffsetDateTime dateTimePath) {
        dateTimeNullAsync(dateTimePath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> base64UrlWithResponseAsync(byte[] base64UrlPath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (base64UrlPath == null) {
            throw new IllegalArgumentException("Parameter base64UrlPath is required and cannot be null.");
        }
        Base64Url base64UrlPathConverted = Base64Url.encode(base64UrlPath);
        return service.base64Url(this.client.getHost(), base64UrlPathConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> base64UrlAsync(byte[] base64UrlPath) {
        return base64UrlWithResponseAsync(base64UrlPath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void base64Url(byte[] base64UrlPath) {
        base64UrlAsync(base64UrlPath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayCsvInPathWithResponseAsync(List<String> arrayPath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (arrayPath == null) {
            throw new IllegalArgumentException("Parameter arrayPath is required and cannot be null.");
        }
        String arrayPathConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(arrayPath, CollectionFormat.CSV);
        return service.arrayCsvInPath(this.client.getHost(), arrayPathConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayCsvInPathAsync(List<String> arrayPath) {
        return arrayCsvInPathWithResponseAsync(arrayPath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayCsvInPath(List<String> arrayPath) {
        arrayCsvInPathAsync(arrayPath).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> unixTimeUrlWithResponseAsync(OffsetDateTime unixTimeUrlPath) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (unixTimeUrlPath == null) {
            throw new IllegalArgumentException("Parameter unixTimeUrlPath is required and cannot be null.");
        }
        long unixTimeUrlPathConverted = unixTimeUrlPath.toEpochSecond();
        return service.unixTimeUrl(this.client.getHost(), unixTimeUrlPathConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> unixTimeUrlAsync(OffsetDateTime unixTimeUrlPath) {
        return unixTimeUrlWithResponseAsync(unixTimeUrlPath)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void unixTimeUrl(OffsetDateTime unixTimeUrlPath) {
        unixTimeUrlAsync(unixTimeUrlPath).block();
    }
}
