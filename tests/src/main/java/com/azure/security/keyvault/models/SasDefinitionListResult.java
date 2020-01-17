package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The SasDefinitionListResult model.
 */
@Immutable
public final class SasDefinitionListResult {
    /*
     * A response message containing a list of SAS definitions along with a
     * link to the next page of SAS definitions.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<SasDefinitionItem> value;

    /*
     * The URL to get the next set of SAS definitions.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of SAS
     * definitions along with a link to the next page of SAS definitions.
     * 
     * @return the value value.
     */
    public List<SasDefinitionItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of SAS
     * definitions.
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
