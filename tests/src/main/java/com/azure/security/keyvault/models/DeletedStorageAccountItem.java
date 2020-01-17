package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The DeletedStorageAccountItem model.
 */
@Fluent
public final class DeletedStorageAccountItem extends StorageAccountItem {
    /*
     * The url of the recovery object, used to identify and recover the deleted
     * storage account.
     */
    @JsonProperty(value = "recoveryId")
    private String recoveryId;

    /*
     * The time when the storage account is scheduled to be purged, in UTC
     */
    @JsonProperty(value = "scheduledPurgeDate", access = JsonProperty.Access.WRITE_ONLY)
    private Long scheduledPurgeDate;

    /*
     * The time when the storage account was deleted, in UTC
     */
    @JsonProperty(value = "deletedDate", access = JsonProperty.Access.WRITE_ONLY)
    private Long deletedDate;

    /**
     * Get the recoveryId property: The url of the recovery object, used to
     * identify and recover the deleted storage account.
     * 
     * @return the recoveryId value.
     */
    public String getRecoveryId() {
        return this.recoveryId;
    }

    /**
     * Set the recoveryId property: The url of the recovery object, used to
     * identify and recover the deleted storage account.
     * 
     * @param recoveryId the recoveryId value to set.
     * @return the DeletedStorageAccountItem object itself.
     */
    public DeletedStorageAccountItem setRecoveryId(String recoveryId) {
        this.recoveryId = recoveryId;
        return this;
    }

    /**
     * Get the scheduledPurgeDate property: The time when the storage account
     * is scheduled to be purged, in UTC.
     * 
     * @return the scheduledPurgeDate value.
     */
    public Long getScheduledPurgeDate() {
        return this.scheduledPurgeDate;
    }

    /**
     * Get the deletedDate property: The time when the storage account was
     * deleted, in UTC.
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
