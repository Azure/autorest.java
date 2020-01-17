package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The SecretBundle model.
 */
@Fluent
public class SecretBundle {
    /*
     * The secret value.
     */
    @JsonProperty(value = "value")
    private String value;

    /*
     * The secret id.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * The content type of the secret.
     */
    @JsonProperty(value = "contentType")
    private String contentType;

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
     * If this is a secret backing a KV certificate, then this field specifies
     * the corresponding key backing the KV certificate.
     */
    @JsonProperty(value = "kid", access = JsonProperty.Access.WRITE_ONLY)
    private String kid;

    /*
     * True if the secret's lifetime is managed by key vault. If this is a
     * secret backing a certificate, then managed will be true.
     */
    @JsonProperty(value = "managed", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean managed;

    /**
     * Get the value property: The secret value.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The secret value.
     * 
     * @param value the value value to set.
     * @return the SecretBundle object itself.
     */
    public SecretBundle setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the id property: The secret id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: The secret id.
     * 
     * @param id the id value to set.
     * @return the SecretBundle object itself.
     */
    public SecretBundle setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the contentType property: The content type of the secret.
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: The content type of the secret.
     * 
     * @param contentType the contentType value to set.
     * @return the SecretBundle object itself.
     */
    public SecretBundle setContentType(String contentType) {
        this.contentType = contentType;
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
     * @return the SecretBundle object itself.
     */
    public SecretBundle setAttributes(SecretAttributes attributes) {
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
     * @return the SecretBundle object itself.
     */
    public SecretBundle setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the kid property: If this is a secret backing a KV certificate, then
     * this field specifies the corresponding key backing the KV certificate.
     * 
     * @return the kid value.
     */
    public String getKid() {
        return this.kid;
    }

    /**
     * Get the managed property: True if the secret's lifetime is managed by
     * key vault. If this is a secret backing a certificate, then managed will
     * be true.
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
