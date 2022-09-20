// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

/** The DotFish model. */
@Fluent
public class DotFish implements JsonSerializable<DotFish> {
    /*
     * The species property.
     */
    private String species;

    /** Creates an instance of DotFish class. */
    public DotFish() {}

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
    public void validate() {}

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("species", this.species, false);
        return jsonWriter.writeEndObject().flush();
    }

    /**
     * Reads an instance of DotFish from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DotFish if the JsonReader was pointing to an instance of it, or null if it was pointing to
     *     JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing the polymorphic discriminator.
     */
    public static DotFish fromJson(JsonReader jsonReader) {
        return jsonReader.readObject(
                reader -> {
                    String discriminatorValue = null;
                    JsonReader readerToUse = null;

                    // Read the first field name and determine if it's the discriminator field.
                    reader.nextToken();
                    if ("fish\\.type".equals(reader.getFieldName())) {
                        reader.nextToken();
                        discriminatorValue = reader.getStringValue();
                        readerToUse = reader;
                    } else {
                        // If it isn't the discriminator field buffer the JSON to make it replayable and find the
                        // discriminator field value.
                        JsonReader replayReader = reader.bufferObject();
                        replayReader.nextToken(); // Prepare for reading
                        while (replayReader.nextToken() != JsonToken.END_OBJECT) {
                            String fieldName = replayReader.getFieldName();
                            replayReader.nextToken();
                            if ("fish\\.type".equals(fieldName)) {
                                discriminatorValue = replayReader.getStringValue();
                                break;
                            } else {
                                replayReader.skipChildren();
                            }
                        }

                        if (discriminatorValue != null) {
                            readerToUse = replayReader.reset();
                        }
                    }
                    // Use the discriminator value to determine which subtype should be deserialized.
                    if ("DotSalmon".equals(discriminatorValue)) {
                        return DotSalmon.fromJson(readerToUse);
                    } else {
                        throw new IllegalStateException(
                                "Discriminator field 'fish\\.type' didn't match one of the expected values 'DotSalmon'");
                    }
                });
    }
}
