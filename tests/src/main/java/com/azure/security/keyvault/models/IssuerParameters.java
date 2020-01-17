package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The IssuerParameters model.
 */
@Fluent
public final class IssuerParameters {
    /*
     * Name of the referenced issuer object or reserved names; for example,
     * 'Self' or 'Unknown'.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Certificate type as supported by the provider (optional); for example
     * 'OV-SSL', 'EV-SSL'
     */
    @JsonProperty(value = "cty")
    private String cty;

    /*
     * Indicates if the certificates generated under this policy should be
     * published to certificate transparency logs.
     */
    @JsonProperty(value = "cert_transparency")
    private Boolean certificateTransparency;

    /**
     * Get the name property: Name of the referenced issuer object or reserved
     * names; for example, 'Self' or 'Unknown'.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Name of the referenced issuer object or reserved
     * names; for example, 'Self' or 'Unknown'.
     * 
     * @param name the name value to set.
     * @return the IssuerParameters object itself.
     */
    public IssuerParameters setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the cty property: Certificate type as supported by the provider
     * (optional); for example 'OV-SSL', 'EV-SSL'.
     * 
     * @return the cty value.
     */
    public String getCty() {
        return this.cty;
    }

    /**
     * Set the cty property: Certificate type as supported by the provider
     * (optional); for example 'OV-SSL', 'EV-SSL'.
     * 
     * @param cty the cty value to set.
     * @return the IssuerParameters object itself.
     */
    public IssuerParameters setCty(String cty) {
        this.cty = cty;
        return this;
    }

    /**
     * Get the certificateTransparency property: Indicates if the certificates
     * generated under this policy should be published to certificate
     * transparency logs.
     * 
     * @return the certificateTransparency value.
     */
    public Boolean isCertificateTransparency() {
        return this.certificateTransparency;
    }

    /**
     * Set the certificateTransparency property: Indicates if the certificates
     * generated under this policy should be published to certificate
     * transparency logs.
     * 
     * @param certificateTransparency the certificateTransparency value to set.
     * @return the IssuerParameters object itself.
     */
    public IssuerParameters setCertificateTransparency(Boolean certificateTransparency) {
        this.certificateTransparency = certificateTransparency;
        return this;
    }

    public void validate() {
    }
}
