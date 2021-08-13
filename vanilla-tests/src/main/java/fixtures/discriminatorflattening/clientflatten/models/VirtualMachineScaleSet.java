package fixtures.discriminatorflattening.clientflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Describes a Virtual Machine Scale Set. */
@Fluent
public final class VirtualMachineScaleSet {
    /*
     * Describes the properties of a Virtual Machine Scale Set.
     */
    @JsonProperty(value = "properties")
    private VirtualMachineScaleSetProperties innerProperties;

    /**
     * Get the innerProperties property: Describes the properties of a Virtual Machine Scale Set.
     *
     * @return the innerProperties value.
     */
    private VirtualMachineScaleSetProperties getInnerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the virtualMachineProfile property: The virtual machine profile.
     *
     * @return the virtualMachineProfile value.
     */
    public VirtualMachineScaleSetVMProfile getVirtualMachineProfile() {
        return this.getInnerProperties() == null ? null : this.getInnerProperties().getVirtualMachineProfile();
    }

    /**
     * Set the virtualMachineProfile property: The virtual machine profile.
     *
     * @param virtualMachineProfile the virtualMachineProfile value to set.
     * @return the VirtualMachineScaleSet object itself.
     */
    public VirtualMachineScaleSet setVirtualMachineProfile(VirtualMachineScaleSetVMProfile virtualMachineProfile) {
        if (this.getInnerProperties() == null) {
            this.innerProperties = new VirtualMachineScaleSetProperties();
        }
        this.getInnerProperties().setVirtualMachineProfile(virtualMachineProfile);
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
