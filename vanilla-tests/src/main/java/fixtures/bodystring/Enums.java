package fixtures.bodystring;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import fixtures.bodystring.models.Colors;
import fixtures.bodystring.models.RefColorConstant;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Enums. */
public interface Enums {
    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Colors>> getNotExpandableWithResponseAsync();

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Colors> getNotExpandableAsync();

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Colors getNotExpandable();

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putNotExpandableWithResponseAsync(Colors stringBody);

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putNotExpandableAsync(Colors stringBody);

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putNotExpandable(Colors stringBody);

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Colors>> getReferencedWithResponseAsync();

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Colors> getReferencedAsync();

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Colors getReferenced();

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putReferencedWithResponseAsync(Colors enumStringBody);

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putReferencedAsync(Colors enumStringBody);

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putReferenced(Colors enumStringBody);

    /**
     * Get value 'green-color' from the constant.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<RefColorConstant>> getReferencedConstantWithResponseAsync();

    /**
     * Get value 'green-color' from the constant.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<RefColorConstant> getReferencedConstantAsync();

    /**
     * Get value 'green-color' from the constant.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    RefColorConstant getReferencedConstant();

    /**
     * Sends value 'green-color' from a constant.
     *
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putReferencedConstantWithResponseAsync(RefColorConstant enumStringBody);

    /**
     * Sends value 'green-color' from a constant.
     *
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putReferencedConstantAsync(RefColorConstant enumStringBody);

    /**
     * Sends value 'green-color' from a constant.
     *
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putReferencedConstant(RefColorConstant enumStringBody);
}
