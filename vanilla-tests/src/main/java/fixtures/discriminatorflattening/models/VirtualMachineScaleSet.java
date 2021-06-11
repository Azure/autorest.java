package fixtures.discriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes a Virtual Machine Scale Set. */
@Fluent
public final class VirtualMachineScaleSet {
    /*
     * The virtual machine profile.
     */
    @JsonFlatten
    @JsonProperty(value = "properties.virtualMachineProfile")
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
     * @return the VirtualMachineScaleSet object itself.
     */
    public VirtualMachineScaleSet setVirtualMachineProfile(VirtualMachineScaleSetVMProfile virtualMachineProfile) {
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
