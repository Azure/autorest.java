package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The SasDefinitionUpdateParameters model.
 */
@Fluent
public final class SasDefinitionUpdateParameters {
    /*
     * The SAS definition token template signed with an arbitrary key.  Tokens
     * created according to the SAS definition will have the same properties as
     * the template.
     */
    @JsonProperty(value = "templateUri")
    private String templateUri;

    /*
     * The type of SAS token the SAS definition will create.
     */
    @JsonProperty(value = "sasType")
    private SasTokenType sasType;

    /*
     * The validity period of SAS tokens created according to the SAS
     * definition.
     */
    @JsonProperty(value = "validityPeriod")
    private String validityPeriod;

    /*
     * The SAS definition management attributes.
     */
    @JsonProperty(value = "attributes")
    private SasDefinitionAttributes sasDefinitionAttributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

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
     * Set the templateUri property: The SAS definition token template signed
     * with an arbitrary key.  Tokens created according to the SAS definition
     * will have the same properties as the template.
     * 
     * @param templateUri the templateUri value to set.
     * @return the SasDefinitionUpdateParameters object itself.
     */
    public SasDefinitionUpdateParameters setTemplateUri(String templateUri) {
        this.templateUri = templateUri;
        return this;
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
     * Set the sasType property: The type of SAS token the SAS definition will
     * create.
     * 
     * @param sasType the sasType value to set.
     * @return the SasDefinitionUpdateParameters object itself.
     */
    public SasDefinitionUpdateParameters setSasType(SasTokenType sasType) {
        this.sasType = sasType;
        return this;
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
     * Set the validityPeriod property: The validity period of SAS tokens
     * created according to the SAS definition.
     * 
     * @param validityPeriod the validityPeriod value to set.
     * @return the SasDefinitionUpdateParameters object itself.
     */
    public SasDefinitionUpdateParameters setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
        return this;
    }

    /**
     * Get the sasDefinitionAttributes property: The SAS definition management
     * attributes.
     * 
     * @return the sasDefinitionAttributes value.
     */
    public SasDefinitionAttributes getSasDefinitionAttributes() {
        return this.sasDefinitionAttributes;
    }

    /**
     * Set the sasDefinitionAttributes property: The SAS definition management
     * attributes.
     * 
     * @param sasDefinitionAttributes the sasDefinitionAttributes value to set.
     * @return the SasDefinitionUpdateParameters object itself.
     */
    public SasDefinitionUpdateParameters setSasDefinitionAttributes(SasDefinitionAttributes sasDefinitionAttributes) {
        this.sasDefinitionAttributes = sasDefinitionAttributes;
        return this;
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

    /**
     * Set the tags property: Application specific metadata in the form of
     * key-value pairs.
     * 
     * @param tags the tags value to set.
     * @return the SasDefinitionUpdateParameters object itself.
     */
    public SasDefinitionUpdateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public void validate() {
        if (getSasDefinitionAttributes() != null) {
            getSasDefinitionAttributes().validate();
        }
    }
}
