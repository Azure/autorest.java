package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Error model.
 */
@Fluent
public final class Error {
    /*
     * The status property.
     */
    @JsonProperty(value = "status")
    private Integer status;

    /*
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /*
     * The parentError property.
     */
    @JsonProperty(value = "parentError")
    private Error parentError;

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status property.
     * 
     * @param status the status value to set.
     * @return the Error object itself.
     */
    public Error setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get the message property: The message property.
     * 
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     * 
     * @param message the message value to set.
     * @return the Error object itself.
     */
    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the parentError property: The parentError property.
     * 
     * @return the parentError value.
     */
    public Error getParentError() {
        return this.parentError;
    }

    /**
     * Set the parentError property: The parentError property.
     * 
     * @param parentError the parentError value to set.
     * @return the Error object itself.
     */
    public Error setParentError(Error parentError) {
        this.parentError = parentError;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getParentError() != null) {
            getParentError().validate();
        }
    }
}
