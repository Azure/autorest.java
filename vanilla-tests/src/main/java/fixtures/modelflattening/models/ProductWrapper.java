package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ProductWrapper model. */
@JsonFlatten
@Fluent
public class ProductWrapper {
    /*
     * the product value
     */
    @JsonProperty(value = "property.value")
    private String value;

    /**
     * Get the value property: the product value.
     *
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: the product value.
     *
     * @param value the value value to set.
     * @return the ProductWrapper object itself.
     */
    public ProductWrapper setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
