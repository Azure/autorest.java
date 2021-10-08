package fixtures.lro.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fixtures.lro.models.ProductPropertiesProvisioningStateValues;

/** The ProductProperties model. */
@Fluent
public final class ProductProperties {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ProductProperties.class);

    /*
     * The provisioningState property.
     */
    @JsonProperty(value = "provisioningState")
    private String provisioningState;

    /*
     * The provisioningStateValues property.
     */
    @JsonProperty(value = "provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
    private ProductPropertiesProvisioningStateValues provisioningStateValues;

    /**
     * Get the provisioningState property: The provisioningState property.
     *
     * @return the provisioningState value.
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioningState property: The provisioningState property.
     *
     * @param provisioningState the provisioningState value to set.
     * @return the ProductProperties object itself.
     */
    public ProductProperties withProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
     *
     * @return the provisioningStateValues value.
     */
    public ProductPropertiesProvisioningStateValues provisioningStateValues() {
        return this.provisioningStateValues;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
