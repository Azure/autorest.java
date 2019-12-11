package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MyException model.
 */
@Fluent
public class MyException {
    /*
     * The statusCode property.
     */
    @JsonProperty(value = "statusCode")
    private String statusCode;

    /**
     * Get the statusCode property: The statusCode property.
     * 
     * @return the statusCode value.
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * Set the statusCode property: The statusCode property.
     * 
     * @param statusCode the statusCode value to set.
     * @return the MyException object itself.
     */
    public MyException setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
