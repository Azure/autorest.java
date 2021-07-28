package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodycomplex.implementation.PrimitivesImpl;

/** Initializes a new instance of the synchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class PrimitiveClient {
    private final PrimitivesImpl serviceClient;

    /**
     * Initializes an instance of Primitives client.
     *
     * @param serviceClient the service client implementation.
     */
    PrimitiveClient(PrimitivesImpl serviceClient) {
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
     * @return complex types with integer properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getInt(RequestOptions requestOptions) {
        return this.serviceClient.getInt(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with integer properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getIntWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getIntWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putInt(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putInt(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putIntWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putIntWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with long properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getLong(RequestOptions requestOptions) {
        return this.serviceClient.getLong(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with long properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getLongWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getLongWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLong(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putLong(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putLongWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putLongWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with float properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getFloat(RequestOptions requestOptions) {
        return this.serviceClient.getFloat(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with float properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getFloatWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getFloatWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloat(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putFloat(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putFloatWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putFloatWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with double properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDouble(RequestOptions requestOptions) {
        return this.serviceClient.getDouble(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with double properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDoubleWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDoubleWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDouble(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putDouble(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDoubleWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putDoubleWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with bool properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getBool(RequestOptions requestOptions) {
        return this.serviceClient.getBool(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with bool properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getBoolWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getBoolWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBool(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putBool(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putBoolWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putBoolWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with string properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getString(RequestOptions requestOptions) {
        return this.serviceClient.getString(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with string properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getStringWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getStringWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putString(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putString(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putStringWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putStringWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with date properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDate(RequestOptions requestOptions) {
        return this.serviceClient.getDate(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with date properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDateWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDateWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDate(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putDate(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putDateWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with datetime properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDateTime(RequestOptions requestOptions) {
        return this.serviceClient.getDateTime(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with datetime properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDateTimeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDateTimeWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTime(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putDateTime(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateTimeWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putDateTimeWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with datetimeRfc1123 properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDateTimeRfc1123(RequestOptions requestOptions) {
        return this.serviceClient.getDateTimeRfc1123(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with datetimeRfc1123 properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDateTimeRfc1123WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDateTimeRfc1123WithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putDateTimeRfc1123(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateTimeRfc1123WithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putDateTimeRfc1123WithResponse(complexBody, requestOptions, context);
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
     * @return complex types with duration properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDuration(RequestOptions requestOptions) {
        return this.serviceClient.getDuration(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with duration properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDurationWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDurationWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDuration(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putDuration(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDurationWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putDurationWithResponse(complexBody, requestOptions, context);
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
     * @return complex types with byte properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getByte(RequestOptions requestOptions) {
        return this.serviceClient.getByte(requestOptions);
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
     * @param context The context to associate with this operation.
     * @return complex types with byte properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getByteWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getByteWithResponse(requestOptions, context);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByte(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putByte(complexBody, requestOptions);
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
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putByteWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putByteWithResponse(complexBody, requestOptions, context);
    }
}
