package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DeletedStorageListResult model.
 */
@Immutable
public final class DeletedStorageListResult {
    /*
     * A response message containing a list of the deleted storage accounts in
     * the vault along with a link to the next page of deleted storage accounts
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<DeletedStorageAccountItem> value;

    /*
     * The URL to get the next set of deleted storage accounts.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of the
     * deleted storage accounts in the vault along with a link to the next page
     * of deleted storage accounts.
     * 
     * @return the value value.
     */
    public List<DeletedStorageAccountItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of deleted
     * storage accounts.
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
