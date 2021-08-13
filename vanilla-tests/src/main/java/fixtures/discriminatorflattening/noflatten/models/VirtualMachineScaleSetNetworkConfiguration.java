package fixtures.discriminatorflattening.noflatten.models;

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
    private VirtualMachineScaleSetNetworkConfigurationProperties properties;

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
     * Get the properties property: Describes a virtual machine scale set network profile's IP configuration.
     *
     * @return the properties value.
     */
    public VirtualMachineScaleSetNetworkConfigurationProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Describes a virtual machine scale set network profile's IP configuration.
     *
     * @param properties the properties value to set.
     * @return the VirtualMachineScaleSetNetworkConfiguration object itself.
     */
    public VirtualMachineScaleSetNetworkConfiguration setProperties(
            VirtualMachineScaleSetNetworkConfigurationProperties properties) {
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
