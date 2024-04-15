// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.management.Resource;
import com.cadl.armresourceprovider.models.ProvisioningState;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * Concrete tracked resource types can be created by aliasing this type using a specific property type.
 */
@Fluent
public final class TopLevelArmResourceInner extends Resource {
    /*
     * The resource-specific properties for this resource.
     */
    @JsonProperty(value = "properties")
    private TopLevelArmResourceProperties innerProperties;

    /**
     * Creates an instance of TopLevelArmResourceInner class.
     */
    public TopLevelArmResourceInner() {
    }

    /**
     * Get the innerProperties property: The resource-specific properties for this resource.
     * 
     * @return the innerProperties value.
     */
    private TopLevelArmResourceProperties innerProperties() {
        return this.innerProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopLevelArmResourceInner withLocation(String location) {
        super.withLocation(location);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopLevelArmResourceInner withTags(Map<String, String> tags) {
        super.withTags(tags);
        return this;
    }

    /**
     * Get the configurationEndpoints property: Configuration Endpoints.
     * 
     * @return the configurationEndpoints value.
     */
    public List<String> configurationEndpoints() {
        return this.innerProperties() == null ? null : this.innerProperties().configurationEndpoints();
    }

    /**
     * Get the username property: The userName property.
     * 
     * @return the username value.
     */
    public String username() {
        return this.innerProperties() == null ? null : this.innerProperties().username();
    }

    /**
     * Set the username property: The userName property.
     * 
     * @param username the username value to set.
     * @return the TopLevelArmResourceInner object itself.
     */
    public TopLevelArmResourceInner withUsername(String username) {
        if (this.innerProperties() == null) {
            this.innerProperties = new TopLevelArmResourceProperties();
        }
        this.innerProperties().withUsername(username);
        return this;
    }

    /**
     * Get the userNames property: The userNames property.
     * 
     * @return the userNames value.
     */
    public String userNames() {
        return this.innerProperties() == null ? null : this.innerProperties().userNames();
    }

    /**
     * Set the userNames property: The userNames property.
     * 
     * @param userNames the userNames value to set.
     * @return the TopLevelArmResourceInner object itself.
     */
    public TopLevelArmResourceInner withUserNames(String userNames) {
        if (this.innerProperties() == null) {
            this.innerProperties = new TopLevelArmResourceProperties();
        }
        this.innerProperties().withUserNames(userNames);
        return this;
    }

    /**
     * Get the accuserName property: The accuserName property.
     * 
     * @return the accuserName value.
     */
    public String accuserName() {
        return this.innerProperties() == null ? null : this.innerProperties().accuserName();
    }

    /**
     * Set the accuserName property: The accuserName property.
     * 
     * @param accuserName the accuserName value to set.
     * @return the TopLevelArmResourceInner object itself.
     */
    public TopLevelArmResourceInner withAccuserName(String accuserName) {
        if (this.innerProperties() == null) {
            this.innerProperties = new TopLevelArmResourceProperties();
        }
        this.innerProperties().withAccuserName(accuserName);
        return this;
    }

    /**
     * Get the startTimestamp property: The startTimeStamp property.
     * 
     * @return the startTimestamp value.
     */
    public OffsetDateTime startTimestamp() {
        return this.innerProperties() == null ? null : this.innerProperties().startTimestamp();
    }

    /**
     * Set the startTimestamp property: The startTimeStamp property.
     * 
     * @param startTimestamp the startTimestamp value to set.
     * @return the TopLevelArmResourceInner object itself.
     */
    public TopLevelArmResourceInner withStartTimestamp(OffsetDateTime startTimestamp) {
        if (this.innerProperties() == null) {
            this.innerProperties = new TopLevelArmResourceProperties();
        }
        this.innerProperties().withStartTimestamp(startTimestamp);
        return this;
    }

    /**
     * Get the provisioningState property: The status of the last operation.
     * 
     * @return the provisioningState value.
     */
    public ProvisioningState provisioningState() {
        return this.innerProperties() == null ? null : this.innerProperties().provisioningState();
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (innerProperties() != null) {
            innerProperties().validate();
        }
    }
}
