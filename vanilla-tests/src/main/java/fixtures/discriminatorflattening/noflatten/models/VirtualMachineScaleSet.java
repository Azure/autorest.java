package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes a Virtual Machine Scale Set. */
@Fluent
public final class VirtualMachineScaleSet {
    /*
     * Describes the properties of a Virtual Machine Scale Set.
     */
    @JsonProperty(value = "properties")
    private VirtualMachineScaleSetProperties properties;

    /**
     * Get the properties property: Describes the properties of a Virtual Machine Scale Set.
     *
     * @return the properties value.
     */
    public VirtualMachineScaleSetProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Describes the properties of a Virtual Machine Scale Set.
     *
     * @param properties the properties value to set.
     * @return the VirtualMachineScaleSet object itself.
     */
    public VirtualMachineScaleSet setProperties(VirtualMachineScaleSetProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProperties() != null) {
            getProperties().validate();
        }
    }
}
