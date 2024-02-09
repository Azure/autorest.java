// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;

import com.azure.core.annotation.ServiceMethod;

import com.azure.core.http.rest.Response;

import com.azure.core.util.Context;

import fixtures.bodycomplex.implementation.PrimitivesImpl;
import fixtures.bodycomplex.implementation.models.BooleanWrapper;
import fixtures.bodycomplex.implementation.models.ByteWrapper;
import fixtures.bodycomplex.implementation.models.Datetimerfc1123Wrapper;
import fixtures.bodycomplex.implementation.models.DatetimeWrapper;
import fixtures.bodycomplex.implementation.models.DateWrapper;
import fixtures.bodycomplex.implementation.models.DoubleWrapper;
import fixtures.bodycomplex.implementation.models.DurationWrapper;
import fixtures.bodycomplex.implementation.models.ErrorException;
import fixtures.bodycomplex.implementation.models.FloatWrapper;
import fixtures.bodycomplex.implementation.models.IntWrapper;
import fixtures.bodycomplex.implementation.models.LongWrapper;
import fixtures.bodycomplex.implementation.models.StringWrapper;

/**
 * Initializes a new instance of the synchronous AutoRestComplexTestService type.
 */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class PrimitiveClient {
    @Generated
    private final PrimitivesImpl serviceClient;

    /**
     * Initializes an instance of PrimitiveClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PrimitiveClient(PrimitivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with integer properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with integer properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<IntWrapper> getIntWithResponse(Context context) {
        return this.serviceClient.getIntWithResponse(context);
    }

    /**
     * Get complex types with integer properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with integer properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IntWrapper getInt() {
        return this.serviceClient.getInt();
    }

    /**
     * Put complex types with integer properties.
     * 
     * @param complexBody Please put -1 and 2.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putIntWithResponse(IntWrapper complexBody, Context context) {
        return this.serviceClient.putIntWithResponse(complexBody, context);
    }

    /**
     * Put complex types with integer properties.
     * 
     * @param complexBody Please put -1 and 2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putInt(IntWrapper complexBody) {
        this.serviceClient.putInt(complexBody);
    }

    /**
     * Get complex types with long properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with long properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<LongWrapper> getLongWithResponse(Context context) {
        return this.serviceClient.getLongWithResponse(context);
    }

    /**
     * Get complex types with long properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with long properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LongWrapper getLong() {
        return this.serviceClient.getLong();
    }

    /**
     * Put complex types with long properties.
     * 
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putLongWithResponse(LongWrapper complexBody, Context context) {
        return this.serviceClient.putLongWithResponse(complexBody, context);
    }

    /**
     * Put complex types with long properties.
     * 
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLong(LongWrapper complexBody) {
        this.serviceClient.putLong(complexBody);
    }

    /**
     * Get complex types with float properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with float properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<FloatWrapper> getFloatWithResponse(Context context) {
        return this.serviceClient.getFloatWithResponse(context);
    }

    /**
     * Get complex types with float properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with float properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FloatWrapper getFloat() {
        return this.serviceClient.getFloat();
    }

    /**
     * Put complex types with float properties.
     * 
     * @param complexBody Please put 1.05 and -0.003.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putFloatWithResponse(FloatWrapper complexBody, Context context) {
        return this.serviceClient.putFloatWithResponse(complexBody, context);
    }

    /**
     * Put complex types with float properties.
     * 
     * @param complexBody Please put 1.05 and -0.003.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloat(FloatWrapper complexBody) {
        this.serviceClient.putFloat(complexBody);
    }

    /**
     * Get complex types with double properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with double properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DoubleWrapper> getDoubleWithResponse(Context context) {
        return this.serviceClient.getDoubleWithResponse(context);
    }

    /**
     * Get complex types with double properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with double properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DoubleWrapper getDouble() {
        return this.serviceClient.getDouble();
    }

    /**
     * Put complex types with double properties.
     * 
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDoubleWithResponse(DoubleWrapper complexBody, Context context) {
        return this.serviceClient.putDoubleWithResponse(complexBody, context);
    }

    /**
     * Put complex types with double properties.
     * 
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDouble(DoubleWrapper complexBody) {
        this.serviceClient.putDouble(complexBody);
    }

    /**
     * Get complex types with bool properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with bool properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BooleanWrapper> getBoolWithResponse(Context context) {
        return this.serviceClient.getBoolWithResponse(context);
    }

    /**
     * Get complex types with bool properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with bool properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BooleanWrapper getBool() {
        return this.serviceClient.getBool();
    }

    /**
     * Put complex types with bool properties.
     * 
     * @param complexBody Please put true and false.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putBoolWithResponse(BooleanWrapper complexBody, Context context) {
        return this.serviceClient.putBoolWithResponse(complexBody, context);
    }

    /**
     * Put complex types with bool properties.
     * 
     * @param complexBody Please put true and false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBool(BooleanWrapper complexBody) {
        this.serviceClient.putBool(complexBody);
    }

    /**
     * Get complex types with string properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with string properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<StringWrapper> getStringWithResponse(Context context) {
        return this.serviceClient.getStringWithResponse(context);
    }

    /**
     * Get complex types with string properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with string properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StringWrapper getString() {
        return this.serviceClient.getString();
    }

    /**
     * Put complex types with string properties.
     * 
     * @param complexBody Please put 'goodrequest', '', and null.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putStringWithResponse(StringWrapper complexBody, Context context) {
        return this.serviceClient.putStringWithResponse(complexBody, context);
    }

    /**
     * Put complex types with string properties.
     * 
     * @param complexBody Please put 'goodrequest', '', and null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putString(StringWrapper complexBody) {
        this.serviceClient.putString(complexBody);
    }

    /**
     * Get complex types with date properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with date properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DateWrapper> getDateWithResponse(Context context) {
        return this.serviceClient.getDateWithResponse(context);
    }

    /**
     * Get complex types with date properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with date properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DateWrapper getDate() {
        return this.serviceClient.getDate();
    }

    /**
     * Put complex types with date properties.
     * 
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateWithResponse(DateWrapper complexBody, Context context) {
        return this.serviceClient.putDateWithResponse(complexBody, context);
    }

    /**
     * Put complex types with date properties.
     * 
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDate(DateWrapper complexBody) {
        this.serviceClient.putDate(complexBody);
    }

    /**
     * Get complex types with datetime properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetime properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DatetimeWrapper> getDateTimeWithResponse(Context context) {
        return this.serviceClient.getDateTimeWithResponse(context);
    }

    /**
     * Get complex types with datetime properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetime properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DatetimeWrapper getDateTime() {
        return this.serviceClient.getDateTime();
    }

    /**
     * Put complex types with datetime properties.
     * 
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateTimeWithResponse(DatetimeWrapper complexBody, Context context) {
        return this.serviceClient.putDateTimeWithResponse(complexBody, context);
    }

    /**
     * Put complex types with datetime properties.
     * 
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTime(DatetimeWrapper complexBody) {
        this.serviceClient.putDateTime(complexBody);
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetimeRfc1123 properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Datetimerfc1123Wrapper> getDateTimeRfc1123WithResponse(Context context) {
        return this.serviceClient.getDateTimeRfc1123WithResponse(context);
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetimeRfc1123 properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Datetimerfc1123Wrapper getDateTimeRfc1123() {
        return this.serviceClient.getDateTimeRfc1123();
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     * 
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateTimeRfc1123WithResponse(Datetimerfc1123Wrapper complexBody, Context context) {
        return this.serviceClient.putDateTimeRfc1123WithResponse(complexBody, context);
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     * 
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123(Datetimerfc1123Wrapper complexBody) {
        this.serviceClient.putDateTimeRfc1123(complexBody);
    }

    /**
     * Get complex types with duration properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with duration properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DurationWrapper> getDurationWithResponse(Context context) {
        return this.serviceClient.getDurationWithResponse(context);
    }

    /**
     * Get complex types with duration properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with duration properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DurationWrapper getDuration() {
        return this.serviceClient.getDuration();
    }

    /**
     * Put complex types with duration properties.
     * 
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDurationWithResponse(DurationWrapper complexBody, Context context) {
        return this.serviceClient.putDurationWithResponse(complexBody, context);
    }

    /**
     * Put complex types with duration properties.
     * 
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDuration(DurationWrapper complexBody) {
        this.serviceClient.putDuration(complexBody);
    }

    /**
     * Get complex types with byte properties.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with byte properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ByteWrapper> getByteWithResponse(Context context) {
        return this.serviceClient.getByteWithResponse(context);
    }

    /**
     * Get complex types with byte properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with byte properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ByteWrapper getByte() {
        return this.serviceClient.getByte();
    }

    /**
     * Put complex types with byte properties.
     * 
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putByteWithResponse(ByteWrapper complexBody, Context context) {
        return this.serviceClient.putByteWithResponse(complexBody, context);
    }

    /**
     * Put complex types with byte properties.
     * 
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByte(ByteWrapper complexBody) {
        this.serviceClient.putByte(complexBody);
    }
}
