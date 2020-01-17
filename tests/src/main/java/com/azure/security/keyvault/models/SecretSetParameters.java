package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The SecretSetParameters model.
 */
@Fluent
public final class SecretSetParameters {
    /*
     * The value of the secret.
     */
    @JsonProperty(value = "value", required = true)
    private String value;

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
     * The secret management attributes.
     */
    @JsonProperty(value = "attributes")
    private SecretAttributes secretAttributes;

    /**
     * Get the value property: The value of the secret.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value of the secret.
     * 
     * @param value the value value to set.
     * @return the SecretSetParameters object itself.
     */
    public SecretSetParameters setValue(String value) {
        this.value = value;
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
     * @return the SecretSetParameters object itself.
     */
    public SecretSetParameters setTags(Map<String, String> tags) {
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
     * @return the SecretSetParameters object itself.
     */
    public SecretSetParameters setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the secretAttributes property: The secret management attributes.
     * 
     * @return the secretAttributes value.
     */
    public SecretAttributes getSecretAttributes() {
        return this.secretAttributes;
    }

    /**
     * Set the secretAttributes property: The secret management attributes.
     * 
     * @param secretAttributes the secretAttributes value to set.
     * @return the SecretSetParameters object itself.
     */
    public SecretSetParameters setSecretAttributes(SecretAttributes secretAttributes) {
        this.secretAttributes = secretAttributes;
        return this;
    }

    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model SecretSetParameters");
        }
        if (getSecretAttributes() != null) {
            getSecretAttributes().validate();
        }
    }
}
