package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The CloudError model. */
@Fluent
public final class CloudError {
    /*
     * The code property.
     */
    @JsonProperty(value = "code")
    private Integer code;

    /*
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the CloudError object itself.
     */
    public CloudError setCode(Integer code) {
        this.code = code;
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
     * @return the CloudError object itself.
     */
    public CloudError setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
