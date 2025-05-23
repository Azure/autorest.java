// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes a virtual machine scale set virtual machine profile.
 */
@Fluent
public final class VirtualMachineScaleSetVMProfile {
    /*
     * Specifies properties of the network interfaces of the virtual machines in the scale set.
     */
    @Generated
    @JsonProperty(value = "networkProfile")
    private VirtualMachineScaleSetNetworkProfile networkProfile;

    /**
     * Creates an instance of VirtualMachineScaleSetVMProfile class.
     */
    @Generated
    public VirtualMachineScaleSetVMProfile() {
    }

    /**
     * Get the networkProfile property: Specifies properties of the network interfaces of the virtual machines in the
     * scale set.
     * 
     * @return the networkProfile value.
     */
    @Generated
    public VirtualMachineScaleSetNetworkProfile getNetworkProfile() {
        return this.networkProfile;
    }

    /**
     * Set the networkProfile property: Specifies properties of the network interfaces of the virtual machines in the
     * scale set.
     * 
     * @param networkProfile the networkProfile value to set.
     * @return the VirtualMachineScaleSetVMProfile object itself.
     */
    @Generated
    public VirtualMachineScaleSetVMProfile setNetworkProfile(VirtualMachineScaleSetNetworkProfile networkProfile) {
        this.networkProfile = networkProfile;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getNetworkProfile() != null) {
            getNetworkProfile().validate();
        }
    }
}
