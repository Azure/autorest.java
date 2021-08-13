package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The OperationResult model.
 */
@Fluent
public final class OperationResult {
    /*
     * The status of the request
     */
    @JsonProperty(value = "status")
    private OperationResultStatus status;

    /*
     * The error property.
     */
    @JsonProperty(value = "error")
    private OperationResultError error;

    /**
     * Get the status property: The status of the request.
     * 
     * @return the status value.
     */
    public OperationResultStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status of the request.
     * 
     * @param status the status value to set.
     * @return the OperationResult object itself.
     */
    public OperationResult setStatus(OperationResultStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the error property: The error property.
     * 
     * @return the error value.
     */
    public OperationResultError getError() {
        return this.error;
    }

    /**
     * Set the error property: The error property.
     * 
     * @param error the error value to set.
     * @return the OperationResult object itself.
     */
    public OperationResult setError(OperationResultError error) {
        this.error = error;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getError() != null) {
            getError().validate();
        }
    }
}
