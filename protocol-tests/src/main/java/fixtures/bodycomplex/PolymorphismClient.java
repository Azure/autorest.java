package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getValid(RequestOptions requestOptions) {
        return this.serviceClient.getValid(requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putValid(complexBody, requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDotSyntax(RequestOptions requestOptions) {
        return this.serviceClient.getDotSyntax(requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getComposedWithDiscriminator(RequestOptions requestOptions) {
        return this.serviceClient.getComposedWithDiscriminator(requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getComposedWithoutDiscriminator(RequestOptions requestOptions) {
        return this.serviceClient.getComposedWithoutDiscriminator(requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getComplicated(RequestOptions requestOptions) {
        return this.serviceClient.getComplicated(requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplicated(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putComplicated(complexBody, requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData putMissingDiscriminator(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putMissingDiscriminator(complexBody, requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValidMissingRequired(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putValidMissingRequired(complexBody, requestOptions);
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidMissingRequiredWithResponse(
            BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putValidMissingRequiredWithResponse(complexBody, requestOptions, context);
    }
}
