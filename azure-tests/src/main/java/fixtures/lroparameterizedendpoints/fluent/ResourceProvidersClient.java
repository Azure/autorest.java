// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

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
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<String>, String> beginPollWithParameterizedEndpoints(String accountName);

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
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

    /**
     * Poll with method and client level parameters in endpoint, with a constant value.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<String>, String> beginPollWithConstantParameterizedEndpoints(String accountName);

    /**
     * Poll with method and client level parameters in endpoint, with a constant value.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<String>, String> beginPollWithConstantParameterizedEndpoints(
        String accountName, Context context);

    /**
     * Poll with method and client level parameters in endpoint, with a constant value.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String pollWithConstantParameterizedEndpoints(String accountName);

    /**
     * Poll with method and client level parameters in endpoint, with a constant value.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws fixtures.lroparameterizedendpoints.models.ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    String pollWithConstantParameterizedEndpoints(String accountName, Context context);
}
