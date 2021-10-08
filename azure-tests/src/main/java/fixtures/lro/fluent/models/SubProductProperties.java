package fixtures.lro.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fixtures.lro.models.SubProductPropertiesProvisioningStateValues;

/** The SubProductProperties model. */
@Fluent
public final class SubProductProperties {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(SubProductProperties.class);

    /*
     * The provisioningState property.
     */
    @JsonProperty(value = "provisioningState")
    private String provisioningState;

    /*
     * The provisioningStateValues property.
     */
    @JsonProperty(value = "provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
    private SubProductPropertiesProvisioningStateValues provisioningStateValues;

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
     * @return the SubProductProperties object itself.
     */
    public SubProductProperties withProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
     *
     * @return the provisioningStateValues value.
     */
    public SubProductPropertiesProvisioningStateValues provisioningStateValues() {
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
