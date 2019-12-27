package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The Error model.
 */
@JacksonXmlRootElement(localName = "Error")
@Fluent
public final class Error {
    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "status")
    private Integer status;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the status property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the status value.
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Set the status property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @param status the status value to set.
     * @return the Error object itself.
     */
    public Error setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get the message property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param message the message value to set.
     * @return the Error object itself.
     */
    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    public void validate() {
    }
}
