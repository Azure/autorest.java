package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The X509CertificateProperties model.
 */
@Fluent
public final class X509CertificateProperties {
    /*
     * The subject name. Should be a valid X509 distinguished Name.
     */
    @JsonProperty(value = "subject")
    private String subject;

    /*
     * The enhanced key usage.
     */
    @JsonProperty(value = "ekus")
    private List<String> ekus;

    /*
     * The subject alternate names of a X509 object.
     */
    @JsonProperty(value = "sans")
    private SubjectAlternativeNames subjectAlternativeNames;

    /*
     * List of key usages.
     */
    @JsonProperty(value = "key_usage")
    private List<KeyUsageType> keyUsage;

    /*
     * The duration that the certificate is valid in months.
     */
    @JsonProperty(value = "validity_months")
    private Integer validityInMonths;

    /**
     * Get the subject property: The subject name. Should be a valid X509
     * distinguished Name.
     * 
     * @return the subject value.
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Set the subject property: The subject name. Should be a valid X509
     * distinguished Name.
     * 
     * @param subject the subject value to set.
     * @return the X509CertificateProperties object itself.
     */
    public X509CertificateProperties setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Get the ekus property: The enhanced key usage.
     * 
     * @return the ekus value.
     */
    public List<String> getEkus() {
        return this.ekus;
    }

    /**
     * Set the ekus property: The enhanced key usage.
     * 
     * @param ekus the ekus value to set.
     * @return the X509CertificateProperties object itself.
     */
    public X509CertificateProperties setEkus(List<String> ekus) {
        this.ekus = ekus;
        return this;
    }

    /**
     * Get the subjectAlternativeNames property: The subject alternate names of
     * a X509 object.
     * 
     * @return the subjectAlternativeNames value.
     */
    public SubjectAlternativeNames getSubjectAlternativeNames() {
        return this.subjectAlternativeNames;
    }

    /**
     * Set the subjectAlternativeNames property: The subject alternate names of
     * a X509 object.
     * 
     * @param subjectAlternativeNames the subjectAlternativeNames value to set.
     * @return the X509CertificateProperties object itself.
     */
    public X509CertificateProperties setSubjectAlternativeNames(SubjectAlternativeNames subjectAlternativeNames) {
        this.subjectAlternativeNames = subjectAlternativeNames;
        return this;
    }

    /**
     * Get the keyUsage property: List of key usages.
     * 
     * @return the keyUsage value.
     */
    public List<KeyUsageType> getKeyUsage() {
        return this.keyUsage;
    }

    /**
     * Set the keyUsage property: List of key usages.
     * 
     * @param keyUsage the keyUsage value to set.
     * @return the X509CertificateProperties object itself.
     */
    public X509CertificateProperties setKeyUsage(List<KeyUsageType> keyUsage) {
        this.keyUsage = keyUsage;
        return this;
    }

    /**
     * Get the validityInMonths property: The duration that the certificate is
     * valid in months.
     * 
     * @return the validityInMonths value.
     */
    public Integer getValidityInMonths() {
        return this.validityInMonths;
    }

    /**
     * Set the validityInMonths property: The duration that the certificate is
     * valid in months.
     * 
     * @param validityInMonths the validityInMonths value to set.
     * @return the X509CertificateProperties object itself.
     */
    public X509CertificateProperties setValidityInMonths(Integer validityInMonths) {
        this.validityInMonths = validityInMonths;
        return this;
    }

    public void validate() {
        if (getSubjectAlternativeNames() != null) {
            getSubjectAlternativeNames().validate();
        }
    }
}
