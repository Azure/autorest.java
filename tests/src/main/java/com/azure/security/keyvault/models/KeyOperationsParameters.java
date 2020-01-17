package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyOperationsParameters model.
 */
@Fluent
public final class KeyOperationsParameters {
    /*
     * algorithm identifier
     */
    @JsonProperty(value = "alg", required = true)
    private JsonWebKeyEncryptionAlgorithm alg;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BYTEARRAY
     */
    @JsonProperty(value = "value", required = true)
    private Base64Url value;

    /**
     * Get the alg property: algorithm identifier.
     * 
     * @return the alg value.
     */
    public JsonWebKeyEncryptionAlgorithm getAlg() {
        return this.alg;
    }

    /**
     * Set the alg property: algorithm identifier.
     * 
     * @param alg the alg value to set.
     * @return the KeyOperationsParameters object itself.
     */
    public KeyOperationsParameters setAlg(JsonWebKeyEncryptionAlgorithm alg) {
        this.alg = alg;
        return this;
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

    /**
     * Set the value property.
     * 
     * @param value the value value to set.
     * @return the KeyOperationsParameters object itself.
     */
    public KeyOperationsParameters setValue(byte[] value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = Base64Url.encode(CoreUtils.clone(value));
        }
        return this;
    }

    public void validate() {
        if (getAlg() == null) {
            throw new IllegalArgumentException("Missing required property alg in model KeyOperationsParameters");
        }
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model KeyOperationsParameters");
        }
    }
}
