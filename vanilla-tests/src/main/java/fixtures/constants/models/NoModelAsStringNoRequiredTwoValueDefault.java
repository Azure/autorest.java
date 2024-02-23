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
 * The NoModelAsStringNoRequiredTwoValueDefault model.
 */
@Fluent
public final class NoModelAsStringNoRequiredTwoValueDefault
    implements JsonSerializable<NoModelAsStringNoRequiredTwoValueDefault> {
    /*
     * The parameter property.
     */
    private NoModelAsStringNoRequiredTwoValueDefaultEnum parameter
        = NoModelAsStringNoRequiredTwoValueDefaultEnum.VALUE1;

    /**
     * Creates an instance of NoModelAsStringNoRequiredTwoValueDefault class.
     */
    public NoModelAsStringNoRequiredTwoValueDefault() {
    }

    /**
     * Get the parameter property: The parameter property.
     * 
     * @return the parameter value.
     */
    public NoModelAsStringNoRequiredTwoValueDefaultEnum getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     * 
     * @param parameter the parameter value to set.
     * @return the NoModelAsStringNoRequiredTwoValueDefault object itself.
     */
    public NoModelAsStringNoRequiredTwoValueDefault
        setParameter(NoModelAsStringNoRequiredTwoValueDefaultEnum parameter) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("parameter", this.parameter == null ? null : this.parameter.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of NoModelAsStringNoRequiredTwoValueDefault from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of NoModelAsStringNoRequiredTwoValueDefault if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the NoModelAsStringNoRequiredTwoValueDefault.
     */
    public static NoModelAsStringNoRequiredTwoValueDefault fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            NoModelAsStringNoRequiredTwoValueDefault deserializedNoModelAsStringNoRequiredTwoValueDefault
                = new NoModelAsStringNoRequiredTwoValueDefault();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("parameter".equals(fieldName)) {
                    deserializedNoModelAsStringNoRequiredTwoValueDefault.parameter
                        = NoModelAsStringNoRequiredTwoValueDefaultEnum.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedNoModelAsStringNoRequiredTwoValueDefault;
        });
    }
}
