package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The BaseProduct model. */
@Fluent
public class BaseProduct {
    /*
     * Unique identifier representing a specific product for a given latitude &
     * longitude. For example, uberX in San Francisco will have a different
     * product_id than uberX in Los Angeles.
     */
    @JsonProperty(value = "base_product_id", required = true)
    private String productId;

    /*
     * Description of product.
     */
    @JsonProperty(value = "base_product_description")
    private String description;

    /**
     * Get the productId property: Unique identifier representing a specific product for a given latitude &amp;
     * longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     *
     * @return the productId value.
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Set the productId property: Unique identifier representing a specific product for a given latitude &amp;
     * longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     *
     * @param productId the productId value to set.
     * @return the BaseProduct object itself.
     */
    public BaseProduct setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Get the description property: Description of product.
     *
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Description of product.
     *
     * @param description the description value to set.
     * @return the BaseProduct object itself.
     */
    public BaseProduct setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProductId() == null) {
            throw new IllegalArgumentException("Missing required property productId in model BaseProduct");
        }
    }
}
