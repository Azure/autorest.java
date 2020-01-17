package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The SasDefinitionItem model.
 */
@Immutable
public class SasDefinitionItem {
    /*
     * The storage SAS identifier.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The storage account SAS definition secret id.
     */
    @JsonProperty(value = "sid", access = JsonProperty.Access.WRITE_ONLY)
    private String sid;

    /*
     * The SAS definition management attributes.
     */
    @JsonProperty(value = "attributes", access = JsonProperty.Access.WRITE_ONLY)
    private SasDefinitionAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags", access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, String> tags;

    /**
     * Get the id property: The storage SAS identifier.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the sid property: The storage account SAS definition secret id.
     * 
     * @return the sid value.
     */
    public String getSid() {
        return this.sid;
    }

    /**
     * Get the attributes property: The SAS definition management attributes.
     * 
     * @return the attributes value.
     */
    public SasDefinitionAttributes getAttributes() {
        return this.attributes;
    }

    /**
     * Get the tags property: Application specific metadata in the form of
     * key-value pairs.
     * 
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    public void validate() {
        if (getAttributes() != null) {
            getAttributes().validate();
        }
    }
}
