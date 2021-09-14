package fixtures.inheritance.donotpassdiscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes a virtual machine scale set network profile's network configurations. */
@JsonFlatten
@Fluent
public class VirtualMachineScaleSetNetworkConfiguration {
    /*
     * The network configuration name.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Specifies the primary network interface in case the virtual machine has
     * more than 1 network interface.
     */
    @JsonProperty(value = "properties.primary")
    private Boolean primary;

    /**
     * Get the name property: The network configuration name.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The network configuration name.
     *
     * @param name the name value to set.
     * @return the VirtualMachineScaleSetNetworkConfiguration object itself.
     */
    public VirtualMachineScaleSetNetworkConfiguration setName(String name) {
        this.name = name;
        return this;
    }

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
     * @return the VirtualMachineScaleSetNetworkConfiguration object itself.
     */
    public VirtualMachineScaleSetNetworkConfiguration setPrimary(Boolean primary) {
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
