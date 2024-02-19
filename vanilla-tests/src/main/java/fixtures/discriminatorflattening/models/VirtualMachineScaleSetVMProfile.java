// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Describes a virtual machine scale set virtual machine profile.
 */
@Fluent
public final class VirtualMachineScaleSetVMProfile implements JsonSerializable<VirtualMachineScaleSetVMProfile> {
    /*
     * Specifies properties of the network interfaces of the virtual machines in the scale set.
     */
    private VirtualMachineScaleSetNetworkProfile networkProfile;

    /**
     * Creates an instance of VirtualMachineScaleSetVMProfile class.
     */
    public VirtualMachineScaleSetVMProfile() {
    }

    /**
     * Get the networkProfile property: Specifies properties of the network interfaces of the virtual machines in the
     * scale set.
     * 
     * @return the networkProfile value.
     */
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("networkProfile", this.networkProfile);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of VirtualMachineScaleSetVMProfile from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of VirtualMachineScaleSetVMProfile if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the VirtualMachineScaleSetVMProfile.
     */
    public static VirtualMachineScaleSetVMProfile fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            VirtualMachineScaleSetVMProfile deserializedVirtualMachineScaleSetVMProfile
                = new VirtualMachineScaleSetVMProfile();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("networkProfile".equals(fieldName)) {
                    deserializedVirtualMachineScaleSetVMProfile.networkProfile
                        = VirtualMachineScaleSetNetworkProfile.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedVirtualMachineScaleSetVMProfile;
        });
    }
}
