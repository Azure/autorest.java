// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pool of backend IP addresses.
 */
@Fluent
public final class BackendAddressPool {
    /*
     * Properties of load balancer backend address pool.
     */
    @Generated
    @JsonProperty(value = "properties")
    private BackendAddressPoolPropertiesFormat properties;

    /**
     * Creates an instance of BackendAddressPool class.
     */
    @Generated
    public BackendAddressPool() {
    }

    /**
     * Get the properties property: Properties of load balancer backend address pool.
     * 
     * @return the properties value.
     */
    @Generated
    public BackendAddressPoolPropertiesFormat getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of load balancer backend address pool.
     * 
     * @param properties the properties value to set.
     * @return the BackendAddressPool object itself.
     */
    @Generated
    public BackendAddressPool setProperties(BackendAddressPoolPropertiesFormat properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProperties() != null) {
            getProperties().validate();
        }
    }
}
