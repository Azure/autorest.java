package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The CertificatePolicy model.
 */
@Fluent
public final class CertificatePolicy {
    /*
     * The certificate id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * Properties of the key pair backing a certificate.
     */
    @JsonProperty(value = "key_props")
    private KeyProperties keyProperties;

    /*
     * Properties of the key backing a certificate.
     */
    @JsonProperty(value = "secret_props")
    private SecretProperties secretProperties;

    /*
     * Properties of the X509 component of a certificate.
     */
    @JsonProperty(value = "x509_props")
    private X509CertificateProperties x509CertificateProperties;

    /*
     * Actions that will be performed by Key Vault over the lifetime of a
     * certificate.
     */
    @JsonProperty(value = "lifetime_actions")
    private List<LifetimeAction> lifetimeActions;

    /*
     * Parameters for the issuer of the X509 component of a certificate.
     */
    @JsonProperty(value = "issuer")
    private IssuerParameters issuerParameters;

    /*
     * The certificate management attributes.
     */
    @JsonProperty(value = "attributes")
    private CertificateAttributes attributes;

    /**
     * Get the id property: The certificate id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the keyProperties property: Properties of the key pair backing a
     * certificate.
     * 
     * @return the keyProperties value.
     */
    public KeyProperties getKeyProperties() {
        return this.keyProperties;
    }

    /**
     * Set the keyProperties property: Properties of the key pair backing a
     * certificate.
     * 
     * @param keyProperties the keyProperties value to set.
     * @return the CertificatePolicy object itself.
     */
    public CertificatePolicy setKeyProperties(KeyProperties keyProperties) {
        this.keyProperties = keyProperties;
        return this;
    }

    /**
     * Get the secretProperties property: Properties of the key backing a
     * certificate.
     * 
     * @return the secretProperties value.
     */
    public SecretProperties getSecretProperties() {
        return this.secretProperties;
    }

    /**
     * Set the secretProperties property: Properties of the key backing a
     * certificate.
     * 
     * @param secretProperties the secretProperties value to set.
     * @return the CertificatePolicy object itself.
     */
    public CertificatePolicy setSecretProperties(SecretProperties secretProperties) {
        this.secretProperties = secretProperties;
        return this;
    }

    /**
     * Get the x509CertificateProperties property: Properties of the X509
     * component of a certificate.
     * 
     * @return the x509CertificateProperties value.
     */
    public X509CertificateProperties getX509CertificateProperties() {
        return this.x509CertificateProperties;
    }

    /**
     * Set the x509CertificateProperties property: Properties of the X509
     * component of a certificate.
     * 
     * @param x509CertificateProperties the x509CertificateProperties value to
     * set.
     * @return the CertificatePolicy object itself.
     */
    public CertificatePolicy setX509CertificateProperties(X509CertificateProperties x509CertificateProperties) {
        this.x509CertificateProperties = x509CertificateProperties;
        return this;
    }

    /**
     * Get the lifetimeActions property: Actions that will be performed by Key
     * Vault over the lifetime of a certificate.
     * 
     * @return the lifetimeActions value.
     */
    public List<LifetimeAction> getLifetimeActions() {
        return this.lifetimeActions;
    }

    /**
     * Set the lifetimeActions property: Actions that will be performed by Key
     * Vault over the lifetime of a certificate.
     * 
     * @param lifetimeActions the lifetimeActions value to set.
     * @return the CertificatePolicy object itself.
     */
    public CertificatePolicy setLifetimeActions(List<LifetimeAction> lifetimeActions) {
        this.lifetimeActions = lifetimeActions;
        return this;
    }

    /**
     * Get the issuerParameters property: Parameters for the issuer of the X509
     * component of a certificate.
     * 
     * @return the issuerParameters value.
     */
    public IssuerParameters getIssuerParameters() {
        return this.issuerParameters;
    }

    /**
     * Set the issuerParameters property: Parameters for the issuer of the X509
     * component of a certificate.
     * 
     * @param issuerParameters the issuerParameters value to set.
     * @return the CertificatePolicy object itself.
     */
    public CertificatePolicy setIssuerParameters(IssuerParameters issuerParameters) {
        this.issuerParameters = issuerParameters;
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
     * @return the CertificatePolicy object itself.
     */
    public CertificatePolicy setAttributes(CertificateAttributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public void validate() {
        if (getKeyProperties() != null) {
            getKeyProperties().validate();
        }
        if (getSecretProperties() != null) {
            getSecretProperties().validate();
        }
        if (getX509CertificateProperties() != null) {
            getX509CertificateProperties().validate();
        }
        if (getLifetimeActions() != null) {
            getLifetimeActions().forEach(e -> e.validate());
        }
        if (getIssuerParameters() != null) {
            getIssuerParameters().validate();
        }
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
