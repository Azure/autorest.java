package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The CertificateBundle model.
 */
@Fluent
public class CertificateBundle {
    /*
     * The certificate id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The key id.
     */
    @JsonProperty(value = "kid", access = JsonProperty.Access.WRITE_ONLY)
    private String kid;

    /*
     * The secret id.
     */
    @JsonProperty(value = "sid", access = JsonProperty.Access.WRITE_ONLY)
    private String sid;

    /*
     * Thumbprint of the certificate.
     */
    @JsonProperty(value = "x5t", access = JsonProperty.Access.WRITE_ONLY)
    private Base64Url x5t;

    /*
     * Management policy for a certificate.
     */
    @JsonProperty(value = "policy", access = JsonProperty.Access.WRITE_ONLY)
    private CertificatePolicy policy;

    /*
     * CER contents of x509 certificate.
     */
    @JsonProperty(value = "cer")
    private byte[] cer;

    /*
     * The content type of the secret.
     */
    @JsonProperty(value = "contentType")
    private String contentType;

    /*
     * The certificate management attributes.
     */
    @JsonProperty(value = "attributes")
    private CertificateAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * Get the id property: The certificate id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the kid property: The key id.
     * 
     * @return the kid value.
     */
    public String getKid() {
        return this.kid;
    }

    /**
     * Get the sid property: The secret id.
     * 
     * @return the sid value.
     */
    public String getSid() {
        return this.sid;
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
     * Get the policy property: Management policy for a certificate.
     * 
     * @return the policy value.
     */
    public CertificatePolicy getPolicy() {
        return this.policy;
    }

    /**
     * Get the cer property: CER contents of x509 certificate.
     * 
     * @return the cer value.
     */
    public byte[] getCer() {
        return CoreUtils.clone(this.cer);
    }

    /**
     * Set the cer property: CER contents of x509 certificate.
     * 
     * @param cer the cer value to set.
     * @return the CertificateBundle object itself.
     */
    public CertificateBundle setCer(byte[] cer) {
        this.cer = CoreUtils.clone(cer);
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
     * @return the CertificateBundle object itself.
     */
    public CertificateBundle setContentType(String contentType) {
        this.contentType = contentType;
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
     * @return the CertificateBundle object itself.
     */
    public CertificateBundle setAttributes(CertificateAttributes attributes) {
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
     * @return the CertificateBundle object itself.
     */
    public CertificateBundle setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getPolicy() != null) {
            getPolicy().validate();
        }
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
