package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * The KeyUpdateParameters model.
 */
@Fluent
public final class KeyUpdateParameters {
    /*
     * Json web key operations. For more information on possible key
     * operations, see JsonWebKeyOperation.
     */
    @JsonProperty(value = "key_ops")
    private List<JsonWebKeyOperation> keyOps;

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
     * Get the keyOps property: Json web key operations. For more information
     * on possible key operations, see JsonWebKeyOperation.
     * 
     * @return the keyOps value.
     */
    public List<JsonWebKeyOperation> getKeyOps() {
        return this.keyOps;
    }

    /**
     * Set the keyOps property: Json web key operations. For more information
     * on possible key operations, see JsonWebKeyOperation.
     * 
     * @param keyOps the keyOps value to set.
     * @return the KeyUpdateParameters object itself.
     */
    public KeyUpdateParameters setKeyOps(List<JsonWebKeyOperation> keyOps) {
        this.keyOps = keyOps;
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
     * @return the KeyUpdateParameters object itself.
     */
    public KeyUpdateParameters setKeyAttributes(KeyAttributes keyAttributes) {
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
     * @return the KeyUpdateParameters object itself.
     */
    public KeyUpdateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getKeyAttributes() != null) {
            getKeyAttributes().validate();
        }
    }
}
