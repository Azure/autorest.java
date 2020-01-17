package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The KeyImportParameters model.
 */
@Fluent
public final class KeyImportParameters {
    /*
     * Whether to import as a hardware key (HSM) or software key.
     */
    @JsonProperty(value = "Hsm")
    private Boolean hsm;

    /*
     * As of http://tools.ietf.org/html/draft-ietf-jose-json-web-key-18
     */
    @JsonProperty(value = "key", required = true)
    private JsonWebKey key;

    /*
     * The attributes of a key managed by the key vault service.
     */
    @JsonProperty(value = "attributes")
    private KeyAttributes keyAttributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Get the hsm property: Whether to import as a hardware key (HSM) or
     * software key.
     * 
     * @return the hsm value.
     */
    public Boolean isHsm() {
        return this.hsm;
    }

    /**
     * Set the hsm property: Whether to import as a hardware key (HSM) or
     * software key.
     * 
     * @param hsm the hsm value to set.
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters setHsm(Boolean hsm) {
        this.hsm = hsm;
        return this;
    }

    /**
     * Get the key property: As of
     * http://tools.ietf.org/html/draft-ietf-jose-json-web-key-18.
     * 
     * @return the key value.
     */
    public JsonWebKey getKey() {
        return this.key;
    }

    /**
     * Set the key property: As of
     * http://tools.ietf.org/html/draft-ietf-jose-json-web-key-18.
     * 
     * @param key the key value to set.
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters setKey(JsonWebKey key) {
        this.key = key;
        return this;
    }

    /**
     * Get the keyAttributes property: The attributes of a key managed by the
     * key vault service.
     * 
     * @return the keyAttributes value.
     */
    public KeyAttributes getKeyAttributes() {
        return this.keyAttributes;
    }

    /**
     * Set the keyAttributes property: The attributes of a key managed by the
     * key vault service.
     * 
     * @param keyAttributes the keyAttributes value to set.
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters setKeyAttributes(KeyAttributes keyAttributes) {
        this.keyAttributes = keyAttributes;
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
     * @return the KeyImportParameters object itself.
     */
    public KeyImportParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getKey() == null) {
            throw new IllegalArgumentException("Missing required property key in model KeyImportParameters");
        } else {
            getKey().validate();
        }
        if (getKeyAttributes() != null) {
            getKeyAttributes().validate();
        }
    }
}
