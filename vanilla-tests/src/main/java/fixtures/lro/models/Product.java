// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro.models;

import com.azure.core.annotation.Fluent;

import com.azure.core.annotation.JsonFlatten;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The Product model.
 */
@JsonFlatten
@Fluent
public class Product extends Resource {
    /*
     * The provisioningState property.
     */
    @JsonProperty(value = "properties.provisioningState")
    private String provisioningState;

    /*
     * The provisioningStateValues property.
     */
    @JsonProperty(value = "properties.provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
    private ProductPropertiesProvisioningStateValues provisioningStateValues;

    /**
     * Creates an instance of Product class.
     */
    public Product() {
    }

    /**
     * Get the provisioningState property: The provisioningState property.
     * 
     * @return the provisioningState value.
     */
    public String getProvisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioningState property: The provisioningState property.
     * 
     * @param provisioningState the provisioningState value to set.
     * @return the Product object itself.
     */
    public Product setProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
     * 
     * @return the provisioningStateValues value.
     */
    public ProductPropertiesProvisioningStateValues getProvisioningStateValues() {
        return this.provisioningStateValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product setTags(Map<String, String> tags) {
        super.setTags(tags);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product setLocation(String location) {
        super.setLocation(location);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
