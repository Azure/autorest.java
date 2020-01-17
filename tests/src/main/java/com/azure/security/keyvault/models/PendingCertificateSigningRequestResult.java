package com.azure.security.keyvault.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PendingCertificateSigningRequestResult model.
 */
@Immutable
public final class PendingCertificateSigningRequestResult {
    /*
     * The pending certificate signing request as Base64 encoded string.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private String value;

    /**
     * Get the value property: The pending certificate signing request as
     * Base64 encoded string.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    public void validate() {
    }
}
