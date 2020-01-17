package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CertificateOperationUpdateParameter model.
 */
@Fluent
public final class CertificateOperationUpdateParameter {
    /*
     * Indicates if cancellation was requested on the certificate operation.
     */
    @JsonProperty(value = "cancellation_requested", required = true)
    private boolean cancellationRequested;

    /**
     * Get the cancellationRequested property: Indicates if cancellation was
     * requested on the certificate operation.
     * 
     * @return the cancellationRequested value.
     */
    public boolean isCancellationRequested() {
        return this.cancellationRequested;
    }

    /**
     * Set the cancellationRequested property: Indicates if cancellation was
     * requested on the certificate operation.
     * 
     * @param cancellationRequested the cancellationRequested value to set.
     * @return the CertificateOperationUpdateParameter object itself.
     */
    public CertificateOperationUpdateParameter setCancellationRequested(boolean cancellationRequested) {
        this.cancellationRequested = cancellationRequested;
        return this;
    }

    public void validate() {
    }
}
