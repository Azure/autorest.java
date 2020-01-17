package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeySignParameters model.
 */
@Fluent
public final class KeySignParameters {
    /*
     * The signing/verification algorithm identifier. For more information on
     * possible algorithm types, see JsonWebKeySignatureAlgorithm.
     */
    @JsonProperty(value = "alg", required = true)
    private JsonWebKeySignatureAlgorithm alg;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BYTEARRAY
     */
    @JsonProperty(value = "value", required = true)
    private Base64Url value;

    /**
     * Get the alg property: The signing/verification algorithm identifier. For
     * more information on possible algorithm types, see
     * JsonWebKeySignatureAlgorithm.
     * 
     * @return the alg value.
     */
    public JsonWebKeySignatureAlgorithm getAlg() {
        return this.alg;
    }

    /**
     * Set the alg property: The signing/verification algorithm identifier. For
     * more information on possible algorithm types, see
     * JsonWebKeySignatureAlgorithm.
     * 
     * @param alg the alg value to set.
     * @return the KeySignParameters object itself.
     */
    public KeySignParameters setAlg(JsonWebKeySignatureAlgorithm alg) {
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
     * @return the KeySignParameters object itself.
     */
    public KeySignParameters setValue(byte[] value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = Base64Url.encode(CoreUtils.clone(value));
        }
        return this;
    }

    public void validate() {
        if (getAlg() == null) {
            throw new IllegalArgumentException("Missing required property alg in model KeySignParameters");
        }
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model KeySignParameters");
        }
    }
}
