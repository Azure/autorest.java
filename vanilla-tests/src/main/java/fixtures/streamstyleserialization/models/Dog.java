// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

/** The Dog model. */
@Fluent
public final class Dog extends Pet {
    /*
     * The food property.
     */
    private String food;

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

    /** {@inheritDoc} */
    @Override
    public Dog setId(Integer id) {
        super.setId(id);
        return this;
    }

    /** {@inheritDoc} */
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("id", getId());
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeStringField("food", this.food);
        return jsonWriter.writeEndObject().flush();
    }

    /**
     * Reads an instance of Dog from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Dog if the JsonReader was pointing to an instance of it, or null if it was pointing to
     *     JSON null.
     */
    public static Dog fromJson(JsonReader jsonReader) {
        return jsonReader.readObject(
                reader -> {
                    Integer id = null;
                    String name = null;
                    String food = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("id".equals(fieldName)) {
                            id = reader.getNullable(JsonReader::getInt);
                        } else if ("name".equals(fieldName)) {
                            name = reader.getString();
                        } else if ("food".equals(fieldName)) {
                            food = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    Dog deserializedValue = new Dog();
                    deserializedValue.setId(id);
                    deserializedValue.setName(name);
                    deserializedValue.food = food;

                    return deserializedValue;
                });
    }
}
