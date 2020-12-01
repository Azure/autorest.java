package com.azure.androidtest.fixtures.bodydictionary;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.androidtest.fixtures.bodydictionary.implementation.AutoRestSwaggerBATDictionaryServiceImpl;
import com.azure.androidtest.fixtures.bodydictionary.implementation.DictionarysImpl;
import com.azure.androidtest.fixtures.bodydictionary.models.ErrorException;
import com.azure.androidtest.fixtures.bodydictionary.models.Widget;
import java.util.List;
import java.util.Map;
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
 * Initializes a new instance of the asynchronous AutoRestSwaggerBATDictionaryService type.
 */
public final class AutoRestSwaggerBATDictionaryServiceAsyncClient {
    private DictionarysImpl serviceClient;

    /**
     * Initializes an instance of Dictionarys client.
     */
    AutoRestSwaggerBATDictionaryServiceAsyncClient(DictionarysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null dictionary value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get empty dictionary value {}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmpty(final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getEmpty(callback);
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody The empty dictionary value {}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEmpty(Map<String, String> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putEmpty(arrayBody, callback);
    }

    /**
     * Get Dictionary with null value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNullValue(final Callback<Map<String, String>> callback) {
        this.serviceClient.getNullValue(callback);
    }

    /**
     * Get Dictionary with null key.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNullKey(final Callback<Map<String, String>> callback) {
        this.serviceClient.getNullKey(callback);
    }

    /**
     * Get Dictionary with key as empty string.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmptyStringKey(final Callback<Map<String, String>> callback) {
        this.serviceClient.getEmptyStringKey(callback);
    }

    /**
     * Get invalid Dictionary value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalid(final Callback<Map<String, String>> callback) {
        this.serviceClient.getInvalid(callback);
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanTfft(final Callback<Map<String, Boolean>> callback) {
        this.serviceClient.getBooleanTfft(callback);
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBooleanTfft(Map<String, Boolean> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putBooleanTfft(arrayBody, callback);
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanInvalidNull(final Callback<Map<String, Boolean>> callback) {
        this.serviceClient.getBooleanInvalidNull(callback);
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanInvalidString(final Callback<Map<String, Boolean>> callback) {
        this.serviceClient.getBooleanInvalidString(callback);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntegerValid(final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getIntegerValid(callback);
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putIntegerValid(Map<String, Integer> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putIntegerValid(arrayBody, callback);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntInvalidNull(final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getIntInvalidNull(callback);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntInvalidString(final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getIntInvalidString(callback);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongValid(final Callback<Map<String, Long>> callback) {
        this.serviceClient.getLongValid(callback);
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLongValid(Map<String, Long> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putLongValid(arrayBody, callback);
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongInvalidNull(final Callback<Map<String, Long>> callback) {
        this.serviceClient.getLongInvalidNull(callback);
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongInvalidString(final Callback<Map<String, Long>> callback) {
        this.serviceClient.getLongInvalidString(callback);
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloatValid(final Callback<Map<String, Float>> callback) {
        this.serviceClient.getFloatValid(callback);
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putFloatValid(Map<String, Float> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putFloatValid(arrayBody, callback);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloatInvalidNull(final Callback<Map<String, Float>> callback) {
        this.serviceClient.getFloatInvalidNull(callback);
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloatInvalidString(final Callback<Map<String, Float>> callback) {
        this.serviceClient.getFloatInvalidString(callback);
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDoubleValid(final Callback<Map<String, Double>> callback) {
        this.serviceClient.getDoubleValid(callback);
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDoubleValid(Map<String, Double> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDoubleValid(arrayBody, callback);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDoubleInvalidNull(final Callback<Map<String, Double>> callback) {
        this.serviceClient.getDoubleInvalidNull(callback);
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDoubleInvalidString(final Callback<Map<String, Double>> callback) {
        this.serviceClient.getDoubleInvalidString(callback);
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringValid(final Callback<Map<String, String>> callback) {
        this.serviceClient.getStringValid(callback);
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody The dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putStringValid(Map<String, String> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putStringValid(arrayBody, callback);
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringWithNull(final Callback<Map<String, String>> callback) {
        this.serviceClient.getStringWithNull(callback);
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getStringWithInvalid(final Callback<Map<String, String>> callback) {
        this.serviceClient.getStringWithInvalid(callback);
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateValid(final Callback<Map<String, LocalDate>> callback) {
        this.serviceClient.getDateValid(callback);
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateValid(Map<String, LocalDate> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDateValid(arrayBody, callback);
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateInvalidNull(final Callback<Map<String, LocalDate>> callback) {
        this.serviceClient.getDateInvalidNull(callback);
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateInvalidChars(final Callback<Map<String, LocalDate>> callback) {
        this.serviceClient.getDateInvalidChars(callback);
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeValid(final Callback<Map<String, OffsetDateTime>> callback) {
        this.serviceClient.getDateTimeValid(callback);
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateTimeValid(Map<String, OffsetDateTime> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDateTimeValid(arrayBody, callback);
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeInvalidNull(final Callback<Map<String, OffsetDateTime>> callback) {
        this.serviceClient.getDateTimeInvalidNull(callback);
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeInvalidChars(final Callback<Map<String, OffsetDateTime>> callback) {
        this.serviceClient.getDateTimeInvalidChars(callback);
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeRfc1123Valid(final Callback<Map<String, DateTimeRfc1123>> callback) {
        this.serviceClient.getDateTimeRfc1123Valid(callback);
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateTimeRfc1123Valid(Map<String, DateTimeRfc1123> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDateTimeRfc1123Valid(arrayBody, callback);
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDurationValid(final Callback<Map<String, Duration>> callback) {
        this.serviceClient.getDurationValid(callback);
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDurationValid(Map<String, Duration> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDurationValid(arrayBody, callback);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getByteValid(final Callback<Map<String, byte[]>> callback) {
        this.serviceClient.getByteValid(callback);
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putByteValid(Map<String, byte[]> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putByteValid(arrayBody, callback);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getByteInvalidNull(final Callback<Map<String, byte[]>> callback) {
        this.serviceClient.getByteInvalidNull(callback);
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBase64Url(final Callback<Map<String, Base64Url>> callback) {
        this.serviceClient.getBase64Url(callback);
    }

    /**
     * Get dictionary of complex type null value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexNull(final Callback<Map<String, Widget>> callback) {
        this.serviceClient.getComplexNull(callback);
    }

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexEmpty(final Callback<Map<String, Widget>> callback) {
        this.serviceClient.getComplexEmpty(callback);
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexItemNull(final Callback<Map<String, Widget>> callback) {
        this.serviceClient.getComplexItemNull(callback);
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexItemEmpty(final Callback<Map<String, Widget>> callback) {
        this.serviceClient.getComplexItemEmpty(callback);
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplexValid(final Callback<Map<String, Widget>> callback) {
        this.serviceClient.getComplexValid(callback);
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putComplexValid(Map<String, Widget> arrayBody, final Callback<Void> callback) {
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
    public void getArrayNull(final Callback<Map<String, List<String>>> callback) {
        this.serviceClient.getArrayNull(callback);
    }

    /**
     * Get an empty dictionary {}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayEmpty(final Callback<Map<String, List<String>>> callback) {
        this.serviceClient.getArrayEmpty(callback);
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayItemNull(final Callback<Map<String, List<String>>> callback) {
        this.serviceClient.getArrayItemNull(callback);
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayItemEmpty(final Callback<Map<String, List<String>>> callback) {
        this.serviceClient.getArrayItemEmpty(callback);
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getArrayValid(final Callback<Map<String, List<String>>> callback) {
        this.serviceClient.getArrayValid(callback);
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putArrayValid(Map<String, List<String>> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putArrayValid(arrayBody, callback);
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryNull(final Callback<Map<String, Object>> callback) {
        this.serviceClient.getDictionaryNull(callback);
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryEmpty(final Callback<Map<String, Object>> callback) {
        this.serviceClient.getDictionaryEmpty(callback);
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryItemNull(final Callback<Map<String, Object>> callback) {
        this.serviceClient.getDictionaryItemNull(callback);
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryItemEmpty(final Callback<Map<String, Object>> callback) {
        this.serviceClient.getDictionaryItemEmpty(callback);
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDictionaryValid(final Callback<Map<String, Object>> callback) {
        this.serviceClient.getDictionaryValid(callback);
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDictionaryValid(Map<String, Object> arrayBody, final Callback<Void> callback) {
        this.serviceClient.putDictionaryValid(arrayBody, callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestSwaggerBATDictionaryServiceAsyncClient type.
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
         * Builds an instance of AutoRestSwaggerBATDictionaryServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestSwaggerBATDictionaryServiceAsyncClient.
         */
        public AutoRestSwaggerBATDictionaryServiceAsyncClient build() {
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
            AutoRestSwaggerBATDictionaryServiceImpl internalClient = new AutoRestSwaggerBATDictionaryServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestSwaggerBATDictionaryServiceAsyncClient(internalClient.getDictionarys());
        }
    }
}
