package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CertificateIssuerItem model.
 */
@Fluent
public final class CertificateIssuerItem {
    /*
     * Certificate Identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * The issuer provider.
     */
    @JsonProperty(value = "provider")
    private String provider;

    /**
     * Get the id property: Certificate Identifier.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Certificate Identifier.
     * 
     * @param id the id value to set.
     * @return the CertificateIssuerItem object itself.
     */
    public CertificateIssuerItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the provider property: The issuer provider.
     * 
     * @return the provider value.
     */
    public String getProvider() {
        return this.provider;
    }

    /**
     * Set the provider property: The issuer provider.
     * 
     * @param provider the provider value to set.
     * @return the CertificateIssuerItem object itself.
     */
    public CertificateIssuerItem setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public void validate() {
    }
}
