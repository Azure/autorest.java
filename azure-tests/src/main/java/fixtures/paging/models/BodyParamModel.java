// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The BodyParamModel model.
 */
@Fluent
public final class BodyParamModel implements JsonSerializable<BodyParamModel> {
    /*
     * The name property.
     */
    private String name;

    /**
     * Creates an instance of BodyParamModel class.
     */
    public BodyParamModel() {
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     * 
     * @param name the name value to set.
     * @return the BodyParamModel object itself.
     */
    public BodyParamModel setName(String name) {
        this.name = name;
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
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BodyParamModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BodyParamModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the BodyParamModel.
     */
    public static BodyParamModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BodyParamModel deserializedBodyParamModel = new BodyParamModel();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedBodyParamModel.name = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBodyParamModel;
        });
    }
}
