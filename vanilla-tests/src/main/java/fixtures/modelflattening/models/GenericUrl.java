package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Generic URL. */
@Fluent
public class GenericUrl {
    /*
     * Generic URL value.
     */
    @JsonProperty(value = "generic_value")
    private String genericValue;

    /**
     * Get the genericValue property: Generic URL value.
     *
     * @return the genericValue value.
     */
    public String getGenericValue() {
        return this.genericValue;
    }

    /**
     * Set the genericValue property: Generic URL value.
     *
     * @param genericValue the genericValue value to set.
     * @return the GenericUrl object itself.
     */
    public GenericUrl setGenericValue(String genericValue) {
        this.genericValue = genericValue;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
