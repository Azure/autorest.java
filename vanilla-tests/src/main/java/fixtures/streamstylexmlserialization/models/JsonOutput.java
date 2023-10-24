// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The JsonOutput model.
 */
@Fluent
public final class JsonOutput implements JsonSerializable<JsonOutput> {
    /*
     * The id property.
     */
    private Integer id;

    /**
     * Creates an instance of JsonOutput class.
     */
    public JsonOutput() {}

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     * 
     * @param id the id value to set.
     * @return the JsonOutput object itself.
     */
    public JsonOutput setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("id", this.id);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JsonOutput from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of JsonOutput if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the JsonOutput.
     */
    public static JsonOutput fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JsonOutput deserializedJsonOutput = new JsonOutput();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedJsonOutput.id = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedJsonOutput;
        });
    }
}
