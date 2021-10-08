package fixtures.lro.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.management.SubResource;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fixtures.lro.models.SubProductPropertiesProvisioningStateValues;

/** The SubProduct model. */
@Fluent
public final class SubProductInner extends SubResource {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(SubProductInner.class);

    /*
     * The properties property.
     */
    @JsonProperty(value = "properties")
    private SubProductProperties innerProperties;

    /**
     * Get the innerProperties property: The properties property.
     *
     * @return the innerProperties value.
     */
    private SubProductProperties innerProperties() {
        return this.innerProperties;
    }

    /** {@inheritDoc} */
    @Override
    public SubProductInner withId(String id) {
        super.withId(id);
        return this;
    }

    /**
     * Get the provisioningState property: The provisioningState property.
     *
     * @return the provisioningState value.
     */
    public String provisioningState() {
        return this.innerProperties() == null ? null : this.innerProperties().provisioningState();
    }

    /**
     * Set the provisioningState property: The provisioningState property.
     *
     * @param provisioningState the provisioningState value to set.
     * @return the SubProductInner object itself.
     */
    public SubProductInner withProvisioningState(String provisioningState) {
        if (this.innerProperties() == null) {
            this.innerProperties = new SubProductProperties();
        }
        this.innerProperties().withProvisioningState(provisioningState);
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
     *
     * @return the provisioningStateValues value.
     */
    public SubProductPropertiesProvisioningStateValues provisioningStateValues() {
        return this.innerProperties() == null ? null : this.innerProperties().provisioningStateValues();
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (innerProperties() != null) {
            innerProperties().validate();
        }
    }
}
