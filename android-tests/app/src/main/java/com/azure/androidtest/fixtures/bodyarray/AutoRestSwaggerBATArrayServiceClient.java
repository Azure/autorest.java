package com.azure.androidtest.fixtures.bodyarray;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.internal.util.serializer.JacksonAdapter;
import com.azure.android.core.internal.util.serializer.SerializerAdapter;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodyarray.implementation.ArraysImpl;
import com.azure.androidtest.fixtures.bodyarray.implementation.AutoRestSwaggerBATArrayServiceImpl;
import com.azure.androidtest.fixtures.bodyarray.models.Enum0;
import com.azure.androidtest.fixtures.bodyarray.models.Enum1;
import com.azure.androidtest.fixtures.bodyarray.models.ErrorException;
import com.azure.androidtest.fixtures.bodyarray.models.FooEnum;
import com.azure.androidtest.fixtures.bodyarray.models.Product;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestSwaggerBATArrayService type.
 */
public final class AutoRestSwaggerBATArrayServiceClient {
    private ArraysImpl serviceClient;

    /**
     * Initializes an instance of Arrays client.
     */
    AutoRestSwaggerBATArrayServiceClient(ArraysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null array value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null array value.
     */
    public Response<List<Integer>> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid array [1, 2, 3.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid array [1, 2, 3.
     */
    public Response<List<Integer>> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * Get empty array value [].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array value [].
     */
    public Response<List<Integer>> getEmptyWithRestResponse() {
        return this.serviceClient.getEmptyWithRestResponse();
    }

    /**
     * Set array value empty [].
     * 
     * @param arrayBody The empty array value [].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putEmptyWithRestResponse(List<String> arrayBody) {
        return this.serviceClient.putEmptyWithRestResponse(arrayBody);
    }

    /**
     * Get boolean array value [true, false, false, true].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, false, false, true].
     */
    public Response<List<Boolean>> getBooleanTfftWithRestResponse() {
        return this.serviceClient.getBooleanTfftWithRestResponse();
    }

    /**
     * Set array value empty [true, false, false, true].
     * 
     * @param arrayBody The array value [true, false, false, true].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBooleanTfftWithRestResponse(List<Boolean> arrayBody) {
        return this.serviceClient.putBooleanTfftWithRestResponse(arrayBody);
    }

    /**
     * Get boolean array value [true, null, false].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, null, false].
     */
    public Response<List<Boolean>> getBooleanInvalidNullWithRestResponse() {
        return this.serviceClient.getBooleanInvalidNullWithRestResponse();
    }

    /**
     * Get boolean array value [true, 'boolean', false].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, 'boolean', false].
     */
    public Response<List<Boolean>> getBooleanInvalidStringWithRestResponse() {
        return this.serviceClient.getBooleanInvalidStringWithRestResponse();
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    public Response<List<Integer>> getIntegerValidWithRestResponse() {
        return this.serviceClient.getIntegerValidWithRestResponse();
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     * 
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putIntegerValidWithRestResponse(List<Integer> arrayBody) {
        return this.serviceClient.putIntegerValidWithRestResponse(arrayBody);
    }

    /**
     * Get integer array value [1, null, 0].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, null, 0].
     */
    public Response<List<Integer>> getIntInvalidNullWithRestResponse() {
        return this.serviceClient.getIntInvalidNullWithRestResponse();
    }

    /**
     * Get integer array value [1, 'integer', 0].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, 'integer', 0].
     */
    public Response<List<Integer>> getIntInvalidStringWithRestResponse() {
        return this.serviceClient.getIntInvalidStringWithRestResponse();
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    public Response<List<Long>> getLongValidWithRestResponse() {
        return this.serviceClient.getLongValidWithRestResponse();
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     * 
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLongValidWithRestResponse(List<Long> arrayBody) {
        return this.serviceClient.putLongValidWithRestResponse(arrayBody);
    }

    /**
     * Get long array value [1, null, 0].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, null, 0].
     */
    public Response<List<Long>> getLongInvalidNullWithRestResponse() {
        return this.serviceClient.getLongInvalidNullWithRestResponse();
    }

    /**
     * Get long array value [1, 'integer', 0].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, 'integer', 0].
     */
    public Response<List<Long>> getLongInvalidStringWithRestResponse() {
        return this.serviceClient.getLongInvalidStringWithRestResponse();
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    public Response<List<Float>> getFloatValidWithRestResponse() {
        return this.serviceClient.getFloatValidWithRestResponse();
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     * 
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putFloatValidWithRestResponse(List<Float> arrayBody) {
        return this.serviceClient.putFloatValidWithRestResponse(arrayBody);
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    public Response<List<Float>> getFloatInvalidNullWithRestResponse() {
        return this.serviceClient.getFloatInvalidNullWithRestResponse();
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    public Response<List<Float>> getFloatInvalidStringWithRestResponse() {
        return this.serviceClient.getFloatInvalidStringWithRestResponse();
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    public Response<List<Double>> getDoubleValidWithRestResponse() {
        return this.serviceClient.getDoubleValidWithRestResponse();
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     * 
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDoubleValidWithRestResponse(List<Double> arrayBody) {
        return this.serviceClient.putDoubleValidWithRestResponse(arrayBody);
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    public Response<List<Double>> getDoubleInvalidNullWithRestResponse() {
        return this.serviceClient.getDoubleInvalidNullWithRestResponse();
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    public Response<List<Double>> getDoubleInvalidStringWithRestResponse() {
        return this.serviceClient.getDoubleInvalidStringWithRestResponse();
    }

    /**
     * Get string array value ['foo1', 'foo2', 'foo3'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo1', 'foo2', 'foo3'].
     */
    public Response<List<String>> getStringValidWithRestResponse() {
        return this.serviceClient.getStringValidWithRestResponse();
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putStringValidWithRestResponse(List<String> arrayBody) {
        return this.serviceClient.putStringValidWithRestResponse(arrayBody);
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    public Response<List<FooEnum>> getEnumValidWithRestResponse() {
        return this.serviceClient.getEnumValidWithRestResponse();
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putEnumValidWithRestResponse(List<FooEnum> arrayBody) {
        return this.serviceClient.putEnumValidWithRestResponse(arrayBody);
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    public Response<List<Enum0>> getStringEnumValidWithRestResponse() {
        return this.serviceClient.getStringEnumValidWithRestResponse();
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putStringEnumValidWithRestResponse(List<Enum1> arrayBody) {
        return this.serviceClient.putStringEnumValidWithRestResponse(arrayBody);
    }

    /**
     * Get string array value ['foo', null, 'foo2'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', null, 'foo2'].
     */
    public Response<List<String>> getStringWithNullWithRestResponse() {
        return this.serviceClient.getStringWithNullWithRestResponse();
    }

    /**
     * Get string array value ['foo', 123, 'foo2'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', 123, 'foo2'].
     */
    public Response<List<String>> getStringWithInvalidWithRestResponse() {
        return this.serviceClient.getStringWithInvalidWithRestResponse();
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     */
    public Response<List<UUID>> getUuidValidWithRestResponse() {
        return this.serviceClient.getUuidValidWithRestResponse();
    }

    /**
     * Set array value  ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * 
     * @param arrayBody The array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUuidValidWithRestResponse(List<UUID> arrayBody) {
        return this.serviceClient.putUuidValidWithRestResponse(arrayBody);
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     */
    public Response<List<UUID>> getUuidInvalidCharsWithRestResponse() {
        return this.serviceClient.getUuidInvalidCharsWithRestResponse();
    }

    /**
     * Get integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     */
    public Response<List<LocalDate>> getDateValidWithRestResponse() {
        return this.serviceClient.getDateValidWithRestResponse();
    }

    /**
     * Set array value  ['2000-12-01', '1980-01-02', '1492-10-12'].
     * 
     * @param arrayBody The array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateValidWithRestResponse(List<LocalDate> arrayBody) {
        return this.serviceClient.putDateValidWithRestResponse(arrayBody);
    }

    /**
     * Get date array value ['2012-01-01', null, '1776-07-04'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2012-01-01', null, '1776-07-04'].
     */
    public Response<List<LocalDate>> getDateInvalidNullWithRestResponse() {
        return this.serviceClient.getDateInvalidNullWithRestResponse();
    }

    /**
     * Get date array value ['2011-03-22', 'date'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2011-03-22', 'date'].
     */
    public Response<List<LocalDate>> getDateInvalidCharsWithRestResponse() {
        return this.serviceClient.getDateInvalidCharsWithRestResponse();
    }

    /**
     * Get date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     */
    public Response<List<OffsetDateTime>> getDateTimeValidWithRestResponse() {
        return this.serviceClient.getDateTimeValidWithRestResponse();
    }

    /**
     * Set array value  ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     * 
     * @param arrayBody The array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateTimeValidWithRestResponse(List<OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeValidWithRestResponse(arrayBody);
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', null].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', null].
     */
    public Response<List<OffsetDateTime>> getDateTimeInvalidNullWithRestResponse() {
        return this.serviceClient.getDateTimeInvalidNullWithRestResponse();
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', 'date-time'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', 'date-time'].
     */
    public Response<List<OffsetDateTime>> getDateTimeInvalidCharsWithRestResponse() {
        return this.serviceClient.getDateTimeInvalidCharsWithRestResponse();
    }

    /**
     * Get date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     */
    public Response<List<DateTimeRfc1123>> getDateTimeRfc1123ValidWithRestResponse() {
        return this.serviceClient.getDateTimeRfc1123ValidWithRestResponse();
    }

    /**
     * Set array value  ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     * 
     * @param arrayBody The array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateTimeRfc1123ValidWithRestResponse(List<DateTimeRfc1123> arrayBody) {
        return this.serviceClient.putDateTimeRfc1123ValidWithRestResponse(arrayBody);
    }

    /**
     * Get duration array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration array value ['P123DT22H14M12.
     */
    public Response<List<Duration>> getDurationValidWithRestResponse() {
        return this.serviceClient.getDurationValidWithRestResponse();
    }

    /**
     * Set array value  ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * 
     * @param arrayBody The array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDurationValidWithRestResponse(List<Duration> arrayBody) {
        return this.serviceClient.putDurationValidWithRestResponse(arrayBody);
    }

    /**
     * Get byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     */
    public Response<List<byte[]>> getByteValidWithRestResponse() {
        return this.serviceClient.getByteValidWithRestResponse();
    }

    /**
     * Put the array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     * 
     * @param arrayBody The array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putByteValidWithRestResponse(List<byte[]> arrayBody) {
        return this.serviceClient.putByteValidWithRestResponse(arrayBody);
    }

    /**
     * Get byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     */
    public Response<List<byte[]>> getByteInvalidNullWithRestResponse() {
        return this.serviceClient.getByteInvalidNullWithRestResponse();
    }

    /**
     * Get array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items base64url encoded.
     */
    public Response<List<Base64Url>> getBase64UrlWithRestResponse() {
        return this.serviceClient.getBase64UrlWithRestResponse();
    }

    /**
     * Get array of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type null value.
     */
    public Response<List<Product>> getComplexNullWithRestResponse() {
        return this.serviceClient.getComplexNullWithRestResponse();
    }

    /**
     * Get empty array of complex type [].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array of complex type [].
     */
    public Response<List<Product>> getComplexEmptyWithRestResponse() {
        return this.serviceClient.getComplexEmptyWithRestResponse();
    }

    /**
     * Get array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     */
    public Response<List<Product>> getComplexItemNullWithRestResponse() {
        return this.serviceClient.getComplexItemNullWithRestResponse();
    }

    /**
     * Get array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     */
    public Response<List<Product>> getComplexItemEmptyWithRestResponse() {
        return this.serviceClient.getComplexItemEmptyWithRestResponse();
    }

    /**
     * Get array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     */
    public Response<List<Product>> getComplexValidWithRestResponse() {
        return this.serviceClient.getComplexValidWithRestResponse();
    }

    /**
     * Put an array of complex type with values [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     * 
     * @param arrayBody array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putComplexValidWithRestResponse(List<Product> arrayBody) {
        return this.serviceClient.putComplexValidWithRestResponse(arrayBody);
    }

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    public Response<List<List<String>>> getArrayNullWithRestResponse() {
        return this.serviceClient.getArrayNullWithRestResponse();
    }

    /**
     * Get an empty array [].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [].
     */
    public Response<List<List<String>>> getArrayEmptyWithRestResponse() {
        return this.serviceClient.getArrayEmptyWithRestResponse();
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     */
    public Response<List<List<String>>> getArrayItemNullWithRestResponse() {
        return this.serviceClient.getArrayItemNullWithRestResponse();
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     */
    public Response<List<List<String>>> getArrayItemEmptyWithRestResponse() {
        return this.serviceClient.getArrayItemEmptyWithRestResponse();
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     */
    public Response<List<List<String>>> getArrayValidWithRestResponse() {
        return this.serviceClient.getArrayValidWithRestResponse();
    }

    /**
     * Put An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * 
     * @param arrayBody An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putArrayValidWithRestResponse(List<List<String>> arrayBody) {
        return this.serviceClient.putArrayValidWithRestResponse(arrayBody);
    }

    /**
     * Get an array of Dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries with value null.
     */
    public Response<List<Map<String, String>>> getDictionaryNullWithRestResponse() {
        return this.serviceClient.getDictionaryNullWithRestResponse();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [].
     */
    public Response<List<Map<String, String>>> getDictionaryEmptyWithRestResponse() {
        return this.serviceClient.getDictionaryEmptyWithRestResponse();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    public Response<List<Map<String, String>>> getDictionaryItemNullWithRestResponse() {
        return this.serviceClient.getDictionaryItemNullWithRestResponse();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    public Response<List<Map<String, String>>> getDictionaryItemEmptyWithRestResponse() {
        return this.serviceClient.getDictionaryItemEmptyWithRestResponse();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    public Response<List<Map<String, String>>> getDictionaryValidWithRestResponse() {
        return this.serviceClient.getDictionaryValidWithRestResponse();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @param arrayBody An array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    public Response<Void> putDictionaryValidWithRestResponse(List<Map<String, String>> arrayBody) {
        return this.serviceClient.putDictionaryValidWithRestResponse(arrayBody);
    }

    /**
     * A builder for creating a new instance of the AutoRestSwaggerBATArrayServiceClient type.
     */
    public static final class Builder {
        /*
         * server parameter
         */
        private String host;

        /**
         * Sets server parameter.
         * 
         * @param host the host value.
         * @return the Builder.
         */
        public Builder host(String host) {
            this.host = host;
            return this;
        }

        /*
         * The Azure Core generic ServiceClient Builder.
         */
        private ServiceClient.Builder serviceClientBuilder;

        /**
         * Sets The Azure Core generic ServiceClient Builder.
         * 
         * @param serviceClientBuilder the serviceClientBuilder value.
         * @return the Builder.
         */
        public Builder serviceClientBuilder(ServiceClient.Builder serviceClientBuilder) {
            this.serviceClientBuilder = serviceClientBuilder;
            return this;
        }

        /*
         * The Interceptor to set intercept request and set credentials.
         */
        private Interceptor credentialInterceptor;

        /**
         * Sets The Interceptor to set intercept request and set credentials.
         * 
         * @param credentialInterceptor the credentialInterceptor value.
         * @return the Builder.
         */
        public Builder credentialInterceptor(Interceptor credentialInterceptor) {
            this.credentialInterceptor = credentialInterceptor;
            return this;
        }

        /**
         * Builds an instance of AutoRestSwaggerBATArrayServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestSwaggerBATArrayServiceClient.
         */
        public AutoRestSwaggerBATArrayServiceClient build() {
            if (host == null) {
                this.host = "http://localhost:3000";
            }
            if (serviceClientBuilder == null) {
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            serviceClientBuilder.setBaseUrl(host);
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestSwaggerBATArrayServiceImpl internalClient = new AutoRestSwaggerBATArrayServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestSwaggerBATArrayServiceClient(internalClient.getArrays());
        }
    }
}
