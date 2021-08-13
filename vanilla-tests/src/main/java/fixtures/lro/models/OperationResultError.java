package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The OperationResultError model.
 */
@Fluent
public final class OperationResultError {
    /*
     * The error code for an operation failure
     */
    @JsonProperty(value = "code")
    private Integer code;

    /*
     * The detailed arror message
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the code property: The error code for an operation failure.
     * 
     * @return the code value.
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * Set the code property: The error code for an operation failure.
     * 
     * @param code the code value to set.
     * @return the OperationResultError object itself.
     */
    public OperationResultError setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * Get the message property: The detailed arror message.
     * 
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The detailed arror message.
     * 
     * @param message the message value to set.
     * @return the OperationResultError object itself.
     */
    public OperationResultError setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
