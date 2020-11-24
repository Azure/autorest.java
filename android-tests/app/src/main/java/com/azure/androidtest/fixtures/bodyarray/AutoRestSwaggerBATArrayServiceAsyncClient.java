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
 * Initializes a new instance of the asynchronous AutoRestSwaggerBATArrayService type.
 */
public final class AutoRestSwaggerBATArrayServiceAsyncClient {
    private ArraysImpl serviceClient;

    /**
     * Initializes an instance of Arrays client.
     */
    AutoRestSwaggerBATArrayServiceAsyncClient(ArraysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null array value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<List<Integer>> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid array [1, 2, 3.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalid(final Callback<List<Integer>> callback) {
        this.serviceClient.getInvalid(callback);
    }

    /**
     * Get empty array value [].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmpty(final Callback<List<Integer>> callback) {
        this.serviceClient.getEmpty(callback);
    }

    /**
     * Set array value empty [].
     * 
     * @param arrayBody The empty array value [].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEmpty(List<String> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putEmpty(arrayBody, callback);
    }

    /**
     * Get boolean array value [true, false, false, true].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanTfft(final Callback<List<Boolean>> callback) {
        this.serviceClient.getBooleanTfft(callback);
    }

    /**
     * Set array value empty [true, false, false, true].
     * 
     * @param arrayBody The array value [true, false, false, true].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBooleanTfft(List<Boolean> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putBooleanTfft(arrayBody, callback);
    }

    /**
     * Get boolean array value [true, null, false].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanInvalidNull(final Callback<List<Boolean>> callback) {
        this.serviceClient.getBooleanInvalidNull(callback);
    }

    /**
     * Get boolean array value [true, 'boolean', false].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanInvalidString(final Callback<List<Boolean>> callback) {
        this.serviceClient.getBooleanInvalidString(callback);
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntegerValid(final Callback<List<Integer>> callback) {
        this.serviceClient.getIntegerValid(callback);
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     * 
     * @param arrayBody The array value [1, -1, 3, 300].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putIntegerValid(List<Integer> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putIntegerValid(arrayBody, callback);
    }

    /**
     * Get integer array value [1, null, 0].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntInvalidNull(final Callback<List<Integer>> callback) {
        this.serviceClient.getIntInvalidNull(callback);
    }

    /**
     * Get integer array value [1, 'integer', 0].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntInvalidString(final Callback<List<Integer>> callback) {
        this.serviceClient.getIntInvalidString(callback);
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongValid(final Callback<List<Long>> callback) {
        this.serviceClient.getLongValid(callback);
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     * 
     * @param arrayBody The array value [1, -1, 3, 300].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLongValid(List<Long> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putLongValid(arrayBody, callback);
    }

    /**
     * Get long array value [1, null, 0].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongInvalidNull(final Callback<List<Long>> callback) {
        this.serviceClient.getLongInvalidNull(callback);
    }

    /**
     * Get long array value [1, 'integer', 0].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongInvalidString(final Callback<List<Long>> callback) {
        this.serviceClient.getLongInvalidString(callback);
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloatValid(final Callback<List<Float>> callback) {
        this.serviceClient.getFloatValid(callback);
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     * 
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putFloatValid(List<Float> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putFloatValid(arrayBody, callback);
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloatInvalidNull(final Callback<List<Float>> callback) {
        this.serviceClient.getFloatInvalidNull(callback);
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloatInvalidString(final Callback<List<Float>> callback) {
        this.serviceClient.getFloatInvalidString(callback);
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDoubleValid(final Callback<List<Double>> callback) {
        this.serviceClient.getDoubleValid(callback);
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     * 
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDoubleValid(List<Double> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDoubleValid(arrayBody, callback);
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDoubleInvalidNull(final Callback<List<Double>> callback) {
        this.serviceClient.getDoubleInvalidNull(callback);
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDoubleInvalidString(final Callback<List<Double>> callback) {
        this.serviceClient.getDoubleInvalidString(callback);
    }

    /**
     * Get string array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringValid(final Callback<List<String>> callback) {
        this.serviceClient.getStringValid(callback);
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putStringValid(List<String> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putStringValid(arrayBody, callback);
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEnumValid(final Callback<List<FooEnum>> callback) {
        this.serviceClient.getEnumValid(callback);
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEnumValid(List<FooEnum> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putEnumValid(arrayBody, callback);
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringEnumValid(final Callback<List<Enum0>> callback) {
        this.serviceClient.getStringEnumValid(callback);
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     * 
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putStringEnumValid(List<Enum1> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putStringEnumValid(arrayBody, callback);
    }

    /**
     * Get string array value ['foo', null, 'foo2'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringWithNull(final Callback<List<String>> callback) {
        this.serviceClient.getStringWithNull(callback);
    }

    /**
     * Get string array value ['foo', 123, 'foo2'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringWithInvalid(final Callback<List<String>> callback) {
        this.serviceClient.getStringWithInvalid(callback);
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUuidValid(final Callback<List<UUID>> callback) {
        this.serviceClient.getUuidValid(callback);
    }

    /**
     * Set array value  ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * 
     * @param arrayBody The array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db', 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUuidValid(List<UUID> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putUuidValid(arrayBody, callback);
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUuidInvalidChars(final Callback<List<UUID>> callback) {
        this.serviceClient.getUuidInvalidChars(callback);
    }

    /**
     * Get integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateValid(final Callback<List<LocalDate>> callback) {
        this.serviceClient.getDateValid(callback);
    }

    /**
     * Set array value  ['2000-12-01', '1980-01-02', '1492-10-12'].
     * 
     * @param arrayBody The array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateValid(List<LocalDate> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDateValid(arrayBody, callback);
    }

    /**
     * Get date array value ['2012-01-01', null, '1776-07-04'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateInvalidNull(final Callback<List<LocalDate>> callback) {
        this.serviceClient.getDateInvalidNull(callback);
    }

    /**
     * Get date array value ['2011-03-22', 'date'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateInvalidChars(final Callback<List<LocalDate>> callback) {
        this.serviceClient.getDateInvalidChars(callback);
    }

    /**
     * Get date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeValid(final Callback<List<OffsetDateTime>> callback) {
        this.serviceClient.getDateTimeValid(callback);
    }

    /**
     * Set array value  ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     * 
     * @param arrayBody The array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateTimeValid(List<OffsetDateTime> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDateTimeValid(arrayBody, callback);
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', null].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeInvalidNull(final Callback<List<OffsetDateTime>> callback) {
        this.serviceClient.getDateTimeInvalidNull(callback);
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', 'date-time'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeInvalidChars(final Callback<List<OffsetDateTime>> callback) {
        this.serviceClient.getDateTimeInvalidChars(callback);
    }

    /**
     * Get date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeRfc1123Valid(final Callback<List<DateTimeRfc1123>> callback) {
        this.serviceClient.getDateTimeRfc1123Valid(callback);
    }

    /**
     * Set array value  ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     * 
     * @param arrayBody The array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01 GMT'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateTimeRfc1123Valid(List<DateTimeRfc1123> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDateTimeRfc1123Valid(arrayBody, callback);
    }

    /**
     * Get duration array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDurationValid(final Callback<List<Duration>> callback) {
        this.serviceClient.getDurationValid(callback);
    }

    /**
     * Set array value  ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * 
     * @param arrayBody The array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDurationValid(List<Duration> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDurationValid(arrayBody, callback);
    }

    /**
     * Get byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getByteValid(final Callback<List<byte[]>> callback) {
        this.serviceClient.getByteValid(callback);
    }

    /**
     * Put the array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     * 
     * @param arrayBody The array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putByteValid(List<byte[]> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putByteValid(arrayBody, callback);
    }

    /**
     * Get byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getByteInvalidNull(final Callback<List<byte[]>> callback) {
        this.serviceClient.getByteInvalidNull(callback);
    }

    /**
     * Get array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items base64url encoded.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBase64Url(final Callback<List<Base64Url>> callback) {
        this.serviceClient.getBase64Url(callback);
    }

    /**
     * Get array of complex type null value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexNull(final Callback<List<Product>> callback) {
        this.serviceClient.getComplexNull(callback);
    }

    /**
     * Get empty array of complex type [].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexEmpty(final Callback<List<Product>> callback) {
        this.serviceClient.getComplexEmpty(callback);
    }

    /**
     * Get array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexItemNull(final Callback<List<Product>> callback) {
        this.serviceClient.getComplexItemNull(callback);
    }

    /**
     * Get array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexItemEmpty(final Callback<List<Product>> callback) {
        this.serviceClient.getComplexItemEmpty(callback);
    }

    /**
     * Get array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexValid(final Callback<List<Product>> callback) {
        this.serviceClient.getComplexValid(callback);
    }

    /**
     * Put an array of complex type with values [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     * 
     * @param arrayBody array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5, 'string': '6'}].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putComplexValid(List<Product> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putComplexValid(arrayBody, callback);
    }

    /**
     * Get a null array.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayNull(final Callback<List<List<String>>> callback) {
        this.serviceClient.getArrayNull(callback);
    }

    /**
     * Get an empty array [].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayEmpty(final Callback<List<List<String>>> callback) {
        this.serviceClient.getArrayEmpty(callback);
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayItemNull(final Callback<List<List<String>>> callback) {
        this.serviceClient.getArrayItemNull(callback);
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayItemEmpty(final Callback<List<List<String>>> callback) {
        this.serviceClient.getArrayItemEmpty(callback);
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayValid(final Callback<List<List<String>>> callback) {
        this.serviceClient.getArrayValid(callback);
    }

    /**
     * Put An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * 
     * @param arrayBody An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putArrayValid(List<List<String>> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putArrayValid(arrayBody, callback);
    }

    /**
     * Get an array of Dictionaries with value null.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryNull(final Callback<List<Map<String, String>>> callback) {
        this.serviceClient.getDictionaryNull(callback);
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryEmpty(final Callback<List<Map<String, String>>> callback) {
        this.serviceClient.getDictionaryEmpty(callback);
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryItemNull(final Callback<List<Map<String, String>>> callback) {
        this.serviceClient.getDictionaryItemNull(callback);
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryItemEmpty(final Callback<List<Map<String, String>>> callback) {
        this.serviceClient.getDictionaryItemEmpty(callback);
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryValid(final Callback<List<Map<String, String>>> callback) {
        this.serviceClient.getDictionaryValid(callback);
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * 
     * @param arrayBody An array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDictionaryValid(List<Map<String, String>> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDictionaryValid(arrayBody, callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestSwaggerBATArrayServiceAsyncClient type.
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
         * Builds an instance of AutoRestSwaggerBATArrayServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestSwaggerBATArrayServiceAsyncClient.
         */
        public AutoRestSwaggerBATArrayServiceAsyncClient build() {
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
            return new AutoRestSwaggerBATArrayServiceAsyncClient(internalClient.getArrays());
        }
    }
}
