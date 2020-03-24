package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SimpleProductProperties model.
 */
@JsonFlatten
@Fluent
public class SimpleProductProperties {
    /*
     * Display name of product.
     */
    @JsonProperty(value = "max_product_display_name", required = true)
    private String maxProductDisplayName;

    /*
     * Capacity of product. For example, 4 people.
     */
    @JsonProperty(value = "max_product_capacity", required = true)
    private String capacity;

    /*
     * Generic URL value.
     */
    @JsonProperty(value = "max_product_image.generic_value")
    private String genericValue;

    /*
     * URL value.
     */
    @JsonProperty(value = "max_product_image.@odata\\.value")
    private String odataValue;

    /**
     * Creates an instance of SimpleProductProperties class.
     */
    public SimpleProductProperties() {
        capacity = "Large";
    }

    /**
     * Get the maxProductDisplayName property: Display name of product.
     * 
     * @return the maxProductDisplayName value.
     */
    public String getMaxProductDisplayName() {
        return this.maxProductDisplayName;
    }

    /**
     * Set the maxProductDisplayName property: Display name of product.
     * 
     * @param maxProductDisplayName the maxProductDisplayName value to set.
     * @return the SimpleProductProperties object itself.
     */
    public SimpleProductProperties setMaxProductDisplayName(String maxProductDisplayName) {
        this.maxProductDisplayName = maxProductDisplayName;
        return this;
    }

    /**
     * Get the capacity property: Capacity of product. For example, 4 people.
     * 
     * @return the capacity value.
     */
    public String getCapacity() {
        return this.capacity;
    }

    /**
     * Set the capacity property: Capacity of product. For example, 4 people.
     * 
     * @param capacity the capacity value to set.
     * @return the SimpleProductProperties object itself.
     */
    public SimpleProductProperties setCapacity(String capacity) {
        this.capacity = capacity;
        return this;
    }

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
     * @return the SimpleProductProperties object itself.
     */
    public SimpleProductProperties setGenericValue(String genericValue) {
        this.genericValue = genericValue;
        return this;
    }

    /**
     * Get the odataValue property: URL value.
     * 
     * @return the odataValue value.
     */
    public String getOdataValue() {
        return this.odataValue;
    }

    /**
     * Set the odataValue property: URL value.
     * 
     * @param odataValue the odataValue value to set.
     * @return the SimpleProductProperties object itself.
     */
    public SimpleProductProperties setOdataValue(String odataValue) {
        this.odataValue = odataValue;
        return this;
    }

    public void validate() {
        if (getMaxProductDisplayName() == null) {
            throw new IllegalArgumentException("Missing required property maxProductDisplayName in model SimpleProductProperties");
        }
    }
}
