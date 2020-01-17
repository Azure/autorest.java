package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyVerifyParameters model.
 */
@Fluent
public final class KeyVerifyParameters {
    /*
     * The signing/verification algorithm identifier. For more information on
     * possible algorithm types, see JsonWebKeySignatureAlgorithm.
     */
    @JsonProperty(value = "alg", required = true)
    private JsonWebKeySignatureAlgorithm alg;

    /*
     * The digest used for signing.
     */
    @JsonProperty(value = "digest", required = true)
    private Base64Url digest;

    /*
     * The signature to be verified.
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
     * @return the KeyVerifyParameters object itself.
     */
    public KeyVerifyParameters setAlg(JsonWebKeySignatureAlgorithm alg) {
        this.alg = alg;
        return this;
    }

    /**
     * Get the digest property: The digest used for signing.
     * 
     * @return the digest value.
     */
    public byte[] getDigest() {
        if (this.digest == null) {
            return null;
        }
        return this.digest.decodedBytes();
    }

    /**
     * Set the digest property: The digest used for signing.
     * 
     * @param digest the digest value to set.
     * @return the KeyVerifyParameters object itself.
     */
    public KeyVerifyParameters setDigest(byte[] digest) {
        if (digest == null) {
            this.digest = null;
        } else {
            this.digest = Base64Url.encode(CoreUtils.clone(digest));
        }
        return this;
    }

    /**
     * Get the value property: The signature to be verified.
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
     * Set the value property: The signature to be verified.
     * 
     * @param value the value value to set.
     * @return the KeyVerifyParameters object itself.
     */
    public KeyVerifyParameters setValue(byte[] value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = Base64Url.encode(CoreUtils.clone(value));
        }
        return this;
    }

    public void validate() {
        if (getAlg() == null) {
            throw new IllegalArgumentException("Missing required property alg in model KeyVerifyParameters");
        }
        if (getDigest() == null) {
            throw new IllegalArgumentException("Missing required property digest in model KeyVerifyParameters");
        }
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model KeyVerifyParameters");
        }
    }
}
