package fixtures.custombaseuri;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.custombaseuri.implementation.PathsImpl;

/** Initializes a new instance of the synchronous AutoRestParameterizedHostTestClient type. */
@ServiceClient(builder = AutoRestParameterizedHostTestClientBuilder.class)
public final class AutoRestParameterizedHostTestClient {
    private final PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     *
     * @param serviceClient the service client implementation.
     */
    AutoRestParameterizedHostTestClient(PathsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getEmpty(String accountName, RequestOptions requestOptions) {
        this.serviceClient.getEmpty(accountName, requestOptions);
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getEmptyWithResponse(String accountName, RequestOptions requestOptions, Context context) {
        return this.serviceClient.getEmptyWithResponse(accountName, requestOptions, context);
    }
}
