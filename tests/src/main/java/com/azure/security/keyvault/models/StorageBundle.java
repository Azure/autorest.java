package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The StorageBundle model.
 */
@Immutable
public class StorageBundle {
    /*
     * The storage account id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The storage account resource id.
     */
    @JsonProperty(value = "resourceId", access = JsonProperty.Access.WRITE_ONLY)
    private String resourceId;

    /*
     * The current active storage account key name.
     */
    @JsonProperty(value = "activeKeyName", access = JsonProperty.Access.WRITE_ONLY)
    private String activeKeyName;

    /*
     * whether keyvault should manage the storage account for the user.
     */
    @JsonProperty(value = "autoRegenerateKey", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean autoRegenerateKey;

    /*
     * The key regeneration time duration specified in ISO-8601 format.
     */
    @JsonProperty(value = "regenerationPeriod", access = JsonProperty.Access.WRITE_ONLY)
    private String regenerationPeriod;

    /*
     * The storage account management attributes.
     */
    @JsonProperty(value = "attributes", access = JsonProperty.Access.WRITE_ONLY)
    private StorageAccountAttributes attributes;

    /*
     * Application specific metadata in the form of key-value pairs
     */
    @JsonProperty(value = "tags", access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, String> tags;

    /**
     * Get the id property: The storage account id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the resourceId property: The storage account resource id.
     * 
     * @return the resourceId value.
     */
    public String getResourceId() {
        return this.resourceId;
    }

    /**
     * Get the activeKeyName property: The current active storage account key
     * name.
     * 
     * @return the activeKeyName value.
     */
    public String getActiveKeyName() {
        return this.activeKeyName;
    }

    /**
     * Get the autoRegenerateKey property: whether keyvault should manage the
     * storage account for the user.
     * 
     * @return the autoRegenerateKey value.
     */
    public Boolean isAutoRegenerateKey() {
        return this.autoRegenerateKey;
    }

    /**
     * Get the regenerationPeriod property: The key regeneration time duration
     * specified in ISO-8601 format.
     * 
     * @return the regenerationPeriod value.
     */
    public String getRegenerationPeriod() {
        return this.regenerationPeriod;
    }

    /**
     * Get the attributes property: The storage account management attributes.
     * 
     * @return the attributes value.
     */
    public StorageAccountAttributes getAttributes() {
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
