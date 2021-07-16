package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodycomplex.implementation.DictionariesImpl;

/** Initializes a new instance of the synchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class)
public final class DictionaryClient {
    private final DictionariesImpl serviceClient;

    /**
     * Initializes an instance of Dictionaries client.
     *
     * @param serviceClient the service client implementation.
     */
    DictionaryClient(DictionariesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with dictionary property.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getValid(RequestOptions requestOptions) {
        return this.serviceClient.getValid(requestOptions);
    }

    /**
     * Get complex types with dictionary property.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getValidWithResponse(requestOptions, context);
    }

    /**
     * Put complex types with dictionary property.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putValid(complexBody, requestOptions);
    }

    /**
     * Put complex types with dictionary property.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putValidWithResponse(complexBody, requestOptions, context);
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getEmpty(RequestOptions requestOptions) {
        return this.serviceClient.getEmpty(requestOptions);
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getEmptyWithResponse(requestOptions, context);
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(BinaryData complexBody, RequestOptions requestOptions) {
        this.serviceClient.putEmpty(complexBody, requestOptions);
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(BinaryData complexBody, RequestOptions requestOptions, Context context) {
        return this.serviceClient.putEmptyWithResponse(complexBody, requestOptions, context);
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNull(RequestOptions requestOptions) {
        return this.serviceClient.getNull(requestOptions);
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getNullWithResponse(requestOptions, context);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getNotProvided(RequestOptions requestOptions) {
        return this.serviceClient.getNotProvided(requestOptions);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     defaultProgram: {
     *         String: String
     *     }
     * }
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNotProvidedWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getNotProvidedWithResponse(requestOptions, context);
    }
}
