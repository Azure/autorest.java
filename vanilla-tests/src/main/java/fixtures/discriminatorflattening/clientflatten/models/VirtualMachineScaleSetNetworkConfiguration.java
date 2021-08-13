package fixtures.discriminatorflattening.clientflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes a virtual machine scale set network profile's network configurations. */
@Fluent
public final class VirtualMachineScaleSetNetworkConfiguration {
    /*
     * The network configuration name.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Describes a virtual machine scale set network profile's IP
     * configuration.
     */
    @JsonProperty(value = "properties")
    private VirtualMachineScaleSetNetworkConfigurationProperties innerProperties;

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
     * Get the innerProperties property: Describes a virtual machine scale set network profile's IP configuration.
     *
     * @return the innerProperties value.
     */
    private VirtualMachineScaleSetNetworkConfigurationProperties getInnerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the primary property: Specifies the primary network interface in case the virtual machine has more than 1
     * network interface.
     *
     * @return the primary value.
     */
    public Boolean isPrimary() {
        return this.getInnerProperties() == null ? null : this.getInnerProperties().isPrimary();
    }

    /**
     * Set the primary property: Specifies the primary network interface in case the virtual machine has more than 1
     * network interface.
     *
     * @param primary the primary value to set.
     * @return the VirtualMachineScaleSetNetworkConfiguration object itself.
     */
    public VirtualMachineScaleSetNetworkConfiguration setPrimary(Boolean primary) {
        if (this.getInnerProperties() == null) {
            this.innerProperties = new VirtualMachineScaleSetNetworkConfigurationProperties();
        }
        this.getInnerProperties().setPrimary(primary);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getInnerProperties() != null) {
            getInnerProperties().validate();
        }
    }
}
