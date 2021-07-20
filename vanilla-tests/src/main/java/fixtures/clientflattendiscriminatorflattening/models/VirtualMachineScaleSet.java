package fixtures.clientflattendiscriminatorflattening.models;

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
    private VirtualMachineScaleSetProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Describes the properties of a Virtual Machine Scale Set.
     *
     * @param properties the properties value to set.
     * @return the VirtualMachineScaleSet object itself.
     */
    private VirtualMachineScaleSet setProperties(VirtualMachineScaleSetProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the virtualMachineProfile property: The virtual machine profile.
     *
     * @return the virtualMachineProfile value.
     */
    public VirtualMachineScaleSetVMProfile getVirtualMachineProfile() {
        if (this.getProperties() == null) {
            return null;
        } else {
            return this.getProperties().getVirtualMachineProfile();
        }
    }

    /**
     * Set the virtualMachineProfile property: The virtual machine profile.
     *
     * @param virtualMachineProfile the virtualMachineProfile value to set.
     * @return the VirtualMachineScaleSet object itself.
     */
    public VirtualMachineScaleSet setVirtualMachineProfile(VirtualMachineScaleSetVMProfile virtualMachineProfile) {
        if (this.getProperties() == null) {
            this.setProperties(new VirtualMachineScaleSetProperties());
        }
        this.getProperties().setVirtualMachineProfile(virtualMachineProfile);
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
