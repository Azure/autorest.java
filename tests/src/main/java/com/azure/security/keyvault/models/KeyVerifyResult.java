package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyVerifyResult model.
 */
@Immutable
public final class KeyVerifyResult {
    /*
     * True if the signature is verified, otherwise false.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean value;

    /**
     * Get the value property: True if the signature is verified, otherwise
     * false.
     * 
     * @return the value value.
     */
    public Boolean isValue() {
        return this.value;
    }

    public void validate() {
    }
}
