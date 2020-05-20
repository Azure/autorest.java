package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The FlattenParameterGroup model. */
@Fluent
public final class FlattenParameterGroup {
    /*
     * Product name with value 'groupproduct'
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /*
     * Simple body product to put
     */
    @JsonProperty(value = "SimpleBodyProduct")
    private SimpleProduct simpleBodyProduct;

    /*
     * Unique identifier representing a specific product for a given latitude &
     * longitude. For example, uberX in San Francisco will have a different
     * product_id than uberX in Los Angeles.
     */
    @JsonProperty(value = "productId", required = true)
    private String productId;

    /*
     * Description of product.
     */
    @JsonProperty(value = "description")
    private String description;

    /*
     * Display name of product.
     */
    @JsonProperty(value = "max_product_display_name")
    private String maxProductDisplayName;

    /*
     * Generic URL value.
     */
    @JsonProperty(value = "generic_value")
    private String genericValue;

    /*
     * URL value.
     */
    @JsonProperty(value = "@odata.value")
    private String odataValue;

    /**
     * Get the name property: Product name with value 'groupproduct'.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Product name with value 'groupproduct'.
     *
     * @param name the name value to set.
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the simpleBodyProduct property: Simple body product to put.
     *
     * @return the simpleBodyProduct value.
     */
    public SimpleProduct getSimpleBodyProduct() {
        return this.simpleBodyProduct;
    }

    /**
     * Set the simpleBodyProduct property: Simple body product to put.
     *
     * @param simpleBodyProduct the simpleBodyProduct value to set.
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setSimpleBodyProduct(SimpleProduct simpleBodyProduct) {
        this.simpleBodyProduct = simpleBodyProduct;
        return this;
    }

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
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setProductId(String productId) {
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
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setDescription(String description) {
        this.description = description;
        return this;
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
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setMaxProductDisplayName(String maxProductDisplayName) {
        this.maxProductDisplayName = maxProductDisplayName;
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
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setGenericValue(String genericValue) {
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
     * @return the FlattenParameterGroup object itself.
     */
    public FlattenParameterGroup setOdataValue(String odataValue) {
        this.odataValue = odataValue;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getName() == null) {
            throw new IllegalArgumentException("Missing required property name in model FlattenParameterGroup");
        }
        if (getSimpleBodyProduct() != null) {
            getSimpleBodyProduct().validate();
        }
        if (getProductId() == null) {
            throw new IllegalArgumentException("Missing required property productId in model FlattenParameterGroup");
        }
    }
}
