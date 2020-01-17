package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The StorageAccountCreateParameters model.
 */
@Fluent
public final class StorageAccountCreateParameters {
    /*
     * Storage account resource id.
     */
    @JsonProperty(value = "resourceId", required = true)
    private String resourceId;

    /*
     * Current active storage account key name.
     */
    @JsonProperty(value = "activeKeyName", required = true)
    private String activeKeyName;

    /*
     * whether keyvault should manage the storage account for the user.
     */
    @JsonProperty(value = "autoRegenerateKey", required = true)
    private boolean autoRegenerateKey;

    /*
     * The key regeneration time duration specified in ISO-8601 format.
     */
    @JsonProperty(value = "regenerationPeriod")
    private String regenerationPeriod;

    /*
     * The storage account management attributes.
     */
    @JsonProperty(value = "attributes")
    private StorageAccountAttributes storageAccountAttributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Get the resourceId property: Storage account resource id.
     * 
     * @return the resourceId value.
     */
    public String getResourceId() {
        return this.resourceId;
    }

    /**
     * Set the resourceId property: Storage account resource id.
     * 
     * @param resourceId the resourceId value to set.
     * @return the StorageAccountCreateParameters object itself.
     */
    public StorageAccountCreateParameters setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * Get the activeKeyName property: Current active storage account key name.
     * 
     * @return the activeKeyName value.
     */
    public String getActiveKeyName() {
        return this.activeKeyName;
    }

    /**
     * Set the activeKeyName property: Current active storage account key name.
     * 
     * @param activeKeyName the activeKeyName value to set.
     * @return the StorageAccountCreateParameters object itself.
     */
    public StorageAccountCreateParameters setActiveKeyName(String activeKeyName) {
        this.activeKeyName = activeKeyName;
        return this;
    }

    /**
     * Get the autoRegenerateKey property: whether keyvault should manage the
     * storage account for the user.
     * 
     * @return the autoRegenerateKey value.
     */
    public boolean isAutoRegenerateKey() {
        return this.autoRegenerateKey;
    }

    /**
     * Set the autoRegenerateKey property: whether keyvault should manage the
     * storage account for the user.
     * 
     * @param autoRegenerateKey the autoRegenerateKey value to set.
     * @return the StorageAccountCreateParameters object itself.
     */
    public StorageAccountCreateParameters setAutoRegenerateKey(boolean autoRegenerateKey) {
        this.autoRegenerateKey = autoRegenerateKey;
        return this;
    }

    /**
     * Get the regenerationPeriod property: The key regeneration time duration
     * specified in ISO-8601 format.
     * 
     * @return the regenerationPeriod value.
     */
    public String getRegenerationPeriod() {
        return this.regenerationPeriod;
    }

    /**
     * Set the regenerationPeriod property: The key regeneration time duration
     * specified in ISO-8601 format.
     * 
     * @param regenerationPeriod the regenerationPeriod value to set.
     * @return the StorageAccountCreateParameters object itself.
     */
    public StorageAccountCreateParameters setRegenerationPeriod(String regenerationPeriod) {
        this.regenerationPeriod = regenerationPeriod;
        return this;
    }

    /**
     * Get the storageAccountAttributes property: The storage account
     * management attributes.
     * 
     * @return the storageAccountAttributes value.
     */
    public StorageAccountAttributes getStorageAccountAttributes() {
        return this.storageAccountAttributes;
    }

    /**
     * Set the storageAccountAttributes property: The storage account
     * management attributes.
     * 
     * @param storageAccountAttributes the storageAccountAttributes value to
     * set.
     * @return the StorageAccountCreateParameters object itself.
     */
    public StorageAccountCreateParameters setStorageAccountAttributes(StorageAccountAttributes storageAccountAttributes) {
        this.storageAccountAttributes = storageAccountAttributes;
        return this;
    }

    /**
     * Get the tags property: Application specific metadata in the form of
     * key-value pairs.
     * 
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: Application specific metadata in the form of
     * key-value pairs.
     * 
     * @param tags the tags value to set.
     * @return the StorageAccountCreateParameters object itself.
     */
    public StorageAccountCreateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getResourceId() == null) {
            throw new IllegalArgumentException("Missing required property resourceId in model StorageAccountCreateParameters");
        }
        if (getActiveKeyName() == null) {
            throw new IllegalArgumentException("Missing required property activeKeyName in model StorageAccountCreateParameters");
        }
        if (getStorageAccountAttributes() != null) {
            getStorageAccountAttributes().validate();
        }
    }
}
