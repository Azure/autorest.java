package fixtures.lro.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.azure.core.management.Resource;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fixtures.lro.models.ProductPropertiesProvisioningStateValues;
import java.util.Map;

/** The Product model. */
@JsonFlatten
@Fluent
public class ProductInner extends Resource {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ProductInner.class);

    /*
     * The provisioningState property.
     */
    @JsonProperty(value = "properties.provisioningState")
    private String provisioningState;

    /*
     * The provisioningStateValues property.
     */
    @JsonProperty(value = "properties.provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
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
     * @return the ProductInner object itself.
     */
    public ProductInner withProvisioningState(String provisioningState) {
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

    /** {@inheritDoc} */
    @Override
    public ProductInner withLocation(String location) {
        super.withLocation(location);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public ProductInner withTags(Map<String, String> tags) {
        super.withTags(tags);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
