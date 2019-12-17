package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The B model.
 */
@Fluent
public final class B extends MyException {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "textStatusCode")
    private String textStatusCode;

    /**
     * Get the textStatusCode property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the textStatusCode value.
     */
    public String getTextStatusCode() {
        return this.textStatusCode;
    }

    /**
     * Set the textStatusCode property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param textStatusCode the textStatusCode value to set.
     * @return the B object itself.
     */
    public B setTextStatusCode(String textStatusCode) {
        this.textStatusCode = textStatusCode;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
