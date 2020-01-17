package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The SasDefinitionAttributes model.
 */
@Fluent
public final class SasDefinitionAttributes {
    /*
     * the enabled state of the object.
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

    /*
     * Reflects the deletion recovery level currently in effect for keys in the
     * current vault. If it contains 'Purgeable' the key can be permanently
     * deleted by a privileged user; otherwise, only the system can purge the
     * key, at the end of the retention interval.
     */
    @JsonProperty(value = "recoveryLevel", access = JsonProperty.Access.WRITE_ONLY)
    private DeletionRecoveryLevel recoveryLevel;

    /**
     * Get the enabled property: the enabled state of the object.
     * 
     * @return the enabled value.
     */
    public Boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: the enabled state of the object.
     * 
     * @param enabled the enabled value to set.
     * @return the SasDefinitionAttributes object itself.
     */
    public SasDefinitionAttributes setEnabled(Boolean enabled) {
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

    /**
     * Get the recoveryLevel property: Reflects the deletion recovery level
     * currently in effect for keys in the current vault. If it contains
     * 'Purgeable' the key can be permanently deleted by a privileged user;
     * otherwise, only the system can purge the key, at the end of the
     * retention interval.
     * 
     * @return the recoveryLevel value.
     */
    public DeletionRecoveryLevel getRecoveryLevel() {
        return this.recoveryLevel;
    }

    public void validate() {
    }
}
