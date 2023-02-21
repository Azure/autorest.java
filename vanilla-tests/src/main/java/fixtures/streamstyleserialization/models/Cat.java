// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/** The Cat model. */
@Fluent
public class Cat extends Pet {
    /*
     * The color property.
     */
    private String color;

    /*
     * The hates property.
     */
    private List<Dog> hates;

    /** Creates an instance of Cat class. */
    public Cat() {}

    /**
     * Get the color property: The color property.
     *
     * @return the color value.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set the color property: The color property.
     *
     * @param color the color value to set.
     * @return the Cat object itself.
     */
    public Cat setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Get the hates property: The hates property.
     *
     * @return the hates value.
     */
    public List<Dog> getHates() {
        return this.hates;
    }

    /**
     * Set the hates property: The hates property.
     *
     * @param hates the hates value to set.
     * @return the Cat object itself.
     */
    public Cat setHates(List<Dog> hates) {
        this.hates = hates;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Cat setId(Integer id) {
        super.setId(id);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Cat setName(String name) {
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
        if (getHates() != null) {
            getHates().forEach(e -> e.validate());
        }
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("id", getId());
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeStringField("color", this.color);
        jsonWriter.writeArrayField("hates", this.hates, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Cat from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Cat if the JsonReader was pointing to an instance of it, or null if it was pointing to
     *     JSON null.
     * @throws IOException If an error occurs while reading the Cat.
     */
    public static Cat fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    Cat deserializedCat = new Cat();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("id".equals(fieldName)) {
                            deserializedCat.setId(reader.getNullable(JsonReader::getInt));
                        } else if ("name".equals(fieldName)) {
                            deserializedCat.setName(reader.getString());
                        } else if ("color".equals(fieldName)) {
                            deserializedCat.color = reader.getString();
                        } else if ("hates".equals(fieldName)) {
                            List<Dog> hates = reader.readArray(reader1 -> Dog.fromJson(reader1));
                            deserializedCat.hates = hates;
                        } else {
                            reader.skipChildren();
                        }
                    }

                    return deserializedCat;
                });
    }
}
