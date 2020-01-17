package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The SasDefinitionBundle model.
 */
@Immutable
public class SasDefinitionBundle {
    /*
     * The SAS definition id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * Storage account SAS definition secret id.
     */
    @JsonProperty(value = "sid", access = JsonProperty.Access.WRITE_ONLY)
    private String sid;

    /*
     * The SAS definition token template signed with an arbitrary key.  Tokens
     * created according to the SAS definition will have the same properties as
     * the template.
     */
    @JsonProperty(value = "templateUri", access = JsonProperty.Access.WRITE_ONLY)
    private String templateUri;

    /*
     * The type of SAS token the SAS definition will create.
     */
    @JsonProperty(value = "sasType", access = JsonProperty.Access.WRITE_ONLY)
    private SasTokenType sasType;

    /*
     * The validity period of SAS tokens created according to the SAS
     * definition.
     */
    @JsonProperty(value = "validityPeriod", access = JsonProperty.Access.WRITE_ONLY)
    private String validityPeriod;

    /*
     * The SAS definition management attributes.
     */
    @JsonProperty(value = "attributes", access = JsonProperty.Access.WRITE_ONLY)
    private SasDefinitionAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs
     */
    @JsonProperty(value = "tags", access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, String> tags;

    /**
     * Get the id property: The SAS definition id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the sid property: Storage account SAS definition secret id.
     * 
     * @return the sid value.
     */
    public String getSid() {
        return this.sid;
    }

    /**
     * Get the templateUri property: The SAS definition token template signed
     * with an arbitrary key.  Tokens created according to the SAS definition
     * will have the same properties as the template.
     * 
     * @return the templateUri value.
     */
    public String getTemplateUri() {
        return this.templateUri;
    }

    /**
     * Get the sasType property: The type of SAS token the SAS definition will
     * create.
     * 
     * @return the sasType value.
     */
    public SasTokenType getSasType() {
        return this.sasType;
    }

    /**
     * Get the validityPeriod property: The validity period of SAS tokens
     * created according to the SAS definition.
     * 
     * @return the validityPeriod value.
     */
    public String getValidityPeriod() {
        return this.validityPeriod;
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
