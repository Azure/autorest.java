package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CertificateIssuerSetParameters model.
 */
@Fluent
public final class CertificateIssuerSetParameters {
    /*
     * The issuer provider.
     */
    @JsonProperty(value = "provider", required = true)
    private String provider;

    /*
     * The credentials to be used for the certificate issuer.
     */
    @JsonProperty(value = "credentials")
    private IssuerCredentials credentials;

    /*
     * Details of the organization of the certificate issuer.
     */
    @JsonProperty(value = "org_details")
    private OrganizationDetails organizationDetails;

    /*
     * The attributes of an issuer managed by the Key Vault service.
     */
    @JsonProperty(value = "attributes")
    private IssuerAttributes attributes;

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
     * @return the CertificateIssuerSetParameters object itself.
     */
    public CertificateIssuerSetParameters setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    /**
     * Get the credentials property: The credentials to be used for the
     * certificate issuer.
     * 
     * @return the credentials value.
     */
    public IssuerCredentials getCredentials() {
        return this.credentials;
    }

    /**
     * Set the credentials property: The credentials to be used for the
     * certificate issuer.
     * 
     * @param credentials the credentials value to set.
     * @return the CertificateIssuerSetParameters object itself.
     */
    public CertificateIssuerSetParameters setCredentials(IssuerCredentials credentials) {
        this.credentials = credentials;
        return this;
    }

    /**
     * Get the organizationDetails property: Details of the organization of the
     * certificate issuer.
     * 
     * @return the organizationDetails value.
     */
    public OrganizationDetails getOrganizationDetails() {
        return this.organizationDetails;
    }

    /**
     * Set the organizationDetails property: Details of the organization of the
     * certificate issuer.
     * 
     * @param organizationDetails the organizationDetails value to set.
     * @return the CertificateIssuerSetParameters object itself.
     */
    public CertificateIssuerSetParameters setOrganizationDetails(OrganizationDetails organizationDetails) {
        this.organizationDetails = organizationDetails;
        return this;
    }

    /**
     * Get the attributes property: The attributes of an issuer managed by the
     * Key Vault service.
     * 
     * @return the attributes value.
     */
    public IssuerAttributes getAttributes() {
        return this.attributes;
    }

    /**
     * Set the attributes property: The attributes of an issuer managed by the
     * Key Vault service.
     * 
     * @param attributes the attributes value to set.
     * @return the CertificateIssuerSetParameters object itself.
     */
    public CertificateIssuerSetParameters setAttributes(IssuerAttributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public void validate() {
        if (getProvider() == null) {
            throw new IllegalArgumentException("Missing required property provider in model CertificateIssuerSetParameters");
        }
        if (getCredentials() != null) {
            getCredentials().validate();
        }
        if (getOrganizationDetails() != null) {
            getOrganizationDetails().validate();
        }
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
