// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.inheritance.passdiscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Describes a Virtual Machine Scale Set.
 */
@Fluent
public class VirtualMachineScaleSet implements JsonSerializable<VirtualMachineScaleSet> {
    /*
     * The virtual machine profile.
     */
    private VirtualMachineScaleSetVMProfile virtualMachineProfile;

    /**
     * Creates an instance of VirtualMachineScaleSet class.
     */
    public VirtualMachineScaleSet() {
    }

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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (virtualMachineProfile != null) {
            jsonWriter.writeStartObject("properties");
            jsonWriter.writeJsonField("virtualMachineProfile", this.virtualMachineProfile);
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of VirtualMachineScaleSet from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of VirtualMachineScaleSet if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the VirtualMachineScaleSet.
     */
    public static VirtualMachineScaleSet fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            VirtualMachineScaleSet deserializedVirtualMachineScaleSet = new VirtualMachineScaleSet();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("properties".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("virtualMachineProfile".equals(fieldName)) {
                            deserializedVirtualMachineScaleSet.virtualMachineProfile
                                = VirtualMachineScaleSetVMProfile.fromJson(reader);
                        } else {
                            reader.skipChildren();
                        }
                    }
                }
                reader.skipChildren();
            }

            return deserializedVirtualMachineScaleSet;
        });
    }
}
