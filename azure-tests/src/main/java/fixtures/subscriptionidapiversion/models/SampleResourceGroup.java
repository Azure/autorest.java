// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.subscriptionidapiversion.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The SampleResourceGroup model.
 */
@Fluent
public final class SampleResourceGroup implements JsonSerializable<SampleResourceGroup> {
    /*
     * resource group name 'testgroup101'
     */
    private String name;

    /*
     * resource group location 'West US'
     */
    private String location;

    /**
     * Creates an instance of SampleResourceGroup class.
     */
    public SampleResourceGroup() {
    }

    /**
     * Get the name property: resource group name 'testgroup101'.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: resource group name 'testgroup101'.
     * 
     * @param name the name value to set.
     * @return the SampleResourceGroup object itself.
     */
    public SampleResourceGroup setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the location property: resource group location 'West US'.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: resource group location 'West US'.
     * 
     * @param location the location value to set.
     * @return the SampleResourceGroup object itself.
     */
    public SampleResourceGroup setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("location", this.location);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SampleResourceGroup from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SampleResourceGroup if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the SampleResourceGroup.
     */
    public static SampleResourceGroup fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SampleResourceGroup deserializedSampleResourceGroup = new SampleResourceGroup();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedSampleResourceGroup.name = reader.getString();
                } else if ("location".equals(fieldName)) {
                    deserializedSampleResourceGroup.location = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSampleResourceGroup;
        });
    }
}
