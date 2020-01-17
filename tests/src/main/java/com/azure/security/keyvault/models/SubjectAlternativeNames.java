package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The SubjectAlternativeNames model.
 */
@Fluent
public final class SubjectAlternativeNames {
    /*
     * Email addresses.
     */
    @JsonProperty(value = "emails")
    private List<String> emails;

    /*
     * Domain names.
     */
    @JsonProperty(value = "dns_names")
    private List<String> dnsNames;

    /*
     * User principal names.
     */
    @JsonProperty(value = "upns")
    private List<String> upns;

    /**
     * Get the emails property: Email addresses.
     * 
     * @return the emails value.
     */
    public List<String> getEmails() {
        return this.emails;
    }

    /**
     * Set the emails property: Email addresses.
     * 
     * @param emails the emails value to set.
     * @return the SubjectAlternativeNames object itself.
     */
    public SubjectAlternativeNames setEmails(List<String> emails) {
        this.emails = emails;
        return this;
    }

    /**
     * Get the dnsNames property: Domain names.
     * 
     * @return the dnsNames value.
     */
    public List<String> getDnsNames() {
        return this.dnsNames;
    }

    /**
     * Set the dnsNames property: Domain names.
     * 
     * @param dnsNames the dnsNames value to set.
     * @return the SubjectAlternativeNames object itself.
     */
    public SubjectAlternativeNames setDnsNames(List<String> dnsNames) {
        this.dnsNames = dnsNames;
        return this;
    }

    /**
     * Get the upns property: User principal names.
     * 
     * @return the upns value.
     */
    public List<String> getUpns() {
        return this.upns;
    }

    /**
     * Set the upns property: User principal names.
     * 
     * @param upns the upns value to set.
     * @return the SubjectAlternativeNames object itself.
     */
    public SubjectAlternativeNames setUpns(List<String> upns) {
        this.upns = upns;
        return this;
    }

    public void validate() {
    }
}
