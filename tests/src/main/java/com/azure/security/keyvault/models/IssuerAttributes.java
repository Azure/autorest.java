package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The IssuerAttributes model.
 */
@Fluent
public final class IssuerAttributes {
    /*
     * Determines whether the issuer is enabled.
     */
    @JsonProperty(value = "enabled")
    private Boolean enabled;

    /*
     * Creation time in UTC.
     */
    @JsonProperty(value = "created", access = JsonProperty.Access.WRITE_ONLY)
    private Long created;

    /*
     * Last updated time in UTC.
     */
    @JsonProperty(value = "updated", access = JsonProperty.Access.WRITE_ONLY)
    private Long updated;

    /**
     * Get the enabled property: Determines whether the issuer is enabled.
     * 
     * @return the enabled value.
     */
    public Boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: Determines whether the issuer is enabled.
     * 
     * @param enabled the enabled value to set.
     * @return the IssuerAttributes object itself.
     */
    public IssuerAttributes setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the created property: Creation time in UTC.
     * 
     * @return the created value.
     */
    public Long getCreated() {
        return this.created;
    }

    /**
     * Get the updated property: Last updated time in UTC.
     * 
     * @return the updated value.
     */
    public Long getUpdated() {
        return this.updated;
    }

    public void validate() {
    }
}
