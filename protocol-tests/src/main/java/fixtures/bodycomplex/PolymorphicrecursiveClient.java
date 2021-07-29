package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodycomplex.implementation.PolymorphicrecursivesImpl;

/** Initializes a new instance of the synchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class PolymorphicrecursiveClient {
    private final PolymorphicrecursivesImpl serviceClient;

    /**
     * Initializes an instance of Polymorphicrecursives client.
     *
     * @param serviceClient the service client implementation.
     */
    PolymorphicrecursiveClient(PolymorphicrecursivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getValid(RequestOptions requestOptions) {
        return this.serviceClient.getValid(requestOptions);
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getValidWithResponse(requestOptions, context);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putValid(complexBody, requestOptions);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putValidWithResponse(complexBody, requestOptions, context);
    }
}
