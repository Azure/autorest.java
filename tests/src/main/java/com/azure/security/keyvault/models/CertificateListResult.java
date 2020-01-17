package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The CertificateListResult model.
 */
@Immutable
public final class CertificateListResult {
    /*
     * A response message containing a list of certificates in the key vault
     * along with a link to the next page of certificates.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<CertificateItem> value;

    /*
     * The URL to get the next set of certificates.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of
     * certificates in the key vault along with a link to the next page of
     * certificates.
     * 
     * @return the value value.
     */
    public List<CertificateItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of certificates.
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
