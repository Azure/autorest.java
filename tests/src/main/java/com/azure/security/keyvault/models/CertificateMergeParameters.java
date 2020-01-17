package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * The CertificateMergeParameters model.
 */
@Fluent
public final class CertificateMergeParameters {
    /*
     * The certificate or the certificate chain to merge.
     */
    @JsonProperty(value = "x5c", required = true)
    private List<byte[]> x5c;

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
     * Get the x5c property: The certificate or the certificate chain to merge.
     * 
     * @return the x5c value.
     */
    public List<byte[]> getX5c() {
        return this.x5c;
    }

    /**
     * Set the x5c property: The certificate or the certificate chain to merge.
     * 
     * @param x5c the x5c value to set.
     * @return the CertificateMergeParameters object itself.
     */
    public CertificateMergeParameters setX5c(List<byte[]> x5c) {
        this.x5c = x5c;
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
     * @return the CertificateMergeParameters object itself.
     */
    public CertificateMergeParameters setCertificateAttributes(CertificateAttributes certificateAttributes) {
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
     * @return the CertificateMergeParameters object itself.
     */
    public CertificateMergeParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getX5c() == null) {
            throw new IllegalArgumentException("Missing required property x5c in model CertificateMergeParameters");
        }
        if (getCertificateAttributes() != null) {
            getCertificateAttributes().validate();
        }
    }
}
