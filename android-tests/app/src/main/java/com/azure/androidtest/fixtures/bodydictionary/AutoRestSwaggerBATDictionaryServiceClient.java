package com.azure.androidtest.fixtures.bodydictionary;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.android.core.util.paging.PagedDataRetriever;
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
 * Initializes a new instance of the synchronous AutoRestSwaggerBATDictionaryService type.
 */
public final class AutoRestSwaggerBATDictionaryServiceClient {
    private DictionarysImpl serviceClient;

    /**
     * Initializes an instance of Dictionarys client.
     */
    AutoRestSwaggerBATDictionaryServiceClient(DictionarysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null dictionary value.
     */
    public Response<Map<String, Integer>> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get empty dictionary value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary value {}.
     */
    public Response<Map<String, Integer>> getEmptyWithRestResponse() {
        return this.serviceClient.getEmptyWithRestResponse();
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody The empty dictionary value {}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putEmptyWithRestResponse(Map<String, String> arrayBody) {
        return this.serviceClient.putEmptyWithRestResponse(arrayBody);
    }

    /**
     * Get Dictionary with null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null value.
     */
    public Response<Map<String, String>> getNullValueWithRestResponse() {
        return this.serviceClient.getNullValueWithRestResponse();
    }

    /**
     * Get Dictionary with null key.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null key.
     */
    public Response<Map<String, String>> getNullKeyWithRestResponse() {
        return this.serviceClient.getNullKeyWithRestResponse();
    }

    /**
     * Get Dictionary with key as empty string.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with key as empty string.
     */
    public Response<Map<String, String>> getEmptyStringKeyWithRestResponse() {
        return this.serviceClient.getEmptyStringKeyWithRestResponse();
    }

    /**
     * Get invalid Dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Dictionary value.
     */
    public Response<Map<String, String>> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     */
    public Response<Map<String, Boolean>> getBooleanTfftWithRestResponse() {
        return this.serviceClient.getBooleanTfftWithRestResponse();
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBooleanTfftWithRestResponse(Map<String, Boolean> arrayBody) {
        return this.serviceClient.putBooleanTfftWithRestResponse(arrayBody);
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": null, "2": false }.
     */
    public Response<Map<String, Boolean>> getBooleanInvalidNullWithRestResponse() {
        return this.serviceClient.getBooleanInvalidNullWithRestResponse();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     */
    public Response<Map<String, Boolean>> getBooleanInvalidStringWithRestResponse() {
        return this.serviceClient.getBooleanInvalidStringWithRestResponse();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    public Response<Map<String, Integer>> getIntegerValidWithRestResponse() {
        return this.serviceClient.getIntegerValidWithRestResponse();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putIntegerValidWithRestResponse(Map<String, Integer> arrayBody) {
        return this.serviceClient.putIntegerValidWithRestResponse(arrayBody);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": null, "2": 0}.
     */
    public Response<Map<String, Integer>> getIntInvalidNullWithRestResponse() {
        return this.serviceClient.getIntInvalidNullWithRestResponse();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    public Response<Map<String, Integer>> getIntInvalidStringWithRestResponse() {
        return this.serviceClient.getIntInvalidStringWithRestResponse();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    public Response<Map<String, Long>> getLongValidWithRestResponse() {
        return this.serviceClient.getLongValidWithRestResponse();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLongValidWithRestResponse(Map<String, Long> arrayBody) {
        return this.serviceClient.putLongValidWithRestResponse(arrayBody);
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": null, "2": 0}.
     */
    public Response<Map<String, Long>> getLongInvalidNullWithRestResponse() {
        return this.serviceClient.getLongInvalidNullWithRestResponse();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    public Response<Map<String, Long>> getLongInvalidStringWithRestResponse() {
        return this.serviceClient.getLongInvalidStringWithRestResponse();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.
     */
    public Response<Map<String, Float>> getFloatValidWithRestResponse() {
        return this.serviceClient.getFloatValidWithRestResponse();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putFloatValidWithRestResponse(Map<String, Float> arrayBody) {
        return this.serviceClient.putFloatValidWithRestResponse(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.
     */
    public Response<Map<String, Float>> getFloatInvalidNullWithRestResponse() {
        return this.serviceClient.getFloatInvalidNullWithRestResponse();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.
     */
    public Response<Map<String, Float>> getFloatInvalidStringWithRestResponse() {
        return this.serviceClient.getFloatInvalidStringWithRestResponse();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.
     */
    public Response<Map<String, Double>> getDoubleValidWithRestResponse() {
        return this.serviceClient.getDoubleValidWithRestResponse();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDoubleValidWithRestResponse(Map<String, Double> arrayBody) {
        return this.serviceClient.putDoubleValidWithRestResponse(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.
     */
    public Response<Map<String, Double>> getDoubleInvalidNullWithRestResponse() {
        return this.serviceClient.getDoubleInvalidNullWithRestResponse();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.
     */
    public Response<Map<String, Double>> getDoubleInvalidStringWithRestResponse() {
        return this.serviceClient.getDoubleInvalidStringWithRestResponse();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     */
    public Response<Map<String, String>> getStringValidWithRestResponse() {
        return this.serviceClient.getStringValidWithRestResponse();
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody The dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putStringValidWithRestResponse(Map<String, String> arrayBody) {
        return this.serviceClient.putStringValidWithRestResponse(arrayBody);
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     */
    public Response<Map<String, String>> getStringWithNullWithRestResponse() {
        return this.serviceClient.getStringWithNullWithRestResponse();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     */
    public Response<Map<String, String>> getStringWithInvalidWithRestResponse() {
        return this.serviceClient.getStringWithInvalidWithRestResponse();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     */
    public Response<Map<String, LocalDate>> getDateValidWithRestResponse() {
        return this.serviceClient.getDateValidWithRestResponse();
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateValidWithRestResponse(Map<String, LocalDate> arrayBody) {
        return this.serviceClient.putDateValidWithRestResponse(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     */
    public Response<Map<String, LocalDate>> getDateInvalidNullWithRestResponse() {
        return this.serviceClient.getDateInvalidNullWithRestResponse();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2011-03-22", "1": "date"}.
     */
    public Response<Map<String, LocalDate>> getDateInvalidCharsWithRestResponse() {
        return this.serviceClient.getDateInvalidCharsWithRestResponse();
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     */
    public Response<Map<String, OffsetDateTime>> getDateTimeValidWithRestResponse() {
        return this.serviceClient.getDateTimeValidWithRestResponse();
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateTimeValidWithRestResponse(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeValidWithRestResponse(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     */
    public Response<Map<String, OffsetDateTime>> getDateTimeInvalidNullWithRestResponse() {
        return this.serviceClient.getDateTimeInvalidNullWithRestResponse();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     */
    public Response<Map<String, OffsetDateTime>> getDateTimeInvalidCharsWithRestResponse() {
        return this.serviceClient.getDateTimeInvalidCharsWithRestResponse();
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     */
    public Response<Map<String, DateTimeRfc1123>> getDateTimeRfc1123ValidWithRestResponse() {
        return this.serviceClient.getDateTimeRfc1123ValidWithRestResponse();
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateTimeRfc1123ValidWithRestResponse(Map<String, DateTimeRfc1123> arrayBody) {
        return this.serviceClient.putDateTimeRfc1123ValidWithRestResponse(arrayBody);
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration dictionary value {"0": "P123DT22H14M12.
     */
    public Response<Map<String, Duration>> getDurationValidWithRestResponse() {
        return this.serviceClient.getDurationValidWithRestResponse();
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDurationValidWithRestResponse(Map<String, Duration> arrayBody) {
        return this.serviceClient.putDurationValidWithRestResponse(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     */
    public Response<Map<String, byte[]>> getByteValidWithRestResponse() {
        return this.serviceClient.getByteValidWithRestResponse();
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putByteValidWithRestResponse(Map<String, byte[]> arrayBody) {
        return this.serviceClient.putByteValidWithRestResponse(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     */
    public Response<Map<String, byte[]>> getByteInvalidNullWithRestResponse() {
        return this.serviceClient.getByteInvalidNullWithRestResponse();
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     */
    public Response<Map<String, Base64Url>> getBase64UrlWithRestResponse() {
        return this.serviceClient.getBase64UrlWithRestResponse();
    }

    /**
     * Get dictionary of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type null value.
     */
    public Response<Map<String, Widget>> getComplexNullWithRestResponse() {
        return this.serviceClient.getComplexNullWithRestResponse();
    }

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary of complex type {}.
     */
    public Response<Map<String, Widget>> getComplexEmptyWithRestResponse() {
        return this.serviceClient.getComplexEmptyWithRestResponse();
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     */
    public Response<Map<String, Widget>> getComplexItemNullWithRestResponse() {
        return this.serviceClient.getComplexItemNullWithRestResponse();
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     */
    public Response<Map<String, Widget>> getComplexItemEmptyWithRestResponse() {
        return this.serviceClient.getComplexItemEmptyWithRestResponse();
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     */
    public Response<Map<String, Widget>> getComplexValidWithRestResponse() {
        return this.serviceClient.getComplexValidWithRestResponse();
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putComplexValidWithRestResponse(Map<String, Widget> arrayBody) {
        return this.serviceClient.putComplexValidWithRestResponse(arrayBody);
    }

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    public Response<Map<String, List<String>>> getArrayNullWithRestResponse() {
        return this.serviceClient.getArrayNullWithRestResponse();
    }

    /**
     * Get an empty dictionary {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty dictionary {}.
     */
    public Response<Map<String, List<String>>> getArrayEmptyWithRestResponse() {
        return this.serviceClient.getArrayEmptyWithRestResponse();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     */
    public Response<Map<String, List<String>>> getArrayItemNullWithRestResponse() {
        return this.serviceClient.getArrayItemNullWithRestResponse();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     */
    public Response<Map<String, List<String>>> getArrayItemEmptyWithRestResponse() {
        return this.serviceClient.getArrayItemEmptyWithRestResponse();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     */
    public Response<Map<String, List<String>>> getArrayValidWithRestResponse() {
        return this.serviceClient.getArrayValidWithRestResponse();
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putArrayValidWithRestResponse(Map<String, List<String>> arrayBody) {
        return this.serviceClient.putArrayValidWithRestResponse(arrayBody);
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries with value null.
     */
    public Response<Map<String, Object>> getDictionaryNullWithRestResponse() {
        return this.serviceClient.getDictionaryNullWithRestResponse();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     */
    public Response<Map<String, Object>> getDictionaryEmptyWithRestResponse() {
        return this.serviceClient.getDictionaryEmptyWithRestResponse();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    public Response<Map<String, Object>> getDictionaryItemNullWithRestResponse() {
        return this.serviceClient.getDictionaryItemNullWithRestResponse();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    public Response<Map<String, Object>> getDictionaryItemEmptyWithRestResponse() {
        return this.serviceClient.getDictionaryItemEmptyWithRestResponse();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    public Response<Map<String, Object>> getDictionaryValidWithRestResponse() {
        return this.serviceClient.getDictionaryValidWithRestResponse();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    public Response<Void> putDictionaryValidWithRestResponse(Map<String, Object> arrayBody) {
        return this.serviceClient.putDictionaryValidWithRestResponse(arrayBody);
    }

    /**
     * A builder for creating a new instance of the AutoRestSwaggerBATDictionaryServiceClient type.
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
         * Builds an instance of AutoRestSwaggerBATDictionaryServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestSwaggerBATDictionaryServiceClient.
         */
        public AutoRestSwaggerBATDictionaryServiceClient build() {
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
            return new AutoRestSwaggerBATDictionaryServiceClient(internalClient.getDictionarys());
        }
    }
}
