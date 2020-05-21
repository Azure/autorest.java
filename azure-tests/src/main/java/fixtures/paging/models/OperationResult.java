package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The OperationResult model. */
@Fluent
public final class OperationResult {
    /*
     * The status of the request
     */
    @JsonProperty(value = "status")
    private OperationResultStatus status;

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
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
