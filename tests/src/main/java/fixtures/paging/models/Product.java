package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Product model.
 */
@Fluent
public final class Product {
    /*
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "properties")
    private ProductProperties properties;

    /**
     * Get the properties property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @return the properties value.
     */
    public ProductProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property.
     * 
     * @param properties the properties value to set.
     * @return the Product object itself.
     */
    public Product setProperties(ProductProperties properties) {
        this.properties = properties;
        return this;
    }

    public void validate() {
        if (getProperties() != null) {
            getProperties().validate();
        }
    }
}
