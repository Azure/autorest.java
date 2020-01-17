package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DeletedCertificateListResult model.
 */
@Immutable
public final class DeletedCertificateListResult {
    /*
     * A response message containing a list of deleted certificates in the
     * vault along with a link to the next page of deleted certificates
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<DeletedCertificateItem> value;

    /*
     * The URL to get the next set of deleted certificates.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of deleted
     * certificates in the vault along with a link to the next page of deleted
     * certificates.
     * 
     * @return the value value.
     */
    public List<DeletedCertificateItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of deleted
     * certificates.
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
