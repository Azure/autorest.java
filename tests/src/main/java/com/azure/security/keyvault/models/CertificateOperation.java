package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CertificateOperation model.
 */
@Fluent
public final class CertificateOperation {
    /*
     * The certificate id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * Parameters for the issuer of the X509 component of a certificate.
     */
    @JsonProperty(value = "issuer")
    private IssuerParameters issuerParameters;

    /*
     * The certificate signing request (CSR) that is being used in the
     * certificate operation.
     */
    @JsonProperty(value = "csr")
    private byte[] csr;

    /*
     * Indicates if cancellation was requested on the certificate operation.
     */
    @JsonProperty(value = "cancellation_requested")
    private Boolean cancellationRequested;

    /*
     * Status of the certificate operation.
     */
    @JsonProperty(value = "status")
    private String status;

    /*
     * The status details of the certificate operation.
     */
    @JsonProperty(value = "status_details")
    private String statusDetails;

    /*
     * The key vault server error.
     */
    @JsonProperty(value = "error")
    private Error error;

    /*
     * Location which contains the result of the certificate operation.
     */
    @JsonProperty(value = "target")
    private String target;

    /*
     * Identifier for the certificate operation.
     */
    @JsonProperty(value = "request_id")
    private String requestId;

    /**
     * Get the id property: The certificate id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the issuerParameters property: Parameters for the issuer of the X509
     * component of a certificate.
     * 
     * @return the issuerParameters value.
     */
    public IssuerParameters getIssuerParameters() {
        return this.issuerParameters;
    }

    /**
     * Set the issuerParameters property: Parameters for the issuer of the X509
     * component of a certificate.
     * 
     * @param issuerParameters the issuerParameters value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setIssuerParameters(IssuerParameters issuerParameters) {
        this.issuerParameters = issuerParameters;
        return this;
    }

    /**
     * Get the csr property: The certificate signing request (CSR) that is
     * being used in the certificate operation.
     * 
     * @return the csr value.
     */
    public byte[] getCsr() {
        return CoreUtils.clone(this.csr);
    }

    /**
     * Set the csr property: The certificate signing request (CSR) that is
     * being used in the certificate operation.
     * 
     * @param csr the csr value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setCsr(byte[] csr) {
        this.csr = CoreUtils.clone(csr);
        return this;
    }

    /**
     * Get the cancellationRequested property: Indicates if cancellation was
     * requested on the certificate operation.
     * 
     * @return the cancellationRequested value.
     */
    public Boolean isCancellationRequested() {
        return this.cancellationRequested;
    }

    /**
     * Set the cancellationRequested property: Indicates if cancellation was
     * requested on the certificate operation.
     * 
     * @param cancellationRequested the cancellationRequested value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setCancellationRequested(Boolean cancellationRequested) {
        this.cancellationRequested = cancellationRequested;
        return this;
    }

    /**
     * Get the status property: Status of the certificate operation.
     * 
     * @return the status value.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Status of the certificate operation.
     * 
     * @param status the status value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get the statusDetails property: The status details of the certificate
     * operation.
     * 
     * @return the statusDetails value.
     */
    public String getStatusDetails() {
        return this.statusDetails;
    }

    /**
     * Set the statusDetails property: The status details of the certificate
     * operation.
     * 
     * @param statusDetails the statusDetails value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
        return this;
    }

    /**
     * Get the error property: The key vault server error.
     * 
     * @return the error value.
     */
    public Error getError() {
        return this.error;
    }

    /**
     * Set the error property: The key vault server error.
     * 
     * @param error the error value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setError(Error error) {
        this.error = error;
        return this;
    }

    /**
     * Get the target property: Location which contains the result of the
     * certificate operation.
     * 
     * @return the target value.
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Set the target property: Location which contains the result of the
     * certificate operation.
     * 
     * @param target the target value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setTarget(String target) {
        this.target = target;
        return this;
    }

    /**
     * Get the requestId property: Identifier for the certificate operation.
     * 
     * @return the requestId value.
     */
    public String getRequestId() {
        return this.requestId;
    }

    /**
     * Set the requestId property: Identifier for the certificate operation.
     * 
     * @param requestId the requestId value to set.
     * @return the CertificateOperation object itself.
     */
    public CertificateOperation setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public void validate() {
        if (getIssuerParameters() != null) {
            getIssuerParameters().validate();
        }
        if (getError() != null) {
            getError().validate();
        }
    }
}
