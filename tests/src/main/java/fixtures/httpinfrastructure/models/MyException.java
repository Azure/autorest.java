package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MyException model.
 */
@Fluent
public class MyException {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "statusCode")
    private String statusCode;

    /**
     * Get the statusCode property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the statusCode value.
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * Set the statusCode property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param statusCode the statusCode value to set.
     * @return the MyException object itself.
     */
    public MyException setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public void validate() {
    }
}
