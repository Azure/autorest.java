package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The CertificateUpdateParameters model.
 */
@Fluent
public final class CertificateUpdateParameters {
    /*
     * Management policy for a certificate.
     */
    @JsonProperty(value = "policy")
    private CertificatePolicy certificatePolicy;

    /*
     * The certificate management attributes.
     */
    @JsonProperty(value = "attributes")
    private CertificateAttributes certificateAttributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Get the certificatePolicy property: Management policy for a certificate.
     * 
     * @return the certificatePolicy value.
     */
    public CertificatePolicy getCertificatePolicy() {
        return this.certificatePolicy;
    }

    /**
     * Set the certificatePolicy property: Management policy for a certificate.
     * 
     * @param certificatePolicy the certificatePolicy value to set.
     * @return the CertificateUpdateParameters object itself.
     */
    public CertificateUpdateParameters setCertificatePolicy(CertificatePolicy certificatePolicy) {
        this.certificatePolicy = certificatePolicy;
        return this;
    }

    /**
     * Get the certificateAttributes property: The certificate management
     * attributes.
     * 
     * @return the certificateAttributes value.
     */
    public CertificateAttributes getCertificateAttributes() {
        return this.certificateAttributes;
    }

    /**
     * Set the certificateAttributes property: The certificate management
     * attributes.
     * 
     * @param certificateAttributes the certificateAttributes value to set.
     * @return the CertificateUpdateParameters object itself.
     */
    public CertificateUpdateParameters setCertificateAttributes(CertificateAttributes certificateAttributes) {
        this.certificateAttributes = certificateAttributes;
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
     * @return the CertificateUpdateParameters object itself.
     */
    public CertificateUpdateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getCertificatePolicy() != null) {
            getCertificatePolicy().validate();
        }
        if (getCertificateAttributes() != null) {
            getCertificateAttributes().validate();
        }
    }
}
