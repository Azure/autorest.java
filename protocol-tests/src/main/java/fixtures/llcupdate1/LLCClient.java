package fixtures.llcupdate1;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.llcupdate1.implementation.ParamsImpl;

/** Initializes a new instance of the synchronous LLCClient type. */
@ServiceClient(builder = LLCClientBuilder.class)
public final class LLCClient {
    private final ParamsImpl serviceClient;

    /**
     * Initializes an instance of Params client.
     *
     * @param serviceClient the service client implementation.
     */
    LLCClient(ParamsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter1</td><td>String</td><td>Yes</td><td>I am a required parameter with a client default value</td></tr>
     *     <tr><td>parameter2</td><td>String</td><td>No</td><td>I was a required parameter, but now I'm optional</td></tr>
     *     <tr><td>parameter3</td><td>String</td><td>Yes</td><td>I am a required parameter and I'm last in Swagger</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getRequiredWithResponse(requestOptions, context);
    }
}
