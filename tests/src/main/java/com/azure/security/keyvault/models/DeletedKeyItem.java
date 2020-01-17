package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The DeletedKeyItem model.
 */
@Fluent
public final class DeletedKeyItem extends KeyItem {
    /*
     * The url of the recovery object, used to identify and recover the deleted
     * key.
     */
    @JsonProperty(value = "recoveryId")
    private String recoveryId;

    /*
     * The time when the key is scheduled to be purged, in UTC
     */
    @JsonProperty(value = "scheduledPurgeDate", access = JsonProperty.Access.WRITE_ONLY)
    private Long scheduledPurgeDate;

    /*
     * The time when the key was deleted, in UTC
     */
    @JsonProperty(value = "deletedDate", access = JsonProperty.Access.WRITE_ONLY)
    private Long deletedDate;

    /**
     * Get the recoveryId property: The url of the recovery object, used to
     * identify and recover the deleted key.
     * 
     * @return the recoveryId value.
     */
    public String getRecoveryId() {
        return this.recoveryId;
    }

    /**
     * Set the recoveryId property: The url of the recovery object, used to
     * identify and recover the deleted key.
     * 
     * @param recoveryId the recoveryId value to set.
     * @return the DeletedKeyItem object itself.
     */
    public DeletedKeyItem setRecoveryId(String recoveryId) {
        this.recoveryId = recoveryId;
        return this;
    }

    /**
     * Get the scheduledPurgeDate property: The time when the key is scheduled
     * to be purged, in UTC.
     * 
     * @return the scheduledPurgeDate value.
     */
    public Long getScheduledPurgeDate() {
        return this.scheduledPurgeDate;
    }

    /**
     * Get the deletedDate property: The time when the key was deleted, in UTC.
     * 
     * @return the deletedDate value.
     */
    public Long getDeletedDate() {
        return this.deletedDate;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
