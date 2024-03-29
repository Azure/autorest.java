// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.clientflatten.models;

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
public final class VirtualMachineScaleSet implements JsonSerializable<VirtualMachineScaleSet> {
    /*
     * Describes the properties of a Virtual Machine Scale Set.
     */
    private VirtualMachineScaleSetProperties innerProperties;

    /**
     * Creates an instance of VirtualMachineScaleSet class.
     */
    public VirtualMachineScaleSet() {
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("properties", this.innerProperties);
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

                if ("properties".equals(fieldName)) {
                    deserializedVirtualMachineScaleSet.innerProperties
                        = VirtualMachineScaleSetProperties.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedVirtualMachineScaleSet;
        });
    }
}
