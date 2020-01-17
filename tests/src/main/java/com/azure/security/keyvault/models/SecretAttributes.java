package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SecretAttributes model.
 */
@Immutable
public final class SecretAttributes extends Attributes {
    /*
     * Reflects the deletion recovery level currently in effect for keys in the
     * current vault. If it contains 'Purgeable' the key can be permanently
     * deleted by a privileged user; otherwise, only the system can purge the
     * key, at the end of the retention interval.
     */
    @JsonProperty(value = "recoveryLevel", access = JsonProperty.Access.WRITE_ONLY)
    private DeletionRecoveryLevel recoveryLevel;

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

    @Override
    public void validate() {
        super.validate();
    }
}
