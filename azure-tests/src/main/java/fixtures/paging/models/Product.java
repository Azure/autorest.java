package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Product model. */
@Fluent
public final class Product {
    /*
     * The properties property.
     */
    @JsonProperty(value = "properties")
    private ProductProperties properties;

    /**
     * Get the properties property: The properties property.
     *
     * @return the properties value.
     */
    public ProductProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: The properties property.
     *
     * @param properties the properties value to set.
     * @return the Product object itself.
     */
    public Product setProperties(ProductProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProperties() != null) {
            getProperties().validate();
        }
    }
}
