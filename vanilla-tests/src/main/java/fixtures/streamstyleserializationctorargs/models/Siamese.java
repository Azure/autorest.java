// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The Siamese model.
 */
@Fluent
public final class Siamese extends Cat {
    /*
     * The breed property.
     */
    private String breed;

    /**
     * Creates an instance of Siamese class.
     */
    public Siamese() {
    }

    /**
     * Get the breed property: The breed property.
     * 
     * @return the breed value.
     */
    public String getBreed() {
        return this.breed;
    }

    /**
     * Set the breed property: The breed property.
     * 
     * @param breed the breed value to set.
     * @return the Siamese object itself.
     */
    public Siamese setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setColor(String color) {
        super.setColor(color);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setHates(List<Dog> hates) {
        super.setHates(hates);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setId(Integer id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setName(String name) {
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
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("id", getId());
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeStringField("color", getColor());
        jsonWriter.writeArrayField("hates", getHates(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("breed", this.breed);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Siamese from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Siamese if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the Siamese.
     */
    public static Siamese fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Siamese deserializedSiamese = new Siamese();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedSiamese.setId(reader.getNullable(JsonReader::getInt));
                } else if ("name".equals(fieldName)) {
                    deserializedSiamese.setName(reader.getString());
                } else if ("color".equals(fieldName)) {
                    deserializedSiamese.setColor(reader.getString());
                } else if ("hates".equals(fieldName)) {
                    List<Dog> hates = reader.readArray(reader1 -> Dog.fromJson(reader1));
                    deserializedSiamese.setHates(hates);
                } else if ("breed".equals(fieldName)) {
                    deserializedSiamese.breed = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSiamese;
        });
    }
}
