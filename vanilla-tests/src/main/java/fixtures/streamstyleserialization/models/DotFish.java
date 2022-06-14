// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.DefaultJsonReader;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.util.Objects;

/** The DotFish model. */
@Fluent
public class DotFish implements JsonSerializable<DotFish> {
    private String species;

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
        return jsonWriter.flush();
    }

    public static DotFish fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
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
                        // If it isn't the discriminator field buffer the JSON structure to make it
                        // replayable and find the discriminator field value.
                        String json = JsonUtils.bufferJsonObject(reader);
                        JsonReader replayReader = DefaultJsonReader.fromString(json);
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
                            readerToUse = DefaultJsonReader.fromString(json);
                        }
                    }
                    // Use the discriminator value to determine which subtype should be deserialized.
                    if (discriminatorValue == null || "DotFish".equals(discriminatorValue)) {
                        return fromJsonKnownDiscriminator(readerToUse);
                    } else {
                        throw new IllegalStateException(
                                "Discriminator field 'fish\\.type' was present and didn't match one of the expected values 'DotFish'");
                    }
                });
    }

    static DotFish fromJsonKnownDiscriminator(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    boolean discriminatorPropertyFound = false;
                    String discriminatorProperty = null;
                    String species = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if (fieldName.equals("fish\\.type")) {
                            discriminatorPropertyFound = true;
                            discriminatorProperty = reader.getStringValue();
                        } else if ("species".equals(fieldName)) {
                            species = reader.getStringValue();
                        } else {
                            reader.skipChildren();
                        }
                    }

                    if (!discriminatorPropertyFound || !Objects.equals(discriminatorProperty, "DotFish")) {
                        throw new IllegalStateException(
                                "'fish\\.type' was expected to be non-null and equal to 'DotFish'. The found 'fish\\.type' was '"
                                        + discriminatorProperty
                                        + "'.");
                    }

                    DotFish deserializedValue = new DotFish();
                    deserializedValue.setSpecies(species);

                    return deserializedValue;
                });
    }
}
