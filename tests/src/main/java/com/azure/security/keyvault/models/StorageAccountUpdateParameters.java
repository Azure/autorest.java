package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The StorageAccountUpdateParameters model.
 */
@Fluent
public final class StorageAccountUpdateParameters {
    /*
     * The current active storage account key name.
     */
    @JsonProperty(value = "activeKeyName")
    private String activeKeyName;

    /*
     * whether keyvault should manage the storage account for the user.
     */
    @JsonProperty(value = "autoRegenerateKey")
    private Boolean autoRegenerateKey;

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
     * Get the activeKeyName property: The current active storage account key
     * name.
     * 
     * @return the activeKeyName value.
     */
    public String getActiveKeyName() {
        return this.activeKeyName;
    }

    /**
     * Set the activeKeyName property: The current active storage account key
     * name.
     * 
     * @param activeKeyName the activeKeyName value to set.
     * @return the StorageAccountUpdateParameters object itself.
     */
    public StorageAccountUpdateParameters setActiveKeyName(String activeKeyName) {
        this.activeKeyName = activeKeyName;
        return this;
    }

    /**
     * Get the autoRegenerateKey property: whether keyvault should manage the
     * storage account for the user.
     * 
     * @return the autoRegenerateKey value.
     */
    public Boolean isAutoRegenerateKey() {
        return this.autoRegenerateKey;
    }

    /**
     * Set the autoRegenerateKey property: whether keyvault should manage the
     * storage account for the user.
     * 
     * @param autoRegenerateKey the autoRegenerateKey value to set.
     * @return the StorageAccountUpdateParameters object itself.
     */
    public StorageAccountUpdateParameters setAutoRegenerateKey(Boolean autoRegenerateKey) {
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
     * @return the StorageAccountUpdateParameters object itself.
     */
    public StorageAccountUpdateParameters setRegenerationPeriod(String regenerationPeriod) {
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
     * @return the StorageAccountUpdateParameters object itself.
     */
    public StorageAccountUpdateParameters setStorageAccountAttributes(StorageAccountAttributes storageAccountAttributes) {
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
     * @return the StorageAccountUpdateParameters object itself.
     */
    public StorageAccountUpdateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getStorageAccountAttributes() != null) {
            getStorageAccountAttributes().validate();
        }
    }
}
