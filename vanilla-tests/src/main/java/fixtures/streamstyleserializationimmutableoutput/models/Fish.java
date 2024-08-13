// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationimmutableoutput.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The Fish model.
 */
@Fluent
public class Fish implements JsonSerializable<Fish> {
    /*
     * The fishtype property.
     */
    private String fishtype = "Fish";

    /*
     * The species property.
     */
    private String species;

    /*
     * The length property.
     */
    private float length;

    /*
     * The siblings property.
     */
    private List<Fish> siblings;

    /**
     * Creates an instance of Fish class.
     */
    public Fish() {
    }

    /**
     * Get the fishtype property: The fishtype property.
     * 
     * @return the fishtype value.
     */
    public String getFishtype() {
        return this.fishtype;
    }

    /**
     * Get the species property: The species property.
     * 
     * @return the species value.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Set the species property: The species property.
     * 
     * @param species the species value to set.
     * @return the Fish object itself.
     */
    public Fish setSpecies(String species) {
        this.species = species;
        return this;
    }

    /**
     * Get the length property: The length property.
     * 
     * @return the length value.
     */
    public float getLength() {
        return this.length;
    }

    /**
     * Set the length property: The length property.
     * 
     * @param length the length value to set.
     * @return the Fish object itself.
     */
    public Fish setLength(float length) {
        this.length = length;
        return this;
    }

    /**
     * Get the siblings property: The siblings property.
     * 
     * @return the siblings value.
     */
    public List<Fish> getSiblings() {
        return this.siblings;
    }

    /**
     * Set the siblings property: The siblings property.
     * 
     * @param siblings the siblings value to set.
     * @return the Fish object itself.
     */
    public Fish setSiblings(List<Fish> siblings) {
        this.siblings = siblings;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getSiblings() != null) {
            getSiblings().forEach(e -> e.validate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        toJsonShared(jsonWriter);
        return jsonWriter.writeEndObject();
    }

    void toJsonShared(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeFloatField("length", this.length);
        jsonWriter.writeStringField("fishtype", this.fishtype);
        jsonWriter.writeStringField("species", this.species);
        jsonWriter.writeArrayField("siblings", this.siblings, (writer, element) -> writer.writeJson(element));
    }

    /**
     * Reads an instance of Fish from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Fish if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Fish.
     */
    public static Fish fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("fishtype".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("salmon".equals(discriminatorValue)) {
                    return Salmon.fromJsonKnownDiscriminator(readerToUse.reset());
                } else if ("smart_salmon".equals(discriminatorValue)) {
                    return SmartSalmon.fromJson(readerToUse.reset());
                } else if ("shark".equals(discriminatorValue)) {
                    return Shark.fromJsonKnownDiscriminator(readerToUse.reset());
                } else if ("sawshark".equals(discriminatorValue)) {
                    return Sawshark.fromJson(readerToUse.reset());
                } else if ("goblin".equals(discriminatorValue)) {
                    return Goblinshark.fromJson(readerToUse.reset());
                } else if ("cookiecuttershark".equals(discriminatorValue)) {
                    return Cookiecuttershark.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static Fish fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Fish deserializedFish = new Fish();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    deserializedFish.length = reader.getFloat();
                } else if ("fishtype".equals(fieldName)) {
                    deserializedFish.fishtype = reader.getString();
                } else if ("species".equals(fieldName)) {
                    deserializedFish.species = reader.getString();
                } else if ("siblings".equals(fieldName)) {
                    List<Fish> siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                    deserializedFish.siblings = siblings;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFish;
        });
    }
}
