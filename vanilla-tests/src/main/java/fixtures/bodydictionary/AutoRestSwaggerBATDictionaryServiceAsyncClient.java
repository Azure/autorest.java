package fixtures.bodydictionary;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.bodydictionary.implementation.DictionarysImpl;
import fixtures.bodydictionary.models.ErrorException;
import fixtures.bodydictionary.models.Widget;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestSwaggerBATDictionaryService type. */
@ServiceClient(builder = AutoRestSwaggerBATDictionaryServiceBuilder.class)
public final class AutoRestSwaggerBATDictionaryServiceAsyncClient {
    private DictionarysImpl serviceClient;

    /** Initializes an instance of Dictionarys client. */
    AutoRestSwaggerBATDictionaryServiceAsyncClient(DictionarysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null dictionary value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null dictionary value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getNullWithResponse() {
        return this.serviceClient.getNullWithResponseAsync();
    }

    /**
     * Get null dictionary value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null dictionary value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getNull() {
        return this.serviceClient.getNullAsync();
    }

    /**
     * Get empty dictionary value {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary value {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getEmptyWithResponse() {
        return this.serviceClient.getEmptyWithResponseAsync();
    }

    /**
     * Get empty dictionary value {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary value {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getEmpty() {
        return this.serviceClient.getEmptyAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponse(Map<String, String> arrayBody) {
        return this.serviceClient.putEmptyWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmpty(Map<String, String> arrayBody) {
        return this.serviceClient.putEmptyAsync(arrayBody);
    }

    /**
     * Get Dictionary with null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullValueWithResponse() {
        return this.serviceClient.getNullValueWithResponseAsync();
    }

    /**
     * Get Dictionary with null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getNullValue() {
        return this.serviceClient.getNullValueAsync();
    }

    /**
     * Get Dictionary with null key.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null key.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullKeyWithResponse() {
        return this.serviceClient.getNullKeyWithResponseAsync();
    }

    /**
     * Get Dictionary with null key.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with null key.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getNullKey() {
        return this.serviceClient.getNullKeyAsync();
    }

    /**
     * Get Dictionary with key as empty string.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with key as empty string.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getEmptyStringKeyWithResponse() {
        return this.serviceClient.getEmptyStringKeyWithResponseAsync();
    }

    /**
     * Get Dictionary with key as empty string.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary with key as empty string.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getEmptyStringKey() {
        return this.serviceClient.getEmptyStringKeyAsync();
    }

    /**
     * Get invalid Dictionary value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Dictionary value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getInvalidWithResponse() {
        return this.serviceClient.getInvalidWithResponseAsync();
    }

    /**
     * Get invalid Dictionary value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Dictionary value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getInvalid() {
        return this.serviceClient.getInvalidAsync();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanTfftWithResponse() {
        return this.serviceClient.getBooleanTfftWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanTfft() {
        return this.serviceClient.getBooleanTfftAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBooleanTfftWithResponse(Map<String, Boolean> arrayBody) {
        return this.serviceClient.putBooleanTfftWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBooleanTfft(Map<String, Boolean> arrayBody) {
        return this.serviceClient.putBooleanTfftAsync(arrayBody);
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": null, "2": false }.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidNullWithResponse() {
        return this.serviceClient.getBooleanInvalidNullWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": true, "1": null, "2": false }.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanInvalidNull() {
        return this.serviceClient.getBooleanInvalidNullAsync();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidStringWithResponse() {
        return this.serviceClient.getBooleanInvalidStringWithResponseAsync();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanInvalidString() {
        return this.serviceClient.getBooleanInvalidStringAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntegerValidWithResponse() {
        return this.serviceClient.getIntegerValidWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntegerValid() {
        return this.serviceClient.getIntegerValidAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntegerValidWithResponse(Map<String, Integer> arrayBody) {
        return this.serviceClient.putIntegerValidWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntegerValid(Map<String, Integer> arrayBody) {
        return this.serviceClient.putIntegerValidAsync(arrayBody);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": null, "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidNullWithResponse() {
        return this.serviceClient.getIntInvalidNullWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": null, "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntInvalidNull() {
        return this.serviceClient.getIntInvalidNullAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidStringWithResponse() {
        return this.serviceClient.getIntInvalidStringWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntInvalidString() {
        return this.serviceClient.getIntInvalidStringAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongValidWithResponse() {
        return this.serviceClient.getLongValidWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongValid() {
        return this.serviceClient.getLongValidAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongValidWithResponse(Map<String, Long> arrayBody) {
        return this.serviceClient.putLongValidWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongValid(Map<String, Long> arrayBody) {
        return this.serviceClient.putLongValidAsync(arrayBody);
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": null, "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidNullWithResponse() {
        return this.serviceClient.getLongInvalidNullWithResponseAsync();
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": null, "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongInvalidNull() {
        return this.serviceClient.getLongInvalidNullAsync();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidStringWithResponse() {
        return this.serviceClient.getLongInvalidStringWithResponseAsync();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long dictionary value {"0": 1, "1": "integer", "2": 0}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongInvalidString() {
        return this.serviceClient.getLongInvalidStringAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatValidWithResponse() {
        return this.serviceClient.getFloatValidWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatValid() {
        return this.serviceClient.getFloatValidAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatValidWithResponse(Map<String, Float> arrayBody) {
        return this.serviceClient.putFloatValidWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatValid(Map<String, Float> arrayBody) {
        return this.serviceClient.putFloatValidAsync(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidNullWithResponse() {
        return this.serviceClient.getFloatInvalidNullWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatInvalidNull() {
        return this.serviceClient.getFloatInvalidNullAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidStringWithResponse() {
        return this.serviceClient.getFloatInvalidStringWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatInvalidString() {
        return this.serviceClient.getFloatInvalidStringAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleValidWithResponse() {
        return this.serviceClient.getDoubleValidWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0, "1": -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleValid() {
        return this.serviceClient.getDoubleValidAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleValidWithResponse(Map<String, Double> arrayBody) {
        return this.serviceClient.putDoubleValidWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleValid(Map<String, Double> arrayBody) {
        return this.serviceClient.putDoubleValidAsync(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidNullWithResponse() {
        return this.serviceClient.getDoubleInvalidNullWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float dictionary value {"0": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleInvalidNull() {
        return this.serviceClient.getDoubleInvalidNullAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidStringWithResponse() {
        return this.serviceClient.getDoubleInvalidStringWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean dictionary value {"0": 1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleInvalidString() {
        return this.serviceClient.getDoubleInvalidStringAsync();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringValidWithResponse() {
        return this.serviceClient.getStringValidWithResponseAsync();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringValid() {
        return this.serviceClient.getStringValidAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringValidWithResponse(Map<String, String> arrayBody) {
        return this.serviceClient.putStringValidWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringValid(Map<String, String> arrayBody) {
        return this.serviceClient.putStringValidAsync(arrayBody);
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithNullWithResponse() {
        return this.serviceClient.getStringWithNullWithResponseAsync();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringWithNull() {
        return this.serviceClient.getStringWithNullAsync();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithInvalidWithResponse() {
        return this.serviceClient.getStringWithInvalidWithResponseAsync();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringWithInvalid() {
        return this.serviceClient.getStringWithInvalidAsync();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateValidWithResponse() {
        return this.serviceClient.getDateValidWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateValid() {
        return this.serviceClient.getDateValidAsync();
    }

    /**
     * Set dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     *
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateValidWithResponse(Map<String, LocalDate> arrayBody) {
        return this.serviceClient.putDateValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     *
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateValid(Map<String, LocalDate> arrayBody) {
        return this.serviceClient.putDateValidAsync(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidNullWithResponse() {
        return this.serviceClient.getDateInvalidNullWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateInvalidNull() {
        return this.serviceClient.getDateInvalidNullAsync();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2011-03-22", "1": "date"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidCharsWithResponse() {
        return this.serviceClient.getDateInvalidCharsWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2011-03-22", "1": "date"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateInvalidChars() {
        return this.serviceClient.getDateInvalidCharsAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeValidWithResponse() {
        return this.serviceClient.getDateTimeValidWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeValid() {
        return this.serviceClient.getDateTimeValidAsync();
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeValidWithResponse(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeValidWithResponseAsync(arrayBody);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeValid(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeValidAsync(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidNullWithResponse() {
        return this.serviceClient.getDateTimeInvalidNullWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeInvalidNull() {
        return this.serviceClient.getDateTimeInvalidNullAsync();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidCharsWithResponse() {
        return this.serviceClient.getDateTimeInvalidCharsWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeInvalidChars() {
        return this.serviceClient.getDateTimeInvalidCharsAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeRfc1123ValidWithResponse() {
        return this.serviceClient.getDateTimeRfc1123ValidWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeRfc1123Valid() {
        return this.serviceClient.getDateTimeRfc1123ValidAsync();
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123ValidWithResponse(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeRfc1123ValidWithResponseAsync(arrayBody);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123Valid(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeRfc1123ValidAsync(arrayBody);
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration dictionary value {"0": "P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Duration>>> getDurationValidWithResponse() {
        return this.serviceClient.getDurationValidWithResponseAsync();
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration dictionary value {"0": "P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Duration>> getDurationValid() {
        return this.serviceClient.getDurationValidAsync();
    }

    /**
     * Set dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     *
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationValidWithResponse(Map<String, Duration> arrayBody) {
        return this.serviceClient.putDurationValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     *
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationValid(Map<String, Duration> arrayBody) {
        return this.serviceClient.putDurationValidAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteValidWithResponse() {
        return this.serviceClient.getByteValidWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getByteValid() {
        return this.serviceClient.getByteValidAsync();
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteValidWithResponse(Map<String, byte[]> arrayBody) {
        return this.serviceClient.putByteValidWithResponseAsync(arrayBody);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteValid(Map<String, byte[]> arrayBody) {
        return this.serviceClient.putByteValidAsync(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteInvalidNullWithResponse() {
        return this.serviceClient.getByteInvalidNullWithResponseAsync();
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getByteInvalidNull() {
        return this.serviceClient.getByteInvalidNullAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getBase64UrlWithResponse() {
        return this.serviceClient.getBase64UrlWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getBase64Url() {
        return this.serviceClient.getBase64UrlAsync();
    }

    /**
     * Get dictionary of complex type null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexNullWithResponse() {
        return this.serviceClient.getComplexNullWithResponseAsync();
    }

    /**
     * Get dictionary of complex type null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return dictionary of complex type null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexNull() {
        return this.serviceClient.getComplexNullAsync();
    }

    /**
     * Get empty dictionary of complex type {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary of complex type {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexEmptyWithResponse() {
        return this.serviceClient.getComplexEmptyWithResponseAsync();
    }

    /**
     * Get empty dictionary of complex type {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty dictionary of complex type {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexEmpty() {
        return this.serviceClient.getComplexEmptyAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemNullWithResponse() {
        return this.serviceClient.getComplexItemNullWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexItemNull() {
        return this.serviceClient.getComplexItemNullAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemEmptyWithResponse() {
        return this.serviceClient.getComplexItemEmptyWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexItemEmpty() {
        return this.serviceClient.getComplexItemEmptyAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexValidWithResponse() {
        return this.serviceClient.getComplexValidWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexValid() {
        return this.serviceClient.getComplexValidAsync();
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexValidWithResponse(Map<String, Widget> arrayBody) {
        return this.serviceClient.putComplexValidWithResponseAsync(arrayBody);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexValid(Map<String, Widget> arrayBody) {
        return this.serviceClient.putComplexValidAsync(arrayBody);
    }

    /**
     * Get a null array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayNullWithResponse() {
        return this.serviceClient.getArrayNullWithResponseAsync();
    }

    /**
     * Get a null array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayNull() {
        return this.serviceClient.getArrayNullAsync();
    }

    /**
     * Get an empty dictionary {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty dictionary {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayEmptyWithResponse() {
        return this.serviceClient.getArrayEmptyWithResponseAsync();
    }

    /**
     * Get an empty dictionary {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty dictionary {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayEmpty() {
        return this.serviceClient.getArrayEmptyAsync();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemNullWithResponse() {
        return this.serviceClient.getArrayItemNullWithResponseAsync();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayItemNull() {
        return this.serviceClient.getArrayItemNullAsync();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemEmptyWithResponse() {
        return this.serviceClient.getArrayItemEmptyWithResponseAsync();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayItemEmpty() {
        return this.serviceClient.getArrayItemEmptyAsync();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayValidWithResponse() {
        return this.serviceClient.getArrayValidWithResponseAsync();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayValid() {
        return this.serviceClient.getArrayValidAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayValidWithResponse(Map<String, List<String>> arrayBody) {
        return this.serviceClient.putArrayValidWithResponseAsync(arrayBody);
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayValid(Map<String, List<String>> arrayBody) {
        return this.serviceClient.putArrayValidAsync(arrayBody);
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries with value null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryNullWithResponse() {
        return this.serviceClient.getDictionaryNullWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries with value null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryNull() {
        return this.serviceClient.getDictionaryNullAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryEmptyWithResponse() {
        return this.serviceClient.getDictionaryEmptyWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryEmpty() {
        return this.serviceClient.getDictionaryEmptyAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemNullWithResponse() {
        return this.serviceClient.getDictionaryItemNullWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryItemNull() {
        return this.serviceClient.getDictionaryItemNullAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemEmptyWithResponse() {
        return this.serviceClient.getDictionaryItemEmptyWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryItemEmpty() {
        return this.serviceClient.getDictionaryItemEmptyAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryValidWithResponse() {
        return this.serviceClient.getDictionaryValidWithResponseAsync();
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryValid() {
        return this.serviceClient.getDictionaryValidAsync();
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
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two",
     *     "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryValidWithResponse(Map<String, Object> arrayBody) {
        return this.serviceClient.putDictionaryValidWithResponseAsync(arrayBody);
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
     * @return an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two",
     *     "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryValid(Map<String, Object> arrayBody) {
        return this.serviceClient.putDictionaryValidAsync(arrayBody);
    }
}
