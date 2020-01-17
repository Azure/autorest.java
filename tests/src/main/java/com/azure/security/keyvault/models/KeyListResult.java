package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The KeyListResult model.
 */
@Immutable
public final class KeyListResult {
    /*
     * A response message containing a list of keys in the key vault along with
     * a link to the next page of keys.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<KeyItem> value;

    /*
     * The URL to get the next set of keys.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of keys in
     * the key vault along with a link to the next page of keys.
     * 
     * @return the value value.
     */
    public List<KeyItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of keys.
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
