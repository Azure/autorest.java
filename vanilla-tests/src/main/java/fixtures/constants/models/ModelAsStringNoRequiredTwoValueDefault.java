// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The ModelAsStringNoRequiredTwoValueDefault model.
 */
@Fluent
public final class ModelAsStringNoRequiredTwoValueDefault
    implements JsonSerializable<ModelAsStringNoRequiredTwoValueDefault> {
    /*
     * The parameter property.
     */
    private ModelAsStringNoRequiredTwoValueDefaultEnum parameter = ModelAsStringNoRequiredTwoValueDefaultEnum.VALUE1;

    /**
     * Creates an instance of ModelAsStringNoRequiredTwoValueDefault class.
     */
    public ModelAsStringNoRequiredTwoValueDefault() {
    }

    /**
     * Get the parameter property: The parameter property.
     * 
     * @return the parameter value.
     */
    public ModelAsStringNoRequiredTwoValueDefaultEnum getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     * 
     * @param parameter the parameter value to set.
     * @return the ModelAsStringNoRequiredTwoValueDefault object itself.
     */
    public ModelAsStringNoRequiredTwoValueDefault setParameter(ModelAsStringNoRequiredTwoValueDefaultEnum parameter) {
        this.parameter = parameter;
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
        jsonWriter.writeStringField("parameter", this.parameter == null ? null : this.parameter.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ModelAsStringNoRequiredTwoValueDefault from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ModelAsStringNoRequiredTwoValueDefault if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ModelAsStringNoRequiredTwoValueDefault.
     */
    public static ModelAsStringNoRequiredTwoValueDefault fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ModelAsStringNoRequiredTwoValueDefault deserializedModelAsStringNoRequiredTwoValueDefault
                = new ModelAsStringNoRequiredTwoValueDefault();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("parameter".equals(fieldName)) {
                    deserializedModelAsStringNoRequiredTwoValueDefault.parameter
                        = ModelAsStringNoRequiredTwoValueDefaultEnum.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedModelAsStringNoRequiredTwoValueDefault;
        });
    }
}
