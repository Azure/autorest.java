// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The DotFish model.
 */
@Fluent
public class DotFish implements JsonSerializable<DotFish> {
    /*
     * The fish.type property.
     */
    private String fishType = "DotFish";

    /*
     * The species property.
     */
    private String species;

    /**
     * Creates an instance of DotFish class.
     */
    public DotFish() {
    }

    /**
     * Get the fishType property: The fish.type property.
     * 
     * @return the fishType value.
     */
    public String getFishType() {
        return this.fishType;
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
     * @return the DotFish object itself.
     */
    public DotFish setSpecies(String species) {
        this.species = species;
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
        jsonWriter.writeStringField("fish.type", this.fishType);
        jsonWriter.writeStringField("species", this.species);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DotFish from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DotFish if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the DotFish.
     */
    public static DotFish fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("fish.type".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("DotSalmon".equals(discriminatorValue)) {
                    return DotSalmon.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static DotFish fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DotFish deserializedDotFish = new DotFish();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if (!DotFish.fromJsonShared(reader, fieldName, deserializedDotFish)) {
                    reader.skipChildren();
                }
            }

            return deserializedDotFish;
        });
    }

    static boolean fromJsonShared(JsonReader reader, String fieldName, DotFish deserializedDotFish) throws IOException {
        if ("fish.type".equals(fieldName)) {
            deserializedDotFish.fishType = reader.getString();
            return true;
        } else if ("species".equals(fieldName)) {
            deserializedDotFish.species = reader.getString();
            return true;
        }
        return false;
    }
}
