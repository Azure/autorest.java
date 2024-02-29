// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Dog model.
 */
@Fluent
public final class Dog extends Pet {
    /*
     * The food property.
     */
    private String food;

    /**
     * Creates an instance of Dog class.
     */
    public Dog() {
    }

    /**
     * Get the food property: The food property.
     * 
     * @return the food value.
     */
    public String getFood() {
        return this.food;
    }

    /**
     * Set the food property: The food property.
     * 
     * @param food the food value to set.
     * @return the Dog object itself.
     */
    public Dog setFood(String food) {
        this.food = food;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dog setId(Integer id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dog setName(String name) {
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
        jsonWriter.writeNumberField("id", getId());
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeStringField("food", this.food);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Dog from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Dog if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the Dog.
     */
    public static Dog fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Dog deserializedDog = new Dog();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedDog.setId(reader.getNullable(JsonReader::getInt));
                } else if ("name".equals(fieldName)) {
                    deserializedDog.setName(reader.getString());
                } else if ("food".equals(fieldName)) {
                    deserializedDog.food = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDog;
        });
    }
}
