package fixtures.discriminatorflattening.clientflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes a virtual machine scale set network profile's IP configuration. */
@Fluent
public final class VirtualMachineScaleSetNetworkConfigurationProperties {
    /*
     * Specifies the primary network interface in case the virtual machine has
     * more than 1 network interface.
     */
    @JsonProperty(value = "primary")
    private Boolean primary;

    /**
     * Get the primary property: Specifies the primary network interface in case the virtual machine has more than 1
     * network interface.
     *
     * @return the primary value.
     */
    public Boolean isPrimary() {
        return this.primary;
    }

    /**
     * Set the primary property: Specifies the primary network interface in case the virtual machine has more than 1
     * network interface.
     *
     * @param primary the primary value to set.
     * @return the VirtualMachineScaleSetNetworkConfigurationProperties object itself.
     */
    public VirtualMachineScaleSetNetworkConfigurationProperties setPrimary(Boolean primary) {
        this.primary = primary;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
