package fixtures.discriminatorflattening.clientflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes the properties of a Virtual Machine Scale Set. */
@Fluent
public final class VirtualMachineScaleSetProperties {
    /*
     * The virtual machine profile.
     */
    @JsonProperty(value = "virtualMachineProfile")
    private VirtualMachineScaleSetVMProfile virtualMachineProfile;

    /**
     * Get the virtualMachineProfile property: The virtual machine profile.
     *
     * @return the virtualMachineProfile value.
     */
    public VirtualMachineScaleSetVMProfile getVirtualMachineProfile() {
        return this.virtualMachineProfile;
    }

    /**
     * Set the virtualMachineProfile property: The virtual machine profile.
     *
     * @param virtualMachineProfile the virtualMachineProfile value to set.
     * @return the VirtualMachineScaleSetProperties object itself.
     */
    public VirtualMachineScaleSetProperties setVirtualMachineProfile(
            VirtualMachineScaleSetVMProfile virtualMachineProfile) {
        this.virtualMachineProfile = virtualMachineProfile;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getVirtualMachineProfile() != null) {
            getVirtualMachineProfile().validate();
        }
    }
}
