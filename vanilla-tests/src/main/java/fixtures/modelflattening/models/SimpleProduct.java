package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The product documentation. */
@JsonFlatten
@Fluent
public class SimpleProduct extends BaseProduct {
    /*
     * Display name of product.
     */
    @JsonProperty(value = "details.max_product_display_name")
    private String maxProductDisplayName;

    /*
     * Capacity of product. For example, 4 people.
     */
    @JsonProperty(value = "details.max_product_capacity")
    private String capacity;

    /*
     * Generic URL value.
     */
    @JsonProperty(value = "details.max_product_image.generic_value")
    private String genericValue;

    /*
     * URL value.
     */
    @JsonProperty(value = "details.max_product_image.@odata\\.value")
    private String odataValue;

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
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setMaxProductDisplayName(String maxProductDisplayName) {
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
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setCapacity(String capacity) {
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
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setGenericValue(String genericValue) {
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
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setOdataValue(String odataValue) {
        this.odataValue = odataValue;
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
