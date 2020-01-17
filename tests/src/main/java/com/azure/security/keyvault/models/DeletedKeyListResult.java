package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DeletedKeyListResult model.
 */
@Immutable
public final class DeletedKeyListResult {
    /*
     * A response message containing a list of deleted keys in the vault along
     * with a link to the next page of deleted keys
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<DeletedKeyItem> value;

    /*
     * The URL to get the next set of deleted keys.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of deleted
     * keys in the vault along with a link to the next page of deleted keys.
     * 
     * @return the value value.
     */
    public List<DeletedKeyItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of deleted keys.
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
