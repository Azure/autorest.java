package fixtures.lroparameterizedendpoints.fluent;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.management.polling.PollResult;
import com.azure.core.util.Context;
import com.azure.core.util.polling.SyncPoller;

/** An instance of this class provides access to all the operations defined in ResourceProvidersClient. */
public interface ResourceProvidersClient {
    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    SyncPoller<PollResult<String>, String> beginPollWithParameterizedEndpoints(String accountName);

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
    @ServiceMethod(returns = ReturnType.SINGLE)
    SyncPoller<PollResult<String>, String> beginPollWithParameterizedEndpoints(String accountName, Context context);

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    String pollWithParameterizedEndpoints(String accountName, Context context);
}
