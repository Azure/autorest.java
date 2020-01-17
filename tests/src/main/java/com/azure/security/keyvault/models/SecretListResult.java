package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The SecretListResult model.
 */
@Immutable
public final class SecretListResult {
    /*
     * A response message containing a list of secrets in the key vault along
     * with a link to the next page of secrets.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<SecretItem> value;

    /*
     * The URL to get the next set of secrets.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of secrets
     * in the key vault along with a link to the next page of secrets.
     * 
     * @return the value value.
     */
    public List<SecretItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of secrets.
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
