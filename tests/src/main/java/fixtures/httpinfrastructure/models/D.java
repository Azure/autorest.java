package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The D model.
 */
@Fluent
public final class D {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "httpStatusCode")
    private String httpStatusCode;

    /**
     * Get the httpStatusCode property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the httpStatusCode value.
     */
    public String getHttpStatusCode() {
        return this.httpStatusCode;
    }

    /**
     * Set the httpStatusCode property.
     * 
     * @param httpStatusCode the httpStatusCode value to set.
     * @return the D object itself.
     */
    public D setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public void validate() {
    }
}
