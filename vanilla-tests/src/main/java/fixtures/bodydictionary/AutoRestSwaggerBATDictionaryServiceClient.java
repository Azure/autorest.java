package fixtures.bodydictionary;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import fixtures.bodydictionary.implementation.DictionariesImpl;
import fixtures.bodydictionary.models.ErrorException;
import fixtures.bodydictionary.models.Widget;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/** Initializes a new instance of the synchronous AutoRestSwaggerBATDictionaryService type. */
@Generated
@ServiceClient(builder = AutoRestSwaggerBATDictionaryServiceBuilder.class)
public final class AutoRestSwaggerBATDictionaryServiceClient {
    @Generated private final DictionariesImpl serviceClient;

    /**
     * Initializes an instance of Dictionaries client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AutoRestSwaggerBATDictionaryServiceClient(DictionariesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null dictionary value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null dictionary value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getNull() {
        return this.serviceClient.getNull();
    }

    /**
     * Get empty dictionary value {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary value {}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getEmpty() {
        return this.serviceClient.getEmpty();
    }

    /**
     * Set dictionary value empty {}.
     *
     * @param arrayBody The empty dictionary value {}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(Map<String, String> arrayBody) {
        this.serviceClient.putEmpty(arrayBody);
    }

    /**
     * Get Dictionary with null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getNullValue() {
        return this.serviceClient.getNullValue();
    }

    /**
     * Get Dictionary with null key.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null key.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getNullKey() {
        return this.serviceClient.getNullKey();
    }

    /**
     * Get Dictionary with key as empty string.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with key as empty string.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getEmptyStringKey() {
        return this.serviceClient.getEmptyStringKey();
    }

    /**
     * Get invalid Dictionary value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Dictionary value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getInvalid() {
        return this.serviceClient.getInvalid();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanTfft() {
        return this.serviceClient.getBooleanTfft();
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     *
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBooleanTfft(Map<String, Boolean> arrayBody) {
        this.serviceClient.putBooleanTfft(arrayBody);
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": null, "2": false }.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanInvalidNull() {
        return this.serviceClient.getBooleanInvalidNull();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanInvalidString() {
        return this.serviceClient.getBooleanInvalidString();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntegerValid() {
        return this.serviceClient.getIntegerValid();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putIntegerValid(Map<String, Integer> arrayBody) {
        this.serviceClient.putIntegerValid(arrayBody);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": null, "2": 0}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntInvalidNull() {
        return this.serviceClient.getIntInvalidNull();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntInvalidString() {
        return this.serviceClient.getIntInvalidString();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongValid() {
        return this.serviceClient.getLongValid();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLongValid(Map<String, Long> arrayBody) {
        this.serviceClient.putLongValid(arrayBody);
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": null, "2": 0}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongInvalidNull() {
        return this.serviceClient.getLongInvalidNull();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongInvalidString() {
        return this.serviceClient.getLongInvalidString();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatValid() {
        return this.serviceClient.getFloatValid();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloatValid(Map<String, Float> arrayBody) {
        this.serviceClient.putFloatValid(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatInvalidNull() {
        return this.serviceClient.getFloatInvalidNull();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatInvalidString() {
        return this.serviceClient.getFloatInvalidString();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleValid() {
        return this.serviceClient.getDoubleValid();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDoubleValid(Map<String, Double> arrayBody) {
        this.serviceClient.putDoubleValid(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleInvalidNull() {
        return this.serviceClient.getDoubleInvalidNull();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleInvalidString() {
        return this.serviceClient.getDoubleInvalidString();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringValid() {
        return this.serviceClient.getStringValid();
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     *
     * @param arrayBody The dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putStringValid(Map<String, String> arrayBody) {
        this.serviceClient.putStringValid(arrayBody);
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringWithNull() {
        return this.serviceClient.getStringWithNull();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringWithInvalid() {
        return this.serviceClient.getStringWithInvalid();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateValid() {
        return this.serviceClient.getDateValid();
    }

    /**
     * Set dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     *
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateValid(Map<String, LocalDate> arrayBody) {
        this.serviceClient.putDateValid(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateInvalidNull() {
        return this.serviceClient.getDateInvalidNull();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2011-03-22", "1": "date"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateInvalidChars() {
        return this.serviceClient.getDateInvalidChars();
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2":
     * "1492-10-12T10:15:01-08:00"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2":
     *     "1492-10-12T10:15:01-08:00"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeValid() {
        return this.serviceClient.getDateTimeValid();
    }

    /**
     * Set dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2":
     * "1492-10-12T10:15:01-08:00"}.
     *
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2":
     *     "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeValid(Map<String, OffsetDateTime> arrayBody) {
        this.serviceClient.putDateTimeValid(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeInvalidNull() {
        return this.serviceClient.getDateTimeInvalidNull();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeInvalidChars() {
        return this.serviceClient.getDateTimeInvalidChars();
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35
     * GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35
     *     GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeRfc1123Valid() {
        return this.serviceClient.getDateTimeRfc1123Valid();
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2":
     * "Wed, 12 Oct 1492 10:15:01 GMT"}.
     *
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35
     *     GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123Valid(Map<String, OffsetDateTime> arrayBody) {
        this.serviceClient.putDateTimeRfc1123Valid(arrayBody);
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Duration> getDurationValid() {
        return this.serviceClient.getDurationValid();
    }

    /**
     * Set dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     *
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDurationValid(Map<String, Duration> arrayBody) {
        this.serviceClient.putDurationValid(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item
     * encoded in base64.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item
     *     encoded in base64.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getByteValid() {
        return this.serviceClient.getByteValid();
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each
     * elementencoded in base 64.
     *
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with
     *     each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByteValid(Map<String, byte[]> arrayBody) {
        this.serviceClient.putByteValid(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getByteInvalidNull() {
        return this.serviceClient.getByteInvalidNull();
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem
     * ipsum"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2":
     *     "Lorem ipsum"}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getBase64Url() {
        return this.serviceClient.getBase64Url();
    }

    /**
     * Get dictionary of complex type null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type null value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexNull() {
        return this.serviceClient.getComplexNull();
    }

    /**
     * Get empty dictionary of complex type {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary of complex type {}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexEmpty() {
        return this.serviceClient.getComplexEmpty();
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5,
     * "string": "6"}}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2":
     *     {"integer": 5, "string": "6"}}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexItemNull() {
        return this.serviceClient.getComplexItemNull();
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5,
     * "string": "6"}}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer":
     *     5, "string": "6"}}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexItemEmpty() {
        return this.serviceClient.getComplexItemEmpty();
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2":
     * {"integer": 5, "string": "6"}}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"},
     *     "2": {"integer": 5, "string": "6"}}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexValid() {
        return this.serviceClient.getComplexValid();
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string":
     * "4"}, "2": {"integer": 5, "string": "6"}}.
     *
     * @param arrayBody Dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3,
     *     "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplexValid(Map<String, Widget> arrayBody) {
        this.serviceClient.putComplexValid(arrayBody);
    }

    /**
     * Get a null array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayNull() {
        return this.serviceClient.getArrayNull();
    }

    /**
     * Get an empty dictionary {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty dictionary {}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayEmpty() {
        return this.serviceClient.getArrayEmpty();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayItemNull() {
        return this.serviceClient.getArrayItemNull();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayItemEmpty() {
        return this.serviceClient.getArrayItemEmpty();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayValid() {
        return this.serviceClient.getArrayValid();
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     *
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArrayValid(Map<String, List<String>> arrayBody) {
        this.serviceClient.putArrayValid(arrayBody);
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries with value null.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Map<String, String>> getDictionaryNull() {
        return this.serviceClient.getDictionaryNull();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Map<String, String>> getDictionaryEmpty() {
        return this.serviceClient.getDictionaryEmpty();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3":
     * "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two",
     *     "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Map<String, String>> getDictionaryItemNull() {
        return this.serviceClient.getDictionaryItemNull();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3":
     * "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two",
     *     "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Map<String, String>> getDictionaryItemEmpty() {
        return this.serviceClient.getDictionaryItemEmpty();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3":
     * "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two",
     *     "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Map<String, String>> getDictionaryValid() {
        return this.serviceClient.getDictionaryValid();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3":
     * "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     *
     * @param arrayBody An dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one",
     *     "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight",
     *     "9": "nine"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionaryValid(Map<String, Map<String, String>> arrayBody) {
        this.serviceClient.putDictionaryValid(arrayBody);
    }
}
