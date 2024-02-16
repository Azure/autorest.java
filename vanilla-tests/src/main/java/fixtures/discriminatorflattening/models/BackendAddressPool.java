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
 * Pool of backend IP addresses.
 */
@Fluent
public final class BackendAddressPool implements JsonSerializable<BackendAddressPool> {
    /*
     * The location of the backend address pool.
     */
    private String location;

    /**
     * Creates an instance of BackendAddressPool class.
     */
    public BackendAddressPool() {
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
     * @return the BackendAddressPool object itself.
     */
    public BackendAddressPool setLocation(String location) {
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
        if (location != null) {
            jsonWriter.writeStartObject("properties");
            jsonWriter.writeStringField("location", this.location);
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BackendAddressPool from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BackendAddressPool if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the BackendAddressPool.
     */
    public static BackendAddressPool fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BackendAddressPool deserializedBackendAddressPool = new BackendAddressPool();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("properties".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("location".equals(fieldName)) {
                            deserializedBackendAddressPool.location = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                }
                reader.skipChildren();
            }

            return deserializedBackendAddressPool;
        });
    }
}
