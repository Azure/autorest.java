// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.armresourceprovider.fluent;

import com.azure.core.http.HttpPipeline;
import java.time.Duration;

/**
 * The interface for ArmResourceProviderClient class.
 */
public interface ArmResourceProviderClient {

    /**
     * Gets Server parameter.
     *
     * @return the endpoint value.
     */
    String getEndpoint();

    /**
     * Gets Version parameter.
     *
     * @return the apiVersion value.
     */
    String getApiVersion();

    /**
     * Gets The ID of the target subscription.
     *
     * @return the subscriptionId value.
     */
    String getSubscriptionId();

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    HttpPipeline getHttpPipeline();

    /**
     * Gets The default poll interval for long-running operation.
     *
     * @return the defaultPollInterval value.
     */
    Duration getDefaultPollInterval();

    /**
     * Gets the ChildResourcesInterfacesClient object to access its operations.
     *
     * @return the ChildResourcesInterfacesClient object.
     */
    ChildResourcesInterfacesClient getChildResourcesInterfaces();

    /**
     * Gets the TopLevelArmResourceInterfacesClient object to access its operations.
     *
     * @return the TopLevelArmResourceInterfacesClient object.
     */
    TopLevelArmResourceInterfacesClient getTopLevelArmResourceInterfaces();

    /**
     * Gets the CustomTemplateResourceInterfacesClient object to access its operations.
     *
     * @return the CustomTemplateResourceInterfacesClient object.
     */
    CustomTemplateResourceInterfacesClient getCustomTemplateResourceInterfaces();

    /**
     * Gets the OperationsClient object to access its operations.
     *
     * @return the OperationsClient object.
     */
    OperationsClient getOperations();
}
