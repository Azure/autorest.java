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
 * The NoModelAsStringNoRequiredOneValueNoDefault model.
 */
@Fluent
public final class NoModelAsStringNoRequiredOneValueNoDefault
    implements JsonSerializable<NoModelAsStringNoRequiredOneValueNoDefault> {
    /*
     * The parameter property.
     */
    private String parameter = "value1";

    /**
     * Creates an instance of NoModelAsStringNoRequiredOneValueNoDefault class.
     */
    public NoModelAsStringNoRequiredOneValueNoDefault() {
    }

    /**
     * Get the parameter property: The parameter property.
     * 
     * @return the parameter value.
     */
    public String getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     * 
     * @param parameter the parameter value to set.
     * @return the NoModelAsStringNoRequiredOneValueNoDefault object itself.
     */
    public NoModelAsStringNoRequiredOneValueNoDefault setParameter(String parameter) {
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
        jsonWriter.writeStringField("parameter", this.parameter);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of NoModelAsStringNoRequiredOneValueNoDefault from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of NoModelAsStringNoRequiredOneValueNoDefault if the JsonReader was pointing to an instance
     * of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the NoModelAsStringNoRequiredOneValueNoDefault.
     */
    public static NoModelAsStringNoRequiredOneValueNoDefault fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            NoModelAsStringNoRequiredOneValueNoDefault deserializedNoModelAsStringNoRequiredOneValueNoDefault
                = new NoModelAsStringNoRequiredOneValueNoDefault();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("parameter".equals(fieldName)) {
                    deserializedNoModelAsStringNoRequiredOneValueNoDefault.parameter = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedNoModelAsStringNoRequiredOneValueNoDefault;
        });
    }
}
