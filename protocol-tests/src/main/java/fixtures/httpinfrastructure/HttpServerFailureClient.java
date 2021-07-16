package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.httpinfrastructure.implementation.HttpServerFailuresImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class HttpServerFailureClient {
    private final HttpServerFailuresImpl serviceClient;

    /**
     * Initializes an instance of HttpServerFailures client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpServerFailureClient(HttpServerFailuresImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head501(RequestOptions requestOptions) {
        this.serviceClient.head501(requestOptions);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head501WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head501WithResponse(requestOptions, context);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get501(RequestOptions requestOptions) {
        this.serviceClient.get501(requestOptions);
    }

    /** Return 501 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get501WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get501WithResponse(requestOptions, context);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post505(RequestOptions requestOptions) {
        this.serviceClient.post505(requestOptions);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post505WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post505WithResponse(requestOptions, context);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete505(RequestOptions requestOptions) {
        this.serviceClient.delete505(requestOptions);
    }

    /** Return 505 status code - should be represented in the client as an error. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete505WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete505WithResponse(requestOptions, context);
    }
}
