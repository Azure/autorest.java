package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The CertificateImportParameters model.
 */
@Fluent
public final class CertificateImportParameters {
    /*
     * Base64 encoded representation of the certificate object to import. This
     * certificate needs to contain the private key.
     */
    @JsonProperty(value = "value", required = true)
    private String value;

    /*
     * If the private key in base64EncodedCertificate is encrypted, the
     * password used for encryption.
     */
    @JsonProperty(value = "pwd")
    private String pwd;

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
     * Get the value property: Base64 encoded representation of the certificate
     * object to import. This certificate needs to contain the private key.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: Base64 encoded representation of the certificate
     * object to import. This certificate needs to contain the private key.
     * 
     * @param value the value value to set.
     * @return the CertificateImportParameters object itself.
     */
    public CertificateImportParameters setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the pwd property: If the private key in base64EncodedCertificate is
     * encrypted, the password used for encryption.
     * 
     * @return the pwd value.
     */
    public String getPwd() {
        return this.pwd;
    }

    /**
     * Set the pwd property: If the private key in base64EncodedCertificate is
     * encrypted, the password used for encryption.
     * 
     * @param pwd the pwd value to set.
     * @return the CertificateImportParameters object itself.
     */
    public CertificateImportParameters setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

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
     * @return the CertificateImportParameters object itself.
     */
    public CertificateImportParameters setCertificatePolicy(CertificatePolicy certificatePolicy) {
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
     * @return the CertificateImportParameters object itself.
     */
    public CertificateImportParameters setCertificateAttributes(CertificateAttributes certificateAttributes) {
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
     * @return the CertificateImportParameters object itself.
     */
    public CertificateImportParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model CertificateImportParameters");
        }
        if (getCertificatePolicy() != null) {
            getCertificatePolicy().validate();
        }
        if (getCertificateAttributes() != null) {
            getCertificateAttributes().validate();
        }
    }
}
