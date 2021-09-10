package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The SubProduct model. */
@JsonFlatten
@Fluent
public class SubProduct extends SubResource {
    /*
     * The provisioningState property.
     */
    @JsonProperty(value = "properties.provisioningState")
    private String provisioningState;

    /*
     * The provisioningStateValues property.
     */
    @JsonProperty(value = "properties.provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
    private SubProductPropertiesProvisioningStateValues provisioningStateValues;

    /**
     * Get the provisioningState property: The provisioningState property.
     *
     * @return the provisioningState value.
     */
    public String getProvisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioningState property: The provisioningState property.
     *
     * @param provisioningState the provisioningState value to set.
     * @return the SubProduct object itself.
     */
    public SubProduct setProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
     *
     * @return the provisioningStateValues value.
     */
    public SubProductPropertiesProvisioningStateValues getProvisioningStateValues() {
        return this.provisioningStateValues;
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
