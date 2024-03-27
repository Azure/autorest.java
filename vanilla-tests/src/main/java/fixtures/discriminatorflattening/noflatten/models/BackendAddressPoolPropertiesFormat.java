// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Properties of the backend address pool.
 */
@Fluent
public final class BackendAddressPoolPropertiesFormat implements JsonSerializable<BackendAddressPoolPropertiesFormat> {
    /*
     * The location of the backend address pool.
     */
    private String location;

    /**
     * Creates an instance of BackendAddressPoolPropertiesFormat class.
     */
    public BackendAddressPoolPropertiesFormat() {
    }

    /**
     * Get the location property: The location of the backend address pool.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location of the backend address pool.
     * 
     * @param location the location value to set.
     * @return the BackendAddressPoolPropertiesFormat object itself.
     */
    public BackendAddressPoolPropertiesFormat setLocation(String location) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("location", this.location);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BackendAddressPoolPropertiesFormat from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BackendAddressPoolPropertiesFormat if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BackendAddressPoolPropertiesFormat.
     */
    public static BackendAddressPoolPropertiesFormat fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BackendAddressPoolPropertiesFormat deserializedBackendAddressPoolPropertiesFormat
                = new BackendAddressPoolPropertiesFormat();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("location".equals(fieldName)) {
                    deserializedBackendAddressPoolPropertiesFormat.location = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBackendAddressPoolPropertiesFormat;
        });
    }
}
