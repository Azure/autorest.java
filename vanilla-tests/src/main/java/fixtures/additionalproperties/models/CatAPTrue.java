// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The CatAPTrue model.
 */
@Fluent
public final class CatAPTrue extends PetAPTrue {
    /*
     * The friendly property.
     */
    private Boolean friendly;

    /**
     * Creates an instance of CatAPTrue class.
     */
    public CatAPTrue() {
    }

    /**
     * Get the friendly property: The friendly property.
     * 
     * @return the friendly value.
     */
    public Boolean isFriendly() {
        return this.friendly;
    }

    /**
     * Set the friendly property: The friendly property.
     * 
     * @param friendly the friendly value to set.
     * @return the CatAPTrue object itself.
     */
    public CatAPTrue setFriendly(Boolean friendly) {
        this.friendly = friendly;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatAPTrue setId(int id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatAPTrue setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("id", getId());
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeBooleanField("friendly", this.friendly);
        if (getAdditionalProperties() != null) {
            for (Map.Entry<String, Object> additionalProperty : getAdditionalProperties().entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CatAPTrue from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of CatAPTrue if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the CatAPTrue.
     */
    public static CatAPTrue fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            CatAPTrue deserializedCatAPTrue = new CatAPTrue();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedCatAPTrue.setId(reader.getInt());
                } else if ("name".equals(fieldName)) {
                    deserializedCatAPTrue.setName(reader.getString());
                } else if ("status".equals(fieldName)) {
                    deserializedCatAPTrue.setStatus(reader.getNullable(JsonReader::getBoolean));
                } else if ("friendly".equals(fieldName)) {
                    deserializedCatAPTrue.friendly = reader.getNullable(JsonReader::getBoolean);
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedCatAPTrue.setAdditionalProperties(additionalProperties);

            return deserializedCatAPTrue;
        });
    }
}
