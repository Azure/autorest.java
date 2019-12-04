package fixtures.bodyboolean.models;

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
    private int status;

    /*
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status property.
     * 
     * @param status the status value to set.
     * @return the Error object itself.
     */
    public Error setStatus(int status) {
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
}
