package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The BackupStorageResult model.
 */
@Immutable
public final class BackupStorageResult {
    /*
     * The backup blob containing the backed up storage account.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private Base64Url value;

    /**
     * Get the value property: The backup blob containing the backed up storage
     * account.
     * 
     * @return the value value.
     */
    public byte[] getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.decodedBytes();
    }

    public void validate() {
    }
}
