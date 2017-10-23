/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.parameterflattening.implementation;

import com.microsoft.rest.RestProxy;
import com.microsoft.rest.RestResponse;
import fixtures.parameterflattening.AvailabilitySets;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.annotations.BodyParam;
import com.microsoft.rest.annotations.ExpectedResponses;
import com.microsoft.rest.annotations.Headers;
import com.microsoft.rest.annotations.Host;
import com.microsoft.rest.annotations.PATCH;
import com.microsoft.rest.annotations.PathParam;
import com.microsoft.rest.annotations.UnexpectedResponseExceptionType;
import com.microsoft.rest.http.HttpClient;
import com.microsoft.rest.RestException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.Validator;
import fixtures.parameterflattening.models.AvailabilitySetUpdateParameters;
import java.io.IOException;
import java.util.Map;
import rx.functions.Func1;
import rx.Observable;
import rx.Single;

/**
 * An instance of this class provides access to all the operations defined
 * in AvailabilitySets.
 */
public class AvailabilitySetsImpl implements AvailabilitySets {
    /** The RestProxy service to perform REST calls. */
    private AvailabilitySetsService service;
    /** The service client containing this operation class. */
    private AutoRestParameterFlatteningImpl client;

    /**
     * Initializes an instance of AvailabilitySets.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public AvailabilitySetsImpl(AutoRestParameterFlatteningImpl client) {
        this.service = RestProxy.create(AvailabilitySetsService.class, client.restClient().baseURL(), client.httpClient(), client.serializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AvailabilitySets to be
     * used by RestProxy to perform REST calls.
    */
    @Host("http://localhost")
    interface AvailabilitySetsService {
        @Headers({ "x-ms-logging-context: fixtures.parameterflattening.AvailabilitySets update" })
        @PATCH("parameterFlattening/{resourceGroupName}/{availabilitySetName}")
        @ExpectedResponses({200})
        Single<RestResponse<Void, Void>> update(@PathParam("resourceGroupName") String resourceGroupName, @PathParam("availabilitySetName") String avset, @BodyParam("application/json; charset=utf-8") AvailabilitySetUpdateParameters tags);

    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param tags A set of tags. A description about the set of tags.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the void object if successful.
     */
    public void update(String resourceGroupName, String avset, Map<String, String> tags) {
        updateAsync(resourceGroupName, avset, tags).toBlocking().value();
    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param tags A set of tags. A description about the set of tags.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> updateAsync(String resourceGroupName, String avset, Map<String, String> tags, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(updateAsync(resourceGroupName, avset, tags), serviceCallback);
    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param tags A set of tags. A description about the set of tags.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Void> object
     */
    public Single<RestResponse<Void, Void>> updateWithRestResponseAsync(String resourceGroupName, String avset, Map<String, String> tags) {
        if (resourceGroupName == null) {
            throw new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null.");
        }
        if (avset == null) {
            throw new IllegalArgumentException("Parameter avset is required and cannot be null.");
        }
        if (tags == null) {
            throw new IllegalArgumentException("Parameter tags is required and cannot be null.");
        }
        Validator.validate(tags);
    AvailabilitySetUpdateParameters tags1 = new AvailabilitySetUpdateParameters();
    tags1.withTags(tags);
        return service.update(resourceGroupName, avset, tags1);
    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param tags A set of tags. A description about the set of tags.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Void> object
     */
    public Single<Void> updateAsync(String resourceGroupName, String avset, Map<String, String> tags) {
        return updateWithRestResponseAsync(resourceGroupName, avset, tags)
            .map(new Func1<RestResponse<Void, Void>, Void>() { public Void call(RestResponse<Void, Void> restResponse) { return restResponse.body(); } });
        }


}
