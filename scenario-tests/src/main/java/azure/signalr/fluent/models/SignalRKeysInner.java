// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package azure.signalr.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** A class represents the access keys of the resource. */
@Fluent
public final class SignalRKeysInner {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(SignalRKeysInner.class);

    /*
     * The primary access key.
     */
    @JsonProperty(value = "primaryKey")
    private String primaryKey;

    /*
     * The secondary access key.
     */
    @JsonProperty(value = "secondaryKey")
    private String secondaryKey;

    /*
     * Connection string constructed via the primaryKey
     */
    @JsonProperty(value = "primaryConnectionString")
    private String primaryConnectionString;

    /*
     * Connection string constructed via the secondaryKey
     */
    @JsonProperty(value = "secondaryConnectionString")
    private String secondaryConnectionString;

    /**
     * Get the primaryKey property: The primary access key.
     *
     * @return the primaryKey value.
     */
    public String primaryKey() {
        return this.primaryKey;
    }

    /**
     * Set the primaryKey property: The primary access key.
     *
     * @param primaryKey the primaryKey value to set.
     * @return the SignalRKeysInner object itself.
     */
    public SignalRKeysInner withPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    /**
     * Get the secondaryKey property: The secondary access key.
     *
     * @return the secondaryKey value.
     */
    public String secondaryKey() {
        return this.secondaryKey;
    }

    /**
     * Set the secondaryKey property: The secondary access key.
     *
     * @param secondaryKey the secondaryKey value to set.
     * @return the SignalRKeysInner object itself.
     */
    public SignalRKeysInner withSecondaryKey(String secondaryKey) {
        this.secondaryKey = secondaryKey;
        return this;
    }

    /**
     * Get the primaryConnectionString property: Connection string constructed via the primaryKey.
     *
     * @return the primaryConnectionString value.
     */
    public String primaryConnectionString() {
        return this.primaryConnectionString;
    }

    /**
     * Set the primaryConnectionString property: Connection string constructed via the primaryKey.
     *
     * @param primaryConnectionString the primaryConnectionString value to set.
     * @return the SignalRKeysInner object itself.
     */
    public SignalRKeysInner withPrimaryConnectionString(String primaryConnectionString) {
        this.primaryConnectionString = primaryConnectionString;
        return this;
    }

    /**
     * Get the secondaryConnectionString property: Connection string constructed via the secondaryKey.
     *
     * @return the secondaryConnectionString value.
     */
    public String secondaryConnectionString() {
        return this.secondaryConnectionString;
    }

    /**
     * Set the secondaryConnectionString property: Connection string constructed via the secondaryKey.
     *
     * @param secondaryConnectionString the secondaryConnectionString value to set.
     * @return the SignalRKeysInner object itself.
     */
    public SignalRKeysInner withSecondaryConnectionString(String secondaryConnectionString) {
        this.secondaryConnectionString = secondaryConnectionString;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
