package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The SecretItem model.
 */
@Fluent
public class SecretItem {
    /*
     * Secret identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * The secret management attributes.
     */
    @JsonProperty(value = "attributes")
    private SecretAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * Type of the secret value such as a password.
     */
    @JsonProperty(value = "contentType")
    private String contentType;

    /*
     * True if the secret's lifetime is managed by key vault. If this is a key
     * backing a certificate, then managed will be true.
     */
    @JsonProperty(value = "managed", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean managed;

    /**
     * Get the id property: Secret identifier.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Secret identifier.
     * 
     * @param id the id value to set.
     * @return the SecretItem object itself.
     */
    public SecretItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the attributes property: The secret management attributes.
     * 
     * @return the attributes value.
     */
    public SecretAttributes getAttributes() {
        return this.attributes;
    }

    /**
     * Set the attributes property: The secret management attributes.
     * 
     * @param attributes the attributes value to set.
     * @return the SecretItem object itself.
     */
    public SecretItem setAttributes(SecretAttributes attributes) {
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
     * @return the SecretItem object itself.
     */
    public SecretItem setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the contentType property: Type of the secret value such as a
     * password.
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: Type of the secret value such as a
     * password.
     * 
     * @param contentType the contentType value to set.
     * @return the SecretItem object itself.
     */
    public SecretItem setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the managed property: True if the secret's lifetime is managed by
     * key vault. If this is a key backing a certificate, then managed will be
     * true.
     * 
     * @return the managed value.
     */
    public Boolean isManaged() {
        return this.managed;
    }

    public void validate() {
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
