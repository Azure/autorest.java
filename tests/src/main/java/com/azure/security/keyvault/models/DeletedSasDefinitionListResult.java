package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DeletedSasDefinitionListResult model.
 */
@Immutable
public final class DeletedSasDefinitionListResult {
    /*
     * A response message containing a list of the deleted SAS definitions in
     * the vault along with a link to the next page of deleted sas definitions
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<DeletedSasDefinitionItem> value;

    /*
     * The URL to get the next set of deleted SAS definitions.
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Get the value property: A response message containing a list of the
     * deleted SAS definitions in the vault along with a link to the next page
     * of deleted sas definitions.
     * 
     * @return the value value.
     */
    public List<DeletedSasDefinitionItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The URL to get the next set of deleted SAS
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
