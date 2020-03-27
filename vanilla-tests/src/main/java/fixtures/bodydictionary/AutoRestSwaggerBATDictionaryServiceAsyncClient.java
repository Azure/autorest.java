package fixtures.bodydictionary;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Base64Url;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import fixtures.bodydictionary.implementation.DictionarysImpl;
import fixtures.bodydictionary.models.ErrorException;
import fixtures.bodydictionary.models.Widget;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AutoRestSwaggerBATDictionaryService type.
 */
@ServiceClient(builder = AutoRestSwaggerBATDictionaryServiceBuilder.class)
public final class AutoRestSwaggerBATDictionaryServiceAsyncClient {
    private DictionarysImpl serviceClient;

    /**
     * Initializes an instance of AutoRestSwaggerBATDictionaryService client.
     */
    AutoRestSwaggerBATDictionaryServiceAsyncClient(DictionarysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getNullWithResponseAsync() {
        return this.serviceClient.getNullWithResponseAsync();
    }

    /**
     * Get null dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getNullAsync() {
        return this.serviceClient.getNullAsync();
    }

    /**
     * Get empty dictionary value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getEmptyWithResponseAsync() {
        return this.serviceClient.getEmptyWithResponseAsync();
    }

    /**
     * Get empty dictionary value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getEmptyAsync() {
        return this.serviceClient.getEmptyAsync();
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody The empty dictionary value {}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(Map<String, String> arrayBody) {
        return this.serviceClient.putEmptyWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody The empty dictionary value {}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(Map<String, String> arrayBody) {
        return this.serviceClient.putEmptyAsync(arrayBody);
    }

    /**
     * Get Dictionary with null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullValueWithResponseAsync() {
        return this.serviceClient.getNullValueWithResponseAsync();
    }

    /**
     * Get Dictionary with null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getNullValueAsync() {
        return this.serviceClient.getNullValueAsync();
    }

    /**
     * Get Dictionary with null key.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullKeyWithResponseAsync() {
        return this.serviceClient.getNullKeyWithResponseAsync();
    }

    /**
     * Get Dictionary with null key.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getNullKeyAsync() {
        return this.serviceClient.getNullKeyAsync();
    }

    /**
     * Get Dictionary with key as empty string.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getEmptyStringKeyWithResponseAsync() {
        return this.serviceClient.getEmptyStringKeyWithResponseAsync();
    }

    /**
     * Get Dictionary with key as empty string.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getEmptyStringKeyAsync() {
        return this.serviceClient.getEmptyStringKeyAsync();
    }

    /**
     * Get invalid Dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getInvalidWithResponseAsync() {
        return this.serviceClient.getInvalidWithResponseAsync();
    }

    /**
     * Get invalid Dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getInvalidAsync() {
        return this.serviceClient.getInvalidAsync();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanTfftWithResponseAsync() {
        return this.serviceClient.getBooleanTfftWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanTfftAsync() {
        return this.serviceClient.getBooleanTfftAsync();
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBooleanTfftWithResponseAsync(Map<String, Boolean> arrayBody) {
        return this.serviceClient.putBooleanTfftWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBooleanTfftAsync(Map<String, Boolean> arrayBody) {
        return this.serviceClient.putBooleanTfftAsync(arrayBody);
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidNullWithResponseAsync() {
        return this.serviceClient.getBooleanInvalidNullWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanInvalidNullAsync() {
        return this.serviceClient.getBooleanInvalidNullAsync();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidStringWithResponseAsync() {
        return this.serviceClient.getBooleanInvalidStringWithResponseAsync();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanInvalidStringAsync() {
        return this.serviceClient.getBooleanInvalidStringAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntegerValidWithResponseAsync() {
        return this.serviceClient.getIntegerValidWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntegerValidAsync() {
        return this.serviceClient.getIntegerValidAsync();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntegerValidWithResponseAsync(Map<String, Integer> arrayBody) {
        return this.serviceClient.putIntegerValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntegerValidAsync(Map<String, Integer> arrayBody) {
        return this.serviceClient.putIntegerValidAsync(arrayBody);
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidNullWithResponseAsync() {
        return this.serviceClient.getIntInvalidNullWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntInvalidNullAsync() {
        return this.serviceClient.getIntInvalidNullAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidStringWithResponseAsync() {
        return this.serviceClient.getIntInvalidStringWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntInvalidStringAsync() {
        return this.serviceClient.getIntInvalidStringAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongValidWithResponseAsync() {
        return this.serviceClient.getLongValidWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongValidAsync() {
        return this.serviceClient.getLongValidAsync();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongValidWithResponseAsync(Map<String, Long> arrayBody) {
        return this.serviceClient.putLongValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongValidAsync(Map<String, Long> arrayBody) {
        return this.serviceClient.putLongValidAsync(arrayBody);
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidNullWithResponseAsync() {
        return this.serviceClient.getLongInvalidNullWithResponseAsync();
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongInvalidNullAsync() {
        return this.serviceClient.getLongInvalidNullAsync();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidStringWithResponseAsync() {
        return this.serviceClient.getLongInvalidStringWithResponseAsync();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongInvalidStringAsync() {
        return this.serviceClient.getLongInvalidStringAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatValidWithResponseAsync() {
        return this.serviceClient.getFloatValidWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatValidAsync() {
        return this.serviceClient.getFloatValidAsync();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatValidWithResponseAsync(Map<String, Float> arrayBody) {
        return this.serviceClient.putFloatValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatValidAsync(Map<String, Float> arrayBody) {
        return this.serviceClient.putFloatValidAsync(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidNullWithResponseAsync() {
        return this.serviceClient.getFloatInvalidNullWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatInvalidNullAsync() {
        return this.serviceClient.getFloatInvalidNullAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidStringWithResponseAsync() {
        return this.serviceClient.getFloatInvalidStringWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatInvalidStringAsync() {
        return this.serviceClient.getFloatInvalidStringAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleValidWithResponseAsync() {
        return this.serviceClient.getDoubleValidWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleValidAsync() {
        return this.serviceClient.getDoubleValidAsync();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleValidWithResponseAsync(Map<String, Double> arrayBody) {
        return this.serviceClient.putDoubleValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleValidAsync(Map<String, Double> arrayBody) {
        return this.serviceClient.putDoubleValidAsync(arrayBody);
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidNullWithResponseAsync() {
        return this.serviceClient.getDoubleInvalidNullWithResponseAsync();
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleInvalidNullAsync() {
        return this.serviceClient.getDoubleInvalidNullAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidStringWithResponseAsync() {
        return this.serviceClient.getDoubleInvalidStringWithResponseAsync();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleInvalidStringAsync() {
        return this.serviceClient.getDoubleInvalidStringAsync();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringValidWithResponseAsync() {
        return this.serviceClient.getStringValidWithResponseAsync();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringValidAsync() {
        return this.serviceClient.getStringValidAsync();
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody The dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringValidWithResponseAsync(Map<String, String> arrayBody) {
        return this.serviceClient.putStringValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody The dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringValidAsync(Map<String, String> arrayBody) {
        return this.serviceClient.putStringValidAsync(arrayBody);
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithNullWithResponseAsync() {
        return this.serviceClient.getStringWithNullWithResponseAsync();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringWithNullAsync() {
        return this.serviceClient.getStringWithNullAsync();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithInvalidWithResponseAsync() {
        return this.serviceClient.getStringWithInvalidWithResponseAsync();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringWithInvalidAsync() {
        return this.serviceClient.getStringWithInvalidAsync();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateValidWithResponseAsync() {
        return this.serviceClient.getDateValidWithResponseAsync();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateValidAsync() {
        return this.serviceClient.getDateValidAsync();
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateValidWithResponseAsync(Map<String, LocalDate> arrayBody) {
        return this.serviceClient.putDateValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateValidAsync(Map<String, LocalDate> arrayBody) {
        return this.serviceClient.putDateValidAsync(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidNullWithResponseAsync() {
        return this.serviceClient.getDateInvalidNullWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateInvalidNullAsync() {
        return this.serviceClient.getDateInvalidNullAsync();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidCharsWithResponseAsync() {
        return this.serviceClient.getDateInvalidCharsWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateInvalidCharsAsync() {
        return this.serviceClient.getDateInvalidCharsAsync();
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeValidWithResponseAsync() {
        return this.serviceClient.getDateTimeValidWithResponseAsync();
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeValidAsync() {
        return this.serviceClient.getDateTimeValidAsync();
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeValidWithResponseAsync(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeValidAsync(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeValidAsync(arrayBody);
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidNullWithResponseAsync() {
        return this.serviceClient.getDateTimeInvalidNullWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeInvalidNullAsync() {
        return this.serviceClient.getDateTimeInvalidNullAsync();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidCharsWithResponseAsync() {
        return this.serviceClient.getDateTimeInvalidCharsWithResponseAsync();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeInvalidCharsAsync() {
        return this.serviceClient.getDateTimeInvalidCharsAsync();
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeRfc1123ValidWithResponseAsync() {
        return this.serviceClient.getDateTimeRfc1123ValidWithResponseAsync();
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeRfc1123ValidAsync() {
        return this.serviceClient.getDateTimeRfc1123ValidAsync();
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123ValidWithResponseAsync(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeRfc1123ValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123ValidAsync(Map<String, OffsetDateTime> arrayBody) {
        return this.serviceClient.putDateTimeRfc1123ValidAsync(arrayBody);
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Duration>>> getDurationValidWithResponseAsync() {
        return this.serviceClient.getDurationValidWithResponseAsync();
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Duration>> getDurationValidAsync() {
        return this.serviceClient.getDurationValidAsync();
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationValidWithResponseAsync(Map<String, Duration> arrayBody) {
        return this.serviceClient.putDurationValidWithResponseAsync(arrayBody);
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationValidAsync(Map<String, Duration> arrayBody) {
        return this.serviceClient.putDurationValidAsync(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteValidWithResponseAsync() {
        return this.serviceClient.getByteValidWithResponseAsync();
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getByteValidAsync() {
        return this.serviceClient.getByteValidAsync();
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteValidWithResponseAsync(Map<String, byte[]> arrayBody) {
        return this.serviceClient.putByteValidWithResponseAsync(arrayBody);
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteValidAsync(Map<String, byte[]> arrayBody) {
        return this.serviceClient.putByteValidAsync(arrayBody);
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteInvalidNullWithResponseAsync() {
        return this.serviceClient.getByteInvalidNullWithResponseAsync();
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getByteInvalidNullAsync() {
        return this.serviceClient.getByteInvalidNullAsync();
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getBase64UrlWithResponseAsync() {
        return this.serviceClient.getBase64UrlWithResponseAsync();
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getBase64UrlAsync() {
        return this.serviceClient.getBase64UrlAsync();
    }

    /**
     * Get dictionary of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexNullWithResponseAsync() {
        return this.serviceClient.getComplexNullWithResponseAsync();
    }

    /**
     * Get dictionary of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexNullAsync() {
        return this.serviceClient.getComplexNullAsync();
    }

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexEmptyWithResponseAsync() {
        return this.serviceClient.getComplexEmptyWithResponseAsync();
    }

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexEmptyAsync() {
        return this.serviceClient.getComplexEmptyAsync();
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemNullWithResponseAsync() {
        return this.serviceClient.getComplexItemNullWithResponseAsync();
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexItemNullAsync() {
        return this.serviceClient.getComplexItemNullAsync();
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemEmptyWithResponseAsync() {
        return this.serviceClient.getComplexItemEmptyWithResponseAsync();
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexItemEmptyAsync() {
        return this.serviceClient.getComplexItemEmptyAsync();
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexValidWithResponseAsync() {
        return this.serviceClient.getComplexValidWithResponseAsync();
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexValidAsync() {
        return this.serviceClient.getComplexValidAsync();
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexValidWithResponseAsync(Map<String, Widget> arrayBody) {
        return this.serviceClient.putComplexValidWithResponseAsync(arrayBody);
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexValidAsync(Map<String, Widget> arrayBody) {
        return this.serviceClient.putComplexValidAsync(arrayBody);
    }

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayNullWithResponseAsync() {
        return this.serviceClient.getArrayNullWithResponseAsync();
    }

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayNullAsync() {
        return this.serviceClient.getArrayNullAsync();
    }

    /**
     * Get an empty dictionary {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayEmptyWithResponseAsync() {
        return this.serviceClient.getArrayEmptyWithResponseAsync();
    }

    /**
     * Get an empty dictionary {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayEmptyAsync() {
        return this.serviceClient.getArrayEmptyAsync();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemNullWithResponseAsync() {
        return this.serviceClient.getArrayItemNullWithResponseAsync();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayItemNullAsync() {
        return this.serviceClient.getArrayItemNullAsync();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemEmptyWithResponseAsync() {
        return this.serviceClient.getArrayItemEmptyWithResponseAsync();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayItemEmptyAsync() {
        return this.serviceClient.getArrayItemEmptyAsync();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayValidWithResponseAsync() {
        return this.serviceClient.getArrayValidWithResponseAsync();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayValidAsync() {
        return this.serviceClient.getArrayValidAsync();
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayValidWithResponseAsync(Map<String, List<String>> arrayBody) {
        return this.serviceClient.putArrayValidWithResponseAsync(arrayBody);
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayValidAsync(Map<String, List<String>> arrayBody) {
        return this.serviceClient.putArrayValidAsync(arrayBody);
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryNullWithResponseAsync() {
        return this.serviceClient.getDictionaryNullWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryNullAsync() {
        return this.serviceClient.getDictionaryNullAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryEmptyWithResponseAsync() {
        return this.serviceClient.getDictionaryEmptyWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryEmptyAsync() {
        return this.serviceClient.getDictionaryEmptyAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemNullWithResponseAsync() {
        return this.serviceClient.getDictionaryItemNullWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryItemNullAsync() {
        return this.serviceClient.getDictionaryItemNullAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemEmptyWithResponseAsync() {
        return this.serviceClient.getDictionaryItemEmptyWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryItemEmptyAsync() {
        return this.serviceClient.getDictionaryItemEmptyAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryValidWithResponseAsync() {
        return this.serviceClient.getDictionaryValidWithResponseAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryValidAsync() {
        return this.serviceClient.getDictionaryValidAsync();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryValidWithResponseAsync(Map<String, Object> arrayBody) {
        return this.serviceClient.putDictionaryValidWithResponseAsync(arrayBody);
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryValidAsync(Map<String, Object> arrayBody) {
        return this.serviceClient.putDictionaryValidAsync(arrayBody);
    }
}
