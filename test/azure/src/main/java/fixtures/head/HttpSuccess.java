/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.head;

import com.microsoft.azure.v2.CloudException;
import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpSuccess.
 */
public interface HttpSuccess {
    /**
     * Return 200 status code if successful.
     *
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the boolean object if successful.
     */
    boolean head200();

    /**
     * Return 200 status code if successful.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    ServiceFuture<Boolean> head200Async(ServiceCallback<Boolean> serviceCallback);

    /**
     * Return 200 status code if successful.
     *
     * @return a Single which performs the network request upon subscription.
     */
    Single<RestResponse<Void, Boolean>> head200WithRestResponseAsync();

    /**
     * Return 200 status code if successful.
     *
     * @return a Single which performs the network request upon subscription.
     */
    Maybe<Boolean> head200Async();

    /**
     * Return 204 status code if successful.
     *
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the boolean object if successful.
     */
    boolean head204();

    /**
     * Return 204 status code if successful.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    ServiceFuture<Boolean> head204Async(ServiceCallback<Boolean> serviceCallback);

    /**
     * Return 204 status code if successful.
     *
     * @return a Single which performs the network request upon subscription.
     */
    Single<RestResponse<Void, Boolean>> head204WithRestResponseAsync();

    /**
     * Return 204 status code if successful.
     *
     * @return a Single which performs the network request upon subscription.
     */
    Maybe<Boolean> head204Async();

    /**
     * Return 404 status code if successful.
     *
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the boolean object if successful.
     */
    boolean head404();

    /**
     * Return 404 status code if successful.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    ServiceFuture<Boolean> head404Async(ServiceCallback<Boolean> serviceCallback);

    /**
     * Return 404 status code if successful.
     *
     * @return a Single which performs the network request upon subscription.
     */
    Single<RestResponse<Void, Boolean>> head404WithRestResponseAsync();

    /**
     * Return 404 status code if successful.
     *
     * @return a Single which performs the network request upon subscription.
     */
    Maybe<Boolean> head404Async();
}
