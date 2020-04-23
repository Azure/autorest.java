package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The D model.
 */
@Fluent
public final class D {
    /*
     * The httpStatusCode property.
     */
    @JsonProperty(value = "httpStatusCode")
    private String httpStatusCode;

    /**
     * Get the httpStatusCode property: The httpStatusCode property.
     * 
     * @return the httpStatusCode value.
     */
    public String getHttpStatusCode() {
        return this.httpStatusCode;
    }

    /**
     * Set the httpStatusCode property: The httpStatusCode property.
     * 
     * @param httpStatusCode the httpStatusCode value to set.
     * @return the D object itself.
     */
    public D setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
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
