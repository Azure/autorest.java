package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The KeyBundle model.
 */
@Fluent
public class KeyBundle {
    /*
     * As of http://tools.ietf.org/html/draft-ietf-jose-json-web-key-18
     */
    @JsonProperty(value = "key")
    private JsonWebKey key;

    /*
     * The attributes of a key managed by the key vault service.
     */
    @JsonProperty(value = "attributes")
    private KeyAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * True if the key's lifetime is managed by key vault. If this is a key
     * backing a certificate, then managed will be true.
     */
    @JsonProperty(value = "managed", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean managed;

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
     * @return the KeyBundle object itself.
     */
    public KeyBundle setKey(JsonWebKey key) {
        this.key = key;
        return this;
    }

    /**
     * Get the attributes property: The attributes of a key managed by the key
     * vault service.
     * 
     * @return the attributes value.
     */
    public KeyAttributes getAttributes() {
        return this.attributes;
    }

    /**
     * Set the attributes property: The attributes of a key managed by the key
     * vault service.
     * 
     * @param attributes the attributes value to set.
     * @return the KeyBundle object itself.
     */
    public KeyBundle setAttributes(KeyAttributes attributes) {
        this.attributes = attributes;
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
     * @return the KeyBundle object itself.
     */
    public KeyBundle setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the managed property: True if the key's lifetime is managed by key
     * vault. If this is a key backing a certificate, then managed will be
     * true.
     * 
     * @return the managed value.
     */
    public Boolean isManaged() {
        return this.managed;
    }

    public void validate() {
        if (getKey() != null) {
            getKey().validate();
        }
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
