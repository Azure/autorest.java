package fixtures.requirexmsflatteneddiscriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Describes a virtual machine scale set network profile. */
@Fluent
public final class VirtualMachineScaleSetNetworkProfile {
    /*
     * The list of network configurations.
     */
    @JsonProperty(value = "networkInterfaceConfigurations")
    private List<VirtualMachineScaleSetNetworkConfiguration> networkInterfaceConfigurations;

    /**
     * Get the networkInterfaceConfigurations property: The list of network configurations.
     *
     * @return the networkInterfaceConfigurations value.
     */
    public List<VirtualMachineScaleSetNetworkConfiguration> getNetworkInterfaceConfigurations() {
        return this.networkInterfaceConfigurations;
    }

    /**
     * Set the networkInterfaceConfigurations property: The list of network configurations.
     *
     * @param networkInterfaceConfigurations the networkInterfaceConfigurations value to set.
     * @return the VirtualMachineScaleSetNetworkProfile object itself.
     */
    public VirtualMachineScaleSetNetworkProfile setNetworkInterfaceConfigurations(
            List<VirtualMachineScaleSetNetworkConfiguration> networkInterfaceConfigurations) {
        this.networkInterfaceConfigurations = networkInterfaceConfigurations;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getNetworkInterfaceConfigurations() != null) {
            getNetworkInterfaceConfigurations().forEach(e -> e.validate());
        }
    }
}
