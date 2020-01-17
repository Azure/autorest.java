package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The CertificateItem model.
 */
@Fluent
public class CertificateItem {
    /*
     * Certificate identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * The certificate management attributes.
     */
    @JsonProperty(value = "attributes")
    private CertificateAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * Thumbprint of the certificate.
     */
    @JsonProperty(value = "x5t")
    private Base64Url x5t;

    /**
     * Get the id property: Certificate identifier.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Certificate identifier.
     * 
     * @param id the id value to set.
     * @return the CertificateItem object itself.
     */
    public CertificateItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the attributes property: The certificate management attributes.
     * 
     * @return the attributes value.
     */
    public CertificateAttributes getAttributes() {
        return this.attributes;
    }

    /**
     * Set the attributes property: The certificate management attributes.
     * 
     * @param attributes the attributes value to set.
     * @return the CertificateItem object itself.
     */
    public CertificateItem setAttributes(CertificateAttributes attributes) {
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
     * @return the CertificateItem object itself.
     */
    public CertificateItem setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the x5t property: Thumbprint of the certificate.
     * 
     * @return the x5t value.
     */
    public byte[] getX5t() {
        if (this.x5t == null) {
            return null;
        }
        return this.x5t.decodedBytes();
    }

    /**
     * Set the x5t property: Thumbprint of the certificate.
     * 
     * @param x5t the x5t value to set.
     * @return the CertificateItem object itself.
     */
    public CertificateItem setX5t(byte[] x5t) {
        if (x5t == null) {
            this.x5t = null;
        } else {
            this.x5t = Base64Url.encode(CoreUtils.clone(x5t));
        }
        return this;
    }

    public void validate() {
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
