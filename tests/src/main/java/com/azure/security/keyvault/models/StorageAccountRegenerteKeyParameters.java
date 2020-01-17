package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The StorageAccountRegenerteKeyParameters model.
 */
@Fluent
public final class StorageAccountRegenerteKeyParameters {
    /*
     * The storage account key name.
     */
    @JsonProperty(value = "keyName", required = true)
    private String keyName;

    /**
     * Get the keyName property: The storage account key name.
     * 
     * @return the keyName value.
     */
    public String getKeyName() {
        return this.keyName;
    }

    /**
     * Set the keyName property: The storage account key name.
     * 
     * @param keyName the keyName value to set.
     * @return the StorageAccountRegenerteKeyParameters object itself.
     */
    public StorageAccountRegenerteKeyParameters setKeyName(String keyName) {
        this.keyName = keyName;
        return this;
    }

    public void validate() {
        if (getKeyName() == null) {
            throw new IllegalArgumentException("Missing required property keyName in model StorageAccountRegenerteKeyParameters");
        }
    }
}
