package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The C model.
 */
@Fluent
public final class C {
    /*
     * The httpCode property.
     */
    @JsonProperty(value = "httpCode")
    private String httpCode;

    /**
     * Get the httpCode property: The httpCode property.
     * 
     * @return the httpCode value.
     */
    public String getHttpCode() {
        return this.httpCode;
    }

    /**
     * Set the httpCode property: The httpCode property.
     * 
     * @param httpCode the httpCode value to set.
     * @return the C object itself.
     */
    public C setHttpCode(String httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public void validate() {
    }
}
