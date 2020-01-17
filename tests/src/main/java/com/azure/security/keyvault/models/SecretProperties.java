package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SecretProperties model.
 */
@Fluent
public final class SecretProperties {
    /*
     * The media type (MIME type).
     */
    @JsonProperty(value = "contentType")
    private String contentType;

    /**
     * Get the contentType property: The media type (MIME type).
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: The media type (MIME type).
     * 
     * @param contentType the contentType value to set.
     * @return the SecretProperties object itself.
     */
    public SecretProperties setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public void validate() {
    }
}
