package fixtures.validation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Error model. */
@Fluent
public final class Error {
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

    /*
     * The fields property.
     */
    @JsonProperty(value = "fields")
    private String fields;

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
     * @return the Error object itself.
     */
    public Error setCode(Integer code) {
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
     * @return the Error object itself.
     */
    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the fields property: The fields property.
     *
     * @return the fields value.
     */
    public String getFields() {
        return this.fields;
    }

    /**
     * Set the fields property: The fields property.
     *
     * @param fields the fields value to set.
     * @return the Error object itself.
     */
    public Error setFields(String fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
