package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.PrimitivesImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class, isAsync = true)
public final class PrimitiveAsyncClient {
    private final PrimitivesImpl serviceClient;

    /**
     * Initializes an instance of Primitives client.
     *
     * @param serviceClient the service client implementation.
     */
    PrimitiveAsyncClient(PrimitivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with integer properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Integer
     *     field2: Integer
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with integer properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getIntWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with integer properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Integer
     *     field2: Integer
     * }
     * }</pre>
     *
     * @param complexBody Please put -1 and 2.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putIntWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with long properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Long
     *     field2: Long
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with long properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getLongWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getLongWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with long properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Long
     *     field2: Long
     * }
     * }</pre>
     *
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putLongWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with float properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Float
     *     field2: Float
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with float properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getFloatWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getFloatWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with float properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Float
     *     field2: Float
     * }
     * }</pre>
     *
     * @param complexBody Please put 1.05 and -0.003.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putFloatWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with double properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Double
     *     field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose: Double
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with double properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDoubleWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDoubleWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with double properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field1: Double
     *     field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose: Double
     * }
     * }</pre>
     *
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDoubleWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with bool properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     fieldTrue: Boolean
     *     fieldFalse: Boolean
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with bool properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getBoolWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBoolWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with bool properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     fieldTrue: Boolean
     *     fieldFalse: Boolean
     * }
     * }</pre>
     *
     * @param complexBody Please put true and false.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBoolWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putBoolWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with string properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     empty: String
     *     nullProperty: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with string properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getStringWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getStringWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with string properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     empty: String
     *     nullProperty: String
     * }
     * }</pre>
     *
     * @param complexBody Please put 'goodrequest', '', and null.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putStringWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with date properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     leap: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with date properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDateWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDateWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with date properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     leap: String
     * }
     * }</pre>
     *
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDateWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with datetime properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     now: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with datetime properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDateTimeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDateTimeWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with datetime properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     now: String
     * }
     * }</pre>
     *
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDateTimeWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     now: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with datetimeRfc1123 properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDateTimeRfc1123WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDateTimeRfc1123WithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     *     now: String
     * }
     * }</pre>
     *
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123WithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDateTimeRfc1123WithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with duration properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with duration properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDurationWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDurationWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with duration properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: String
     * }
     * }</pre>
     *
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDurationWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with byte properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: byte[]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return complex types with byte properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getByteWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getByteWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with byte properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     field: byte[]
     * }
     * }</pre>
     *
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putByteWithResponseAsync(complexBody, requestOptions);
    }
}
