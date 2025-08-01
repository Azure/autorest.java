// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Pet model.
 */
@Fluent
public class Pet implements JsonSerializable<Pet> {
    /*
     * The name property.
     */
    @Generated
    private String name;

    /**
     * Creates an instance of Pet class.
     */
    @Generated
    public Pet() {
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     * 
     * @param name the name value to set.
     * @return the Pet object itself.
     */
    @Generated
    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getName() == null) {
            throw LOGGER.atError().log(new IllegalArgumentException("Missing required property name in model Pet"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(Pet.class);

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Pet from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Pet if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Pet.
     */
    @Generated
    public static Pet fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Pet deserializedPet = new Pet();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedPet.name = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPet;
        });
    }
}
