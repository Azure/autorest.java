package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The B model. */
@Fluent
public final class B extends MyException {
    /*
     * The textStatusCode property.
     */
    @JsonProperty(value = "textStatusCode")
    private String textStatusCode;

    /**
     * Get the textStatusCode property: The textStatusCode property.
     *
     * @return the textStatusCode value.
     */
    public String getTextStatusCode() {
        return this.textStatusCode;
    }

    /**
     * Set the textStatusCode property: The textStatusCode property.
     *
     * @param textStatusCode the textStatusCode value to set.
     * @return the B object itself.
     */
    public B setTextStatusCode(String textStatusCode) {
        this.textStatusCode = textStatusCode;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
