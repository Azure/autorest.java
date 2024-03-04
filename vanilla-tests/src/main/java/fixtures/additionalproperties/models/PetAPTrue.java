// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The PetAPTrue model.
 */
@Fluent
public class PetAPTrue implements JsonSerializable<PetAPTrue> {
    /*
     * The id property.
     */
    private int id;

    /*
     * The name property.
     */
    private String name;

    /*
     * The status property.
     */
    private Boolean status;

    /*
     * Dictionary of <any>
     */
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of PetAPTrue class.
     */
    public PetAPTrue() {
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     * 
     * @param id the id value to set.
     * @return the PetAPTrue object itself.
     */
    public PetAPTrue setId(int id) {
        this.id = id;
        return this;
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
     * @return the PetAPTrue object itself.
     */
    public PetAPTrue setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    public Boolean isStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status property.
     * 
     * @param status the status value to set.
     * @return the PetAPTrue object itself.
     */
    PetAPTrue setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    /**
     * Get the additionalProperties property: Dictionary of &lt;any&gt;.
     * 
     * @return the additionalProperties value.
     */
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Dictionary of &lt;any&gt;.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the PetAPTrue object itself.
     */
    public PetAPTrue setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
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
        jsonWriter.writeIntField("id", this.id);
        jsonWriter.writeStringField("name", this.name);
        if (additionalProperties != null) {
            for (Map.Entry<String, Object> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PetAPTrue from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of PetAPTrue if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the PetAPTrue.
     */
    public static PetAPTrue fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PetAPTrue deserializedPetAPTrue = new PetAPTrue();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedPetAPTrue.id = reader.getInt();
                } else if ("name".equals(fieldName)) {
                    deserializedPetAPTrue.name = reader.getString();
                } else if ("status".equals(fieldName)) {
                    deserializedPetAPTrue.status = reader.getNullable(JsonReader::getBoolean);
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedPetAPTrue.additionalProperties = additionalProperties;

            return deserializedPetAPTrue;
        });
    }
}
