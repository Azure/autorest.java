package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.PolymorphicrecursivesImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class, isAsync = true)
public final class PolymorphicrecursiveAsyncClient {
    private final PolymorphicrecursivesImpl serviceClient;

    /**
     * Initializes an instance of Polymorphicrecursives client.
     *
     * @param serviceClient the service client implementation.
     */
    PolymorphicrecursiveAsyncClient(PolymorphicrecursivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponseAsync(requestOptions);
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getValid(RequestOptions requestOptions) {
        return this.serviceClient.getValidAsync(requestOptions);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValid(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidAsync(complexBody, requestOptions);
    }
}
