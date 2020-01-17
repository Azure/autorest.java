package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The CertificateIssuerListResult model.
 */
@Immutable
public final class CertificateIssuerListResult {
    /*
     * A response message containing a list of certificate issuers in the key
     * vault along with a link to the next page of certificate issuers.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<CertificateIssuerItem> value;

    /*
     * The URL to get the next set of certificate issuers.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of
     * certificate issuers in the key vault along with a link to the next page
     * of certificate issuers.
     * 
     * @return the value value.
     */
    public List<CertificateIssuerItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of certificate
     * issuers.
     * 
     * @return the nextLink value.
     */
    public String getNextLink() {
        return this.nextLink;
    }

    public void validate() {
        if (getValue() != null) {
            getValue().forEach(e -> e.validate());
        }
    }
}
