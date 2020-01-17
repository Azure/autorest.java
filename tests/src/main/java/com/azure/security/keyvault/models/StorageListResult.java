package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The StorageListResult model.
 */
@Immutable
public final class StorageListResult {
    /*
     * A response message containing a list of storage accounts in the key
     * vault along with a link to the next page of storage accounts.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<StorageAccountItem> value;

    /*
     * The URL to get the next set of storage accounts.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of storage
     * accounts in the key vault along with a link to the next page of storage
     * accounts.
     * 
     * @return the value value.
     */
    public List<StorageAccountItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of storage
     * accounts.
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
