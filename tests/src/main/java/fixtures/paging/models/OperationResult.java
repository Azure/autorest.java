package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The OperationResult model.
 */
@Fluent
public final class OperationResult {
    /*
     * The status property.
     */
    @JsonProperty(value = "status")
    private OperationResultStatus status;

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    public OperationResultStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status property.
     * 
     * @param status the status value to set.
     * @return the OperationResult object itself.
     */
    public OperationResult setStatus(OperationResultStatus status) {
        this.status = status;
        return this;
    }
}
