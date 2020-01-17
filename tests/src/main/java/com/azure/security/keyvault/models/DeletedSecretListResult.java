package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DeletedSecretListResult model.
 */
@Immutable
public final class DeletedSecretListResult {
    /*
     * A response message containing a list of the deleted secrets in the vault
     * along with a link to the next page of deleted secrets
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<DeletedSecretItem> value;

    /*
     * The URL to get the next set of deleted secrets.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of the
     * deleted secrets in the vault along with a link to the next page of
     * deleted secrets.
     * 
     * @return the value value.
     */
    public List<DeletedSecretItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of deleted
     * secrets.
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
