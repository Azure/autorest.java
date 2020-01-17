package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CertificateRestoreParameters model.
 */
@Fluent
public final class CertificateRestoreParameters {
    /*
     * The backup blob associated with a certificate bundle.
     */
    @JsonProperty(value = "value", required = true)
    private Base64Url value;

    /**
     * Get the value property: The backup blob associated with a certificate
     * bundle.
     * 
     * @return the value value.
     */
    public byte[] getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.decodedBytes();
    }

    /**
     * Set the value property: The backup blob associated with a certificate
     * bundle.
     * 
     * @param value the value value to set.
     * @return the CertificateRestoreParameters object itself.
     */
    public CertificateRestoreParameters setValue(byte[] value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = Base64Url.encode(CoreUtils.clone(value));
        }
        return this;
    }

    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model CertificateRestoreParameters");
        }
    }
}
