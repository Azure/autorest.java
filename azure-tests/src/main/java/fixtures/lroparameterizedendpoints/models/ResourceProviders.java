package fixtures.lroparameterizedendpoints.models;

import com.azure.core.util.Context;

/** Resource collection API of ResourceProviders. */
public interface ResourceProviders {
    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    String pollWithParameterizedEndpoints(String accountName);

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    String pollWithParameterizedEndpoints(String accountName, Context context);
}
