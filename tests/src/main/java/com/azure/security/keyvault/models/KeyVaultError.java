package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyVaultError model.
 */
@Immutable
public final class KeyVaultError {
    /*
     * The key vault server error.
     */
    @JsonProperty(value = "error", access = JsonProperty.Access.WRITE_ONLY)
    private Error error;

    /**
     * Get the error property: The key vault server error.
     * 
     * @return the error value.
     */
    public Error getError() {
        return this.error;
    }

    public void validate() {
        if (getError() != null) {
            getError().validate();
        }
    }
}
