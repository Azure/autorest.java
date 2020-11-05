package fixtures.bodystring;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in StringOperations. */
public interface StringOperations {
    /**
     * Get null string value value.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<String>> getNullWithResponseAsync();

    /**
     * Get null string value value.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<String> getNullAsync();

    /**
     * Get null string value value.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String getNull();

    /**
     * Set string value null.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putNullWithResponseAsync(String stringBody);

    /**
     * Set string value null.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putNullAsync(String stringBody);

    /**
     * Set string value null.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putNullAsync();

    /**
     * Set string value null.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putNull(String stringBody);

    /**
     * Set string value null.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putNull();

    /**
     * Get empty string value value ''.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<String>> getEmptyWithResponseAsync();

    /**
     * Get empty string value value ''.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<String> getEmptyAsync();

    /**
     * Get empty string value value ''.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String getEmpty();

    /**
     * Set string value empty ''.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putEmptyWithResponseAsync();

    /**
     * Set string value empty ''.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putEmptyAsync();

    /**
     * Set string value empty ''.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putEmpty();

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<String>> getMbcsWithResponseAsync();

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<String> getMbcsAsync();

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String getMbcs();

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putMbcsWithResponseAsync();

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putMbcsAsync();

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putMbcs();

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<String>> getWhitespaceWithResponseAsync();

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<String> getWhitespaceAsync();

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time
     *     for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String getWhitespace();

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putWhitespaceWithResponseAsync();

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putWhitespaceAsync();

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for
     * all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putWhitespace();

    /**
     * Get String value when no string value is sent in response payload.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<String>> getNotProvidedWithResponseAsync();

    /**
     * Get String value when no string value is sent in response payload.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<String> getNotProvidedAsync();

    /**
     * Get String value when no string value is sent in response payload.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String getNotProvided();

    /**
     * Get value that is base64 encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<byte[]>> getBase64EncodedWithResponseAsync();

    /**
     * Get value that is base64 encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<byte[]> getBase64EncodedAsync();

    /**
     * Get value that is base64 encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    byte[] getBase64Encoded();

    /**
     * Get value that is base64url encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<byte[]>> getBase64UrlEncodedWithResponseAsync();

    /**
     * Get value that is base64url encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<byte[]> getBase64UrlEncodedAsync();

    /**
     * Get value that is base64url encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    byte[] getBase64UrlEncoded();

    /**
     * Put value that is base64url encoded.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<Void>> putBase64UrlEncodedWithResponseAsync(byte[] stringBody);

    /**
     * Put value that is base64url encoded.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Void> putBase64UrlEncodedAsync(byte[] stringBody);

    /**
     * Put value that is base64url encoded.
     *
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void putBase64UrlEncoded(byte[] stringBody);

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<byte[]>> getNullBase64UrlEncodedWithResponseAsync();

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<byte[]> getNullBase64UrlEncodedAsync();

    /**
     * Get null value that is expected to be base64url encoded.
     *
     * @throws fixtures.bodystring.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    byte[] getNullBase64UrlEncoded();
}
