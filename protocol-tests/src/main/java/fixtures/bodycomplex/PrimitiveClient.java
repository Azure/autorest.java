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
     *     field: LocalDate
     *     leap: LocalDate
     * }
     * }</pre>
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
     *     field: LocalDate
     *     leap: LocalDate
     * }
     * }</pre>
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
     *     field: LocalDate
     *     leap: LocalDate
     * }
     * }</pre>
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
     *     field: LocalDate
     *     leap: LocalDate
     * }
     * }</pre>
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
     *     field: OffsetDateTime
     *     now: OffsetDateTime
     * }
     * }</pre>
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
     *     field: OffsetDateTime
     *     now: OffsetDateTime
     * }
     * }</pre>
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
     *     field: OffsetDateTime
     *     now: OffsetDateTime
     * }
     * }</pre>
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
     *     field: OffsetDateTime
     *     now: OffsetDateTime
     * }
     * }</pre>
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
     *     field: DateTimeRfc1123
     *     now: DateTimeRfc1123
     * }
     * }</pre>
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
     *     field: DateTimeRfc1123
     *     now: DateTimeRfc1123
     * }
     * }</pre>
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
     *     field: DateTimeRfc1123
     *     now: DateTimeRfc1123
     * }
     * }</pre>
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
     *     field: DateTimeRfc1123
     *     now: DateTimeRfc1123
     * }
     * }</pre>
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
     *     field: Duration
     * }
     * }</pre>
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
     *     field: Duration
     * }
     * }</pre>
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
     *     field: Duration
     * }
     * }</pre>
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
     *     field: Duration
     * }
     * }</pre>
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putByteWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putByteWithResponse(complexBody, requestOptions, context);
    }
}
