package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodycomplex.implementation.PolymorphismsImpl;

/** Initializes a new instance of the synchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class PolymorphismClient {
    private final PolymorphismsImpl serviceClient;

    /**
     * Initializes an instance of Polymorphisms client.
     *
     * @param serviceClient the service client implementation.
     */
    PolymorphismClient(PolymorphismsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic.
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
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return complex types that are polymorphic.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getValidWithResponse(requestOptions, context);
    }

    /**
     * Put complex types that are polymorphic.
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
     * @param complexBody Please put a salmon that looks like this: { 'fishtype':'Salmon', 'location':'alaska',
     *     'iswild':true, 'species':'king', 'length':1.0, 'siblings':[ { 'fishtype':'Shark', 'age':6, 'birthday':
     *     '2012-01-05T01:00:00Z', 'length':20.0, 'species':'predator', }, { 'fishtype':'Sawshark', 'age':105,
     *     'birthday': '1900-01-05T01:00:00Z', 'length':10.0, 'picture': new Buffer([255, 255, 255, 255,
     *     254]).toString('base64'), 'species':'dangerous', }, { 'fishtype': 'goblin', 'age': 1, 'birthday':
     *     '2015-08-08T00:00:00Z', 'length': 30.0, 'species': 'scary', 'jawsize': 5 } ] };.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putValidWithResponse(complexBody, requestOptions, context);
    }

    /**
     * Get complex types that are polymorphic, JSON key contains a dot.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return complex types that are polymorphic, JSON key contains a dot.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDotSyntaxWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDotSyntaxWithResponse(requestOptions, context);
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with
     * discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     sampleSalmon: {
     *         species: String
     *         location: String
     *         iswild: Boolean
     *     }
     *     salmons: [
     *         (recursive schema, see above)
     *     ]
     *     sampleFish: {
     *         species: String
     *     }
     *     fishes: [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     with discriminator specified.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getComposedWithDiscriminatorWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.getComposedWithDiscriminatorWithResponse(requestOptions, context);
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type,
     * without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     sampleSalmon: {
     *         species: String
     *         location: String
     *         iswild: Boolean
     *     }
     *     salmons: [
     *         (recursive schema, see above)
     *     ]
     *     sampleFish: {
     *         species: String
     *     }
     *     fishes: [
     *         (recursive schema, see above)
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     without discriminator specified on wire.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getComposedWithoutDiscriminatorWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.getComposedWithoutDiscriminatorWithResponse(requestOptions, context);
    }

    /**
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         {
     *             species: String
     *             length: float
     *             siblings: [
     *                 (recursive schema, see above)
     *             ]
     *         }
     *     ]
     *     location: String
     *     iswild: Boolean
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return complex types that are polymorphic, but not at the root of the hierarchy; also have additional
     *     properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getComplicatedWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getComplicatedWithResponse(requestOptions, context);
    }

    /**
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         {
     *             species: String
     *             length: float
     *             siblings: [
     *                 (recursive schema, see above)
     *             ]
     *         }
     *     ]
     *     location: String
     *     iswild: Boolean
     * }
     * }</pre>
     *
     * @param complexBody The complexBody parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putComplicatedWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putComplicatedWithResponse(complexBody, requestOptions, context);
    }

    /**
     * Put complex types that are polymorphic, omitting the discriminator.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     species: String
     *     length: float
     *     siblings: [
     *         {
     *             species: String
     *             length: float
     *             siblings: [
     *                 (recursive schema, see above)
     *             ]
     *         }
     *     ]
     *     location: String
     *     iswild: Boolean
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * (recursive schema, see above)
     * }</pre>
     *
     * @param complexBody The complexBody parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putMissingDiscriminatorWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putMissingDiscriminatorWithResponse(complexBody, requestOptions, context);
    }

    /**
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be
     * allowed from the client.
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
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to
     *     be sent: { "fishtype": "sawshark", "species": "snaggle toothed", "length": 18.5, "age": 2, "birthday":
     *     "2013-06-01T01:00:00Z", "location": "alaska", "picture": base64(FF FF FF FF FE), "siblings": [ { "fishtype":
     *     "shark", "species": "predator", "birthday": "2012-01-05T01:00:00Z", "length": 20, "age": 6 }, { "fishtype":
     *     "sawshark", "species": "dangerous", "picture": base64(FF FF FF FF FE), "length": 10, "age": 105 } ] }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidMissingRequiredWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putValidMissingRequiredWithResponse(complexBody, requestOptions, context);
    }
}
