package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The Attributes model.
 */
@Fluent
public class Attributes {
    /*
     * Determines whether the object is enabled.
     */
    @JsonProperty(value = "enabled")
    private Boolean enabled;

    /*
     * Not before date in UTC.
     */
    @JsonProperty(value = "nbf")
    private Long notBefore;

    /*
     * Expiry date in UTC.
     */
    @JsonProperty(value = "exp")
    private Long expires;

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
     * Get the enabled property: Determines whether the object is enabled.
     * 
     * @return the enabled value.
     */
    public Boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: Determines whether the object is enabled.
     * 
     * @param enabled the enabled value to set.
     * @return the Attributes object itself.
     */
    public Attributes setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the notBefore property: Not before date in UTC.
     * 
     * @return the notBefore value.
     */
    public Long getNotBefore() {
        return this.notBefore;
    }

    /**
     * Set the notBefore property: Not before date in UTC.
     * 
     * @param notBefore the notBefore value to set.
     * @return the Attributes object itself.
     */
    public Attributes setNotBefore(Long notBefore) {
        this.notBefore = notBefore;
        return this;
    }

    /**
     * Get the expires property: Expiry date in UTC.
     * 
     * @return the expires value.
     */
    public Long getExpires() {
        return this.expires;
    }

    /**
     * Set the expires property: Expiry date in UTC.
     * 
     * @param expires the expires value to set.
     * @return the Attributes object itself.
     */
    public Attributes setExpires(Long expires) {
        this.expires = expires;
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
