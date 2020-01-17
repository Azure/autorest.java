package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyOperationResult model.
 */
@Immutable
public final class KeyOperationResult {
    /*
     * Key identifier
     */
    @JsonProperty(value = "kid", access = JsonProperty.Access.WRITE_ONLY)
    private String kid;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BYTEARRAY
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private Base64Url value;

    /**
     * Get the kid property: Key identifier.
     * 
     * @return the kid value.
     */
    public String getKid() {
        return this.kid;
    }

    /**
     * Get the value property: MISSING·SCHEMA-DESCRIPTION-BYTEARRAY.
     * 
     * @return the value value.
     */
    public byte[] getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.decodedBytes();
    }

    public void validate() {
    }
}
